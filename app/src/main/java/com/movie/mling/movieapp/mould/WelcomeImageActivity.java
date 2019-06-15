package com.movie.mling.movieapp.mould;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.mode.bean.InitBean;
import com.movie.mling.movieapp.utils.common.GlideUtils;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.widget.TitleBar;

/**
 * Created by MLing on 2016/7/20.
 */
public class WelcomeImageActivity extends BaseCompatActivity implements View.OnClickListener {
    private InitBean.DataBean.AdBean images;
    private TextView tv_to_main;
    private TimeCount time;
    private ImageView imageview;
    private boolean flag = false;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        return R.layout.activity_welcome_image;
    }

    @Override
    protected void onCreateViewContent(View view) {
        tv_to_main = view.findViewById(R.id.tv_to_main);
        imageview = view.findViewById(R.id.imageview);
    }

    @Override
    protected void getExras() {
        images = (InitBean.DataBean.AdBean) getIntent().getSerializableExtra("images");
    }

    @Override
    protected void setListener() {
        tv_to_main.setOnClickListener(this);
        imageview.setOnClickListener(this);
        if (images != null) {
            GlideUtils.getInstance().LoadContextBitmap(mContext, images.getAdpic(), imageview, R.mipmap.startapp_image, GlideUtils.LOAD_BITMAP);
            time = new TimeCount(images.getAdtime() * 1000, 1000);//构造CountDownTimer对象
            time.start();
        }else {
            time = new TimeCount(1000, 1000);//构造CountDownTimer对象
            time.start();
        }

    }

    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }

    public void toMain() {
        Intent it = new Intent(this, IndexActivity.class);
        startActivity(it);
        SharePreferenceUtil.setValue(mContext, UserConfig.KEY_IS_FIRS_TIN, true);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_to_main:
                flag = true;
                toMain();
                break;
            case R.id.imageview:
                flag = true;
                Intent it = new Intent(this, IndexActivity.class);
                it.putExtra("intenttype", 202);
                it.putExtra("intentid", images.getAdid());
                it.putExtra("adType", images.getAdtype());
                startActivity(it);
                SharePreferenceUtil.setValue(mContext, UserConfig.KEY_IS_FIRS_TIN, true);
                finish();
                break;
        }
    }


    /**
     * 计时器
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {
            if (!flag)
                toMain();
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            tv_to_main.setText(millisUntilFinished / 1000 + "s" + "跳过");
        }
    }
}

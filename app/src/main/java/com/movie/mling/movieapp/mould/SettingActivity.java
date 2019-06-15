package com.movie.mling.movieapp.mould;

import android.view.View;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;


/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/1 16:10
 * $DESE$
 */
public class SettingActivity extends BaseCompatActivity implements View.OnClickListener {
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setTitleName("设置");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(SettingActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreateViewContent(View view) {
        view.findViewById(R.id.btn_update_password).setOnClickListener(this);
        view.findViewById(R.id.btn_exit).setOnClickListener(this);
    }

    @Override
    protected void getExras() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update_password:
                ActivityAnim.intentActivity(this, ResetPassActivity.class, null);
                break;
            case R.id.btn_exit:
                SharePreferenceUtil.setValue(this, UserConfig.SYS_IS_LOGIN, false);
                ActivityAnim.intentActivity(this, IndexActivity.class, null);
                finish();
                break;
        }
    }
}

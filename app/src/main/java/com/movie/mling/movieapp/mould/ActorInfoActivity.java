package com.movie.mling.movieapp.mould;

import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.ActorInfoActivityView;
import com.movie.mling.movieapp.mode.bean.ActorBean;
import com.movie.mling.movieapp.mode.bean.ActorInfoBean;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.presenter.ActorInfoActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.CustomSingoButtonFeedbackDialog;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MLing on 2018/4/27 0027.
 */

public class ActorInfoActivity extends BaseCompatActivity implements ActorInfoActivityView, View.OnClickListener {
    private ActorInfoActivityPresenter mActorInfoActivityPresenter;
    private PDFView pdfView;
    private TextView tv_actor_phone;
    private TextView tv_actor_fankui;
    private TextView tv_shengao;
    private TextView tv_tizhong;
    private ImageView image_collect;
    private ImageView image_play;
    private RelativeLayout line_message;
    private LinearLayout line_action;
    private String actorID;
    private String keytext;
    private String actortitle;
    private TitleBar mTitleBar;
    private boolean flag = false;
    private String loadUrl;
    private String url;
    private String startFav;
    private String zhuye;
    private ArrayList<ActorInfoBean.DataBean.AlbumBean> albumlist;
    private ArrayList<ActorInfoBean.DataBean.VideoBean> videolist;
    private String userName;

    /**
     * @param titleBar 设置标题栏
     */
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mTitleBar = titleBar;
        titleBar.setShowIcon(true, true, false);
        titleBar.setRightEdit(0, "相册");
        mActorInfoActivityPresenter = new ActorInfoActivityPresenter(this);
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(ActorInfoActivity.this);
                        break;
                    case TitleBar.clickEdt:
                        Intent intent=new Intent(ActorInfoActivity.this,ActorImageActivity.class);
                        intent.putParcelableArrayListExtra("imageList",albumlist);
                        ActivityAnim.intentActivity(ActorInfoActivity.this, intent);
                        break;
                }
            }
        });
    }

    /**
     * @return 设置页面布局
     */
    @Override
    protected int onCreateViewId() {
        return R.layout.activity_info_actor;
    }

    /**
     * 设置页面布局实例化
     *
     * @param view
     */
    @Override
    protected void onCreateViewContent(View view) {
        pdfView = (PDFView) view.findViewById(R.id.pdfView);
        line_action = (LinearLayout) view.findViewById(R.id.line_action);
        line_message = (RelativeLayout) view.findViewById(R.id.line_message);
        tv_actor_phone = (TextView) view.findViewById(R.id.tv_actor_phone);
        tv_actor_fankui = (TextView) view.findViewById(R.id.tv_actor_fankui);
        image_collect = (ImageView) view.findViewById(R.id.image_collect);
        image_play = (ImageView) view.findViewById(R.id.image_play);
        tv_shengao = (TextView) view.findViewById(R.id.tv_shengao);
        tv_tizhong = (TextView) view.findViewById(R.id.tv_tizhong);
    }

    /**
     * 本地传参
     */
    @Override
    protected void getExras() {
        actorID = getIntent().getStringExtra("actorID");
        actortitle = getIntent().getStringExtra("actortitle");

    }

    /**
     * 监听事件
     */
    @Override
    protected void setListener() {
//        mTitleBar.setTitleName(actortitle);
        tv_actor_phone.setOnClickListener(this);
        tv_actor_fankui.setOnClickListener(this);
        image_collect.setOnClickListener(this);
        image_play.setOnClickListener(this);
        pdfView.setOnClickListener(this);
        url = Constants.APP_USER_SERVICE_STARAND_INFO;
        mActorInfoActivityPresenter.getInfo(url);
    }

    /**
     * 获取数据方法，之后，View赋值
     */
    @Override
    protected void fromNetGetData() {

    }

    /**
     * 获取数据方法，之后，View赋值
     */
    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public RequestParams getParamentersFeedback() {
        RequestParams params = AppMethod.getMapParams(url);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        params.put("keyid", actorID);
        params.put("keytext", keytext);
        return params;
    }

    @Override
    public void excuteSuccessCallBack(ActorInfoBean userVo) {
        userName =userVo.getData().getXingming();
        mTitleBar.setTitleName(userVo.getData().getXingming());
        zhuye = userVo.getData().getZhuye();
        tv_shengao.setText(userVo.getData().getShengao());
        tv_tizhong.setText(userVo.getData().getTizhong());
        startFav = userVo.getData().getIsfav();
        albumlist = userVo.getData().getAlbumlist();
        videolist = userVo.getData().getVideolist();
        if (!TextUtils.isEmpty(startFav) && TextUtils.equals("1", startFav)) {
            image_collect.setImageResource(R.mipmap.icon_actor_shoucang);
        } else {
            image_collect.setImageResource(R.mipmap.icon_actor_shoucang_un);
        }
        if (TextUtils.isEmpty(userVo.getData().getVideo())) {
            image_play.setVisibility(View.GONE);
        } else {
            loadUrl = userVo.getData().getVideo();
            image_play.setVisibility(View.VISIBLE);
        }
        new RetrievePDFBytes().execute(userVo.getData().getPdf());
    }

    @Override
    public void excuteSuccessFavCallBack(CallBackVo mCallBackVo) {
        if (TextUtils.equals("1", startFav)) {
            image_collect.setImageResource(R.mipmap.icon_actor_shoucang_un);
            startFav = "0";
        } else {
            image_collect.setImageResource(R.mipmap.icon_actor_shoucang);
            startFav = "1";
        }
    }

    @Override
    public void excuteSuccessFeedbackCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(this).showToast("提交成功");
    }

    /**
     * 获取参数
     *
     * @return
     */
    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(url);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        params.put("keyid", actorID);
        params.put("pid", actorID);
        return params;
    }

    /**
     * 显示操作进度
     */
    @Override
    public void showProgress() {
    }

    /**
     * 关闭进度
     */
    @Override
    public void closeProgress() {

    }

    /**
     * 失败回调
     *
     * @param mCallBackVo
     */
    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(this).showToast(mCallBackVo.getMessage());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_actor_phone:
                Map<String, String> map = new HashMap<>();
                map.put("user_uid", zhuye);
                ActivityAnim.intentActivity(this, UserInfoActivity.class, map);
                break;
            case R.id.tv_actor_fankui:
                CustomSingoButtonFeedbackDialog dialog = new CustomSingoButtonFeedbackDialog(this);
                dialog.setButtonListener(new CustomSingoButtonFeedbackDialog.OnButtonListener() {
                    @Override
                    public void onLeftButtonClick(CustomSingoButtonFeedbackDialog var1, String str) {
                        if (!TextUtils.isEmpty(str)) {
                            var1.cancel();
                            keytext = str;
                            url = Constants.APP_USER_SERVICE_STAR_FEEDBACK;
                            mActorInfoActivityPresenter.getActorFeedback(url);
                        }
                    }
                });

                break;
            case R.id.image_collect:
                if (!SharePreferenceUtil.getBoolean(ActorInfoActivity.this, UserConfig.SYS_IS_LOGIN, false)) {
                    //去登陆页
                    ActivityAnim.intentActivity(this, LoginActivity.class, null);
                } else {
                    url = Constants.APP_USER_SERVICE_STAR_FAV;
                    mActorInfoActivityPresenter.getActorFav(url);
                }
                break;
            case R.id.image_play:
                Intent intent=new Intent(ActorInfoActivity.this,VideoListActivity.class);
                intent.putExtra("videoname",userName);
                intent.putParcelableArrayListExtra("videoList",videolist);
                ActivityAnim.intentActivity(ActorInfoActivity.this, intent);
                break;
            case R.id.pdfView:
                if (flag) {
                    line_message.setVisibility(View.GONE);
                    line_action.setVisibility(View.GONE);
                    flag = false;
                } else {
                    line_message.setVisibility(View.VISIBLE);
                    line_action.setVisibility(View.VISIBLE);
                    flag = true;
                }
                break;
        }
    }

    class RetrievePDFBytes extends AsyncTask<String, Void, byte[]> {

        @Override
        protected byte[] doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                return null;
            }
            try {
                return IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            pdfView.fromBytes(bytes).load();
        }
    }
}

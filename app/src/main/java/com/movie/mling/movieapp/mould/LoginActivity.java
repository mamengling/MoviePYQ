package com.movie.mling.movieapp.mould;

import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.LoginActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.UserVo;
import com.movie.mling.movieapp.presenter.LoginActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppManager;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.MD5Utils;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import cn.jpush.android.api.JPushInterface;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/26 16:48
 * $DESE$
 */
public class LoginActivity extends BaseCompatActivity implements LoginActivityView, View.OnClickListener {
    private LoginActivityPresenter mLoginActivityPresenter;
    private View rootView;
    private EditText edt_user_phone;
    private EditText edt_user_password;
    private String userPhone;
    private String userPassword;
    private boolean mIsExit;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mLoginActivityPresenter = new LoginActivityPresenter(this);
        titleBar.setTitleName("登录");
        titleBar.setRightEdit(0, "注册");
        titleBar.setShowIcon(true, true, false);
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(LoginActivity.this);
                        break;
                    case TitleBar.clickEdt:
                        ActivityAnim.intentActivity(LoginActivity.this, RegisterActivity.class, null);
                        break;
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreateViewContent(View view) {
        edt_user_phone = view.findViewById(R.id.edt_user_phone);
        edt_user_password = view.findViewById(R.id.edt_user_password);
        rootView = view.findViewById(R.id.rootView);
        view.findViewById(R.id.btn_login).setOnClickListener(this);
        view.findViewById(R.id.tv_find_psw).setOnClickListener(this);
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
    public RequestParams getParamenters() {
        RequestParams mapParams = new RequestParams();
        String timestamp = AppMethod.getSecondTimestampTwo();
        mapParams.put("sign", MD5Utils.Md5("vfbw4UdPkHfSwMVh|service/login|" + timestamp));
        mapParams.put("timestamp", timestamp);
        mapParams.put("username", userPhone);
        mapParams.put("password", userPassword);
        mapParams.put("channelid",JPushInterface.getRegistrationID(this));
        mapParams.put("devicetype", "2");//安卓为2
        mapParams.put("lastloginversion", "1.1.1");
        return mapParams;
    }

    @Override
    public void showProgress() {
        MDialog.getInstance(this).showProgressDialog();
    }

    @Override
    public void closeProgress() {
        MDialog.getInstance(this).closeProgressDialog();
    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(this).showToast(rootView,mCallBackVo.getMessage());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                isInputSuccess();
                break;
            case R.id.tv_find_psw:
                ActivityAnim.intentActivity(this, FindPasswordActivity.class, null);
                break;

        }
    }

    private void isInputSuccess() {
        userPhone = edt_user_phone.getText().toString();
        userPassword = edt_user_password.getText().toString();
        if (TextUtils.isEmpty(userPhone)) {
            MDialog.getInstance(this).showToast(rootView, "请输入手机号");
            return;
        }
        if (!AppMethod.isMobileNO(userPhone)) {
            MDialog.getInstance(this).showToast(rootView, "请输入正确的手机号");
            return;
        }

        if (TextUtils.isEmpty(userPassword)) {
            MDialog.getInstance(this).showToast("请输入密码");
            return;
        }
        mLoginActivityPresenter.getUserLogin();
    }

    @Override
    public void excuteSuccessCallBack(UserVo mCallBackVo) {
        SharePreferenceUtil.setValue(this, UserConfig.SYS_IS_LOGIN, true);
        SharePreferenceUtil.setValue(this, UserConfig.USER_NAME, mCallBackVo.getData().getUsername());
        SharePreferenceUtil.setValue(this, UserConfig.USER_ID, mCallBackVo.getData().getId());
        SharePreferenceUtil.setValue(this, UserConfig.USER_UID, mCallBackVo.getData().getId());
        SharePreferenceUtil.setValue(this, UserConfig.USER_NICK, mCallBackVo.getData().getNickname());
        SharePreferenceUtil.setValue(this, UserConfig.USER_TOKEN, mCallBackVo.getData().getToken());
        SharePreferenceUtil.setValue(this, UserConfig.USER_TOKEN, mCallBackVo.getData().getToken());
        ActivityAnim.intentActivity(this, IndexActivity.class, null);
        finish();
    }


    @Override
    /**
     * 双击返回键退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                AppManager.getAppManager().AppExit(this);
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}

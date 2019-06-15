package com.movie.mling.movieapp.mould;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.RegisterActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.presenter.RegisterActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import cn.jpush.android.api.JPushInterface;


/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/26 17:08
 * $DESE$
 */
public class RegisterActivity extends BaseCompatActivity implements View.OnClickListener, RegisterActivityView {
    private RegisterActivityPresenter mRegisterActivityPresenter;
    protected View rootView;
    private EditText edt_phone;
    private EditText edt_verify;
    private TextView tv_verify_git;
    private TextView tv_show_code;
    private EditText edt_password;
    private EditText edt_password_again;
    private Button btn_register;
    private String userPhone;
    private String userPassword;
    private String userAgainPassword;
    private String userCode;
    private TimeCount time;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mRegisterActivityPresenter = new RegisterActivityPresenter(this);
        titleBar.setTitleName("注册");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(RegisterActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreateViewContent(View view) {
        edt_phone = view.findViewById(R.id.edt_phone);
        edt_verify = view.findViewById(R.id.edt_verify);
        tv_verify_git = view.findViewById(R.id.tv_verify_git);
        edt_password = view.findViewById(R.id.edt_password);
        edt_password_again = view.findViewById(R.id.edt_password_again);
        btn_register = view.findViewById(R.id.btn_register);
        tv_show_code = view.findViewById(R.id.tv_show_code);
        rootView = view.findViewById(R.id.rootView);
    }

    @Override
    protected void getExras() {

    }

    @Override
    protected void setListener() {
        time = new TimeCount(6000, 1000);//构造CountDownTimer对象
        tv_show_code.setOnClickListener(this);
        tv_verify_git.setOnClickListener(this);
        btn_register.setOnClickListener(this);
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
            case R.id.btn_register:
                isInputRegister();
                break;
            case R.id.tv_verify_git:
                isInputCode();
                break;
            case R.id.tv_show_code:
                isInputShowCode();
                break;
        }
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams mapParams = AppMethod.getMapParams(Constants.APP_USER_ADD);
        mapParams.put("username", userPhone);
        mapParams.put("yzm", userCode);
        mapParams.put("password", userPassword);
        mapParams.put("channelid", JPushInterface.getRegistrationID(this));
        mapParams.put("devicetype", "2");//安卓为2
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
        MDialog.getInstance(this).showToast(rootView, mCallBackVo.getMessage());
    }

    @Override
    public RequestParams getParamentersShow() {
        RequestParams mapParams = AppMethod.getMapParams(Constants.APP_USER_SHOW_CODE);
        mapParams.put("username", userPhone);
        return mapParams;
    }

    @Override
    public RequestParams getParamentersSend() {
        RequestParams mapParams = AppMethod.getMapParams(Constants.APP_USER_SENDSMS);
        mapParams.put("username", userPhone);
        return mapParams;
    }

    @Override
    public void excuteSuccessGetCodeCallBack(CallBackVo mCallBackVo) {
        time.start();
    }

    @Override
    public void excuteSuccessGetRegisterCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(this).showToast(rootView, mCallBackVo.getMessage());
        ActivityAnim.intentActivity(this, LoginActivity.class, null);
        finish();
    }

    @Override
    public void excuteSuccessGetChowCodeCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(this).showToast(rootView, (String) mCallBackVo.getData());
    }

    public void isInputRegister() {
        userPhone = edt_phone.getText().toString();
        userPassword = edt_password.getText().toString();
        userAgainPassword = edt_password_again.getText().toString();
        userCode = edt_verify.getText().toString();
        if (TextUtils.isEmpty(userPhone)) {
            MDialog.getInstance(this).showToast(rootView, "请输入手机号");
            return;
        }
        if (!AppMethod.isMobileNO(userPhone)) {
            MDialog.getInstance(this).showToast(rootView, "请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(userCode)) {
            MDialog.getInstance(this).showToast(rootView, "请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(userPassword)) {
            MDialog.getInstance(this).showToast(rootView, "请输入密码");
            return;
        }
        if (TextUtils.isEmpty(userAgainPassword)) {
            MDialog.getInstance(this).showToast(rootView, "请输入确认密码");
            return;
        }
        if (!TextUtils.equals(userPassword, userAgainPassword)) {
            MDialog.getInstance(this).showToast(rootView, "两次密码输入不一致");
            return;
        }
        mRegisterActivityPresenter.getRegister();
    }

    public void isInputCode() {
        userPhone = edt_phone.getText().toString();
        if (TextUtils.isEmpty(userPhone)) {
            MDialog.getInstance(this).showToast(rootView, "请输入手机号");
            return;
        }
        if (!AppMethod.isMobileNO(userPhone)) {
            MDialog.getInstance(this).showToast(rootView, "请输入正确的手机号");
            return;
        }
        mRegisterActivityPresenter.getPhoneSMS();
    }

    public void isInputShowCode() {
        userPhone = edt_phone.getText().toString();
        if (TextUtils.isEmpty(userPhone)) {
            MDialog.getInstance(this).showToast(rootView, "请输入手机号");
            return;
        }
        if (!AppMethod.isMobileNO(userPhone)) {
            MDialog.getInstance(this).showToast(rootView, "请输入正确的手机号");
            return;
        }
        mRegisterActivityPresenter.getShowCode();
    }


    /**
     * 计时器
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            tv_verify_git.setText("重新获取");
            tv_verify_git.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            tv_verify_git.setClickable(false);
            tv_verify_git.setText(millisUntilFinished / 1000 + "秒" + "后重发");
        }
    }
}

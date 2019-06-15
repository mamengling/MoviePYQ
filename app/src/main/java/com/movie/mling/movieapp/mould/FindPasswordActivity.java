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
import com.movie.mling.movieapp.iactivityview.FindPasswordActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.presenter.FindPasswordActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/19 14:30
 * $DESE$
 */
public class FindPasswordActivity extends BaseCompatActivity implements FindPasswordActivityView, View.OnClickListener {
    private FindPasswordActivityPresenter mFindPasswordActivityPresenter;
    private EditText edt_phone;
    private EditText edt_verify;
    private TextView tv_verify_git;
    private TextView tv_show_code;
    private EditText edt_password;
    private TimeCount time;
    private Button btn_register;
    private String userPhone;
    private String userPassword;
    private String userCode;
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mFindPasswordActivityPresenter = new FindPasswordActivityPresenter(this);
        titleBar.setTitleName("忘记密码");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(FindPasswordActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_find_password;
    }

    @Override
    protected void onCreateViewContent(View view) {
        edt_phone = view.findViewById(R.id.edt_phone);
        edt_verify = view.findViewById(R.id.edt_verify);
        tv_verify_git = view.findViewById(R.id.tv_verify_git);
        edt_password = view.findViewById(R.id.edt_password);
        tv_show_code = view.findViewById(R.id.tv_show_code);
        btn_register = view.findViewById(R.id.btn_register);
        rootView = view.findViewById(R.id.rootView);
    }

    @Override
    protected void getExras() {

    }

    @Override
    protected void setListener() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
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
    public RequestParams getParamentersSend() {
        RequestParams mapParams = AppMethod.getMapParams(Constants.APP_USER_SENDSMS);
        mapParams.put("username", userPhone);
        return mapParams;
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(this).showToast(rootView, mCallBackVo.getMessage());
        finish();
    }

    @Override
    public void excuteSuccessGetCodeCallBack(CallBackVo mCallBackVo) {
        time.start();
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams mapParams = AppMethod.getMapParams(Constants.APP_USER_FIND_PASS);
        mapParams.put("username", userPhone);
        mapParams.put("password", userPassword);
        mapParams.put("yzm", userCode);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                isInputSucFinsh();
                break;
            case R.id.tv_verify_git:
                isInputSuccess();
                break;
        }
    }

    public void isInputSuccess() {
        userPhone = edt_phone.getText().toString();
        if (TextUtils.isEmpty(userPhone)) {
            MDialog.getInstance(this).showToast(rootView, "请输入手机号");
            return;
        }
        if (!AppMethod.isMobileNO(userPhone)) {
            MDialog.getInstance(this).showToast(rootView, "请输入正确的手机号");
            return;
        }
        mFindPasswordActivityPresenter.getPhoneSMS();
    }

    public void isInputSucFinsh() {
        userPhone = edt_phone.getText().toString();
        userPassword = edt_password.getText().toString();
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
        mFindPasswordActivityPresenter.toFind();
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

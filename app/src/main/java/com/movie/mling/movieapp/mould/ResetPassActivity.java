package com.movie.mling.movieapp.mould;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.ResetPassActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.presenter.ResetPassActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/19 15:30
 * $DESE$
 */
public class ResetPassActivity extends BaseCompatActivity implements ResetPassActivityView {
    private ResetPassActivityPresenter mResetPassActivityPresenter;
    private EditText edt_password;
    private EditText edt_password_again;
    private View root_view;
    private String oldPsw;
    private String newPsw;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mResetPassActivityPresenter = new ResetPassActivityPresenter(this);
        titleBar.setTitleName("修改密码");
        titleBar.setShowIcon(true, true, false);
        titleBar.setRightEdit(0, "提交");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(ResetPassActivity.this);
                        break;
                    case TitleBar.clickEdt:
                        inPutSuccess();
                        break;
                }
            }
        });
    }

    private void inPutSuccess() {
        oldPsw = edt_password.getText().toString();
        newPsw = edt_password_again.getText().toString();
        if (TextUtils.isEmpty(oldPsw)) {
            MDialog.getInstance(this).showToast(rootView, "请输入旧密码");
            return;
        }
        if (TextUtils.isEmpty(newPsw)) {
            MDialog.getInstance(this).showToast(rootView, "请输入新密码");
            return;
        }
        if (TextUtils.equals(oldPsw, newPsw)) {
            MDialog.getInstance(this).showToast(rootView, "新密码与旧密码相同");
            return;
        }
        mResetPassActivityPresenter.getResetPsw();
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_reset_pass;
    }

    @Override
    protected void onCreateViewContent(View view) {
        edt_password = view.findViewById(R.id.edt_password);
        edt_password_again = view.findViewById(R.id.edt_password_again);
        root_view = view.findViewById(R.id.root_view);
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
    public void excuteSuccessCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(this).showToast(root_view, mCallBackVo.getMessage());
        finish();
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams mapParams = AppMethod.getMapParams(Constants.APP_USER_RESET_PASS);
        mapParams.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        mapParams.put("oldpass", oldPsw);
        mapParams.put("newpass", newPsw);
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
        MDialog.getInstance(this).showToast(root_view, mCallBackVo.getMessage());
    }

}

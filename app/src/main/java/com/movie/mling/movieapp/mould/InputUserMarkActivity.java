package com.movie.mling.movieapp.mould;

import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.InputUserMarkActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.presenter.InputUserMarkActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2019/2/15 15:44
 */

public class InputUserMarkActivity extends BaseCompatActivity implements InputUserMarkActivityView {
    private InputUserMarkActivityPresenter mPresenter;
    private TextInputLayout editText;
    private String inputContent;
    private String keyid;
    private String keytext;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mPresenter = new InputUserMarkActivityPresenter(this);
        titleBar.setShowIcon(true, true, false);
        titleBar.setTitleName("见组备注");
        titleBar.setRightEdit(0, "提交");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(InputUserMarkActivity.this);
                        break;
                    case TitleBar.clickEdt:
                        inputSuccess();
                        break;
                }
            }
        });
    }

    private void inputSuccess() {
        inputContent = editText.getEditText().getText().toString();
//        if (TextUtils.isEmpty(inputContent)) {
//            MDialog.getInstance(this).showToast(rootView, "请输入见组信息");
//            return;
//        }
        mPresenter.putMarker();
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_input_mart;
    }

    @Override
    protected void onCreateViewContent(View view) {
        editText = view.findViewById(R.id.editText);
    }

    @Override
    protected void getExras() {
        keyid = getIntent().getStringExtra("keyid");
        keytext = getIntent().getStringExtra("keytext");
    }

    @Override
    protected void setListener() {
        editText.getEditText().setText(keytext);
    }

    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_SERVICE_FILM_MARK);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        params.put("keyid", keyid);
        params.put("keytext", inputContent);
        return params;
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
        MDialog.getInstance(this).showToast(mCallBackVo.getMessage());
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(this).showToast("添加通告备注成功");
        finish();
    }
}

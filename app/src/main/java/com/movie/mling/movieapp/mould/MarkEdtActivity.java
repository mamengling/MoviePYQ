package com.movie.mling.movieapp.mould;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.MarkEdtActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.presenter.MarkEdtActivityPresenter;
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
 * 创建时间：2019/2/16 11:23
 */

public class MarkEdtActivity extends BaseCompatActivity implements MarkEdtActivityView, View.OnClickListener {
    private MarkEdtActivityPresenter mPresenter;
    private TextInputLayout editText;
    private Button btn_delete;
    private String inputContent;
    private String markid;
    private String newstext;
    private int notType;
    private String url;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mPresenter = new MarkEdtActivityPresenter(this);
        titleBar.setTitleName("添加工作备注");
        titleBar.setRightEdit(0, "提交");
        titleBar.setShowIcon(true, true, false);
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(MarkEdtActivity.this);
                        break;
                    case TitleBar.clickEdt:
                        notType = 1;
                        url = Constants.APP_USER_SERVICE_NOT_SAVE;
                        inputSuccess();
                        break;
                }
            }
        });
    }

    private void inputSuccess() {
        inputContent = editText.getEditText().getText().toString();
        if (TextUtils.isEmpty(inputContent)) {
            MDialog.getInstance(this).showToast(rootView, "工作备注不能为空");
            return;
        }
        mPresenter.putMarker();
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_edt_mark;
    }

    @Override
    protected void onCreateViewContent(View view) {
        editText = view.findViewById(R.id.editText);
        btn_delete = view.findViewById(R.id.btn_delete);
    }

    @Override
    protected void getExras() {
        markid = getIntent().getStringExtra("markid");
        newstext = getIntent().getStringExtra("newstext");
    }

    @Override
    protected void setListener() {
        btn_delete.setOnClickListener(this);
        editText.getEditText().setText(newstext);
        if (!TextUtils.isEmpty(markid)) {
            btn_delete.setVisibility(View.VISIBLE);
        } else {
            btn_delete.setVisibility(View.GONE);
        }
    }

    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(url);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        params.put("id", markid);
        params.put("newstext", inputContent);
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
    public void excuteSuccessCallBack(CallBackVo userVo) {
        if (notType == 1) {
            MDialog.getInstance(this).showToast("添加工作备注成功");
        } else {
            MDialog.getInstance(this).showToast("删除工作备注成功");
        }
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_delete:
                notType = 2;
                url = Constants.APP_USER_SERVICE_NOT_DELETE;
                mPresenter.markDelete();
                break;
        }
    }
}

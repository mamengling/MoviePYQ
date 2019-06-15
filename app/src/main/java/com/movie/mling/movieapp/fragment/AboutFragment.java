package com.movie.mling.movieapp.fragment;


import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.iactivityview.AboutFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.UserVo;
import com.movie.mling.movieapp.presenter.AboutFragmentPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.TitleBar;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/26 17:27
 * $DESE$
 */
public class AboutFragment extends BaseFragment implements AboutFragmentView, View.OnClickListener {
    private AboutFragmentPresenter mAboutFragmentPresenter;
    private TextInputLayout editText;
    private String mUserAdoutContent = "";
    private String urlMethod = "";
    private String nickname;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mAboutFragmentPresenter = new AboutFragmentPresenter(this);
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void onCreateViewContent(View view) {
        editText = (TextInputLayout) view.findViewById(R.id.editText);
        view.findViewById(R.id.btn_save).setOnClickListener(this);
    }

    @Override
    protected void fromNetGetData() {
        urlMethod = Constants.APP_USER_INFO;
        mAboutFragmentPresenter.getUser();
    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                mUserAdoutContent = editText.getEditText().getText().toString();
                urlMethod = Constants.APP_USER_SAVE;
                mAboutFragmentPresenter.putUser();
                break;
        }
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(urlMethod);
        params.put("token", SharePreferenceUtil.getString(getContext(), UserConfig.USER_TOKEN, ""));
        params.put("intro", mUserAdoutContent);
        params.put("nickname", nickname);
        return params;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {

    }

    @Override
    public void excuteSuccessGetCallBack(UserVo mCallBackVo) {
        editText.getEditText().setText(mCallBackVo.getData().getIntro());
        nickname = mCallBackVo.getData().getNickname();
    }

    @Override
    public void excuteSuccessPutCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(getActivity()).showToast("保存成功");
    }
}

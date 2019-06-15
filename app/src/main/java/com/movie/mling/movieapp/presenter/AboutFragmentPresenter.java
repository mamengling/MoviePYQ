package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.AboutFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.UserVo;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import cz.msebera.android.httpclient.Header;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/28 11:15
 * $DESE$
 */
public class AboutFragmentPresenter {
    private AboutFragmentView mAboutFragmentView;

    public AboutFragmentPresenter(AboutFragmentView mAboutFragmentView) {
        this.mAboutFragmentView = mAboutFragmentView;
    }

    public void getUser() {
        mAboutFragmentView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_INFO, mAboutFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mAboutFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mAboutFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mAboutFragmentView.closeProgress();
                Gson gson = new Gson();
                UserVo userVo = gson.fromJson(result, UserVo.class);
                if (userVo.getCode() == 0) {
                    mAboutFragmentView.excuteSuccessGetCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mAboutFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mAboutFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mAboutFragmentView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

    public void putUser() {
        mAboutFragmentView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_SAVE, mAboutFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mAboutFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mAboutFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mAboutFragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo userVo = gson.fromJson(result, CallBackVo.class);
                if (userVo.getCode() == 0) {
                    mAboutFragmentView.excuteSuccessPutCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mAboutFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mAboutFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mAboutFragmentView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

}

package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.ScreenActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.ScreenBean;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import cz.msebera.android.httpclient.Header;

public class ScreenActivityPresenter{
    private ScreenActivityView mScreenActivityView;

    public ScreenActivityPresenter(ScreenActivityView mScreenActivityView) {
        this.mScreenActivityView = mScreenActivityView;
    }


    public void getLableList() {
        mScreenActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_SERVICE_SEARCH_STARCATE, mScreenActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mScreenActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mScreenActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mScreenActivityView.closeProgress();
                Gson gson = new Gson();
                ScreenBean userVo = gson.fromJson(result, ScreenBean.class);
                if (userVo.getCode() == 0) {
                    mScreenActivityView.excuteSuccessCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mScreenActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mScreenActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mScreenActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }
}

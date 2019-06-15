package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.MarkEdtActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import cz.msebera.android.httpclient.Header;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2019/2/16 14:17
 */

public class MarkEdtActivityPresenter {
    private MarkEdtActivityView mMarkEdtActivityView;

    public MarkEdtActivityPresenter(MarkEdtActivityView mMarkEdtActivityView) {
        this.mMarkEdtActivityView = mMarkEdtActivityView;
    }

    public void putMarker() {
        mMarkEdtActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_SERVICE_NOT_SAVE, mMarkEdtActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mMarkEdtActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mMarkEdtActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mMarkEdtActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo userVo = gson.fromJson(result, CallBackVo.class);
                if (userVo.getCode() == 0) {
                    mMarkEdtActivityView.excuteSuccessCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mMarkEdtActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mMarkEdtActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mMarkEdtActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

    public void markDelete() {
        mMarkEdtActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_SERVICE_NOT_DELETE, mMarkEdtActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mMarkEdtActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mMarkEdtActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mMarkEdtActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo userVo = gson.fromJson(result, CallBackVo.class);
                if (userVo.getCode() == 0) {
                    mMarkEdtActivityView.excuteSuccessCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mMarkEdtActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mMarkEdtActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mMarkEdtActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }
}

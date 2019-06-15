package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.FindPasswordActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import cz.msebera.android.httpclient.Header;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/4/2 16:33
 * $DESE$
 */
public class FindPasswordActivityPresenter {
    private FindPasswordActivityView mFindPasswordActivityView;

    public FindPasswordActivityPresenter(FindPasswordActivityView mFindPasswordActivityView) {
        this.mFindPasswordActivityView = mFindPasswordActivityView;
    }

    public void toFind() {
        mFindPasswordActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_FIND_PASS, mFindPasswordActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mFindPasswordActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mFindPasswordActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mFindPasswordActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo userVo = gson.fromJson(result, CallBackVo.class);
                if (userVo.getCode() == 0) {
                    mFindPasswordActivityView.excuteSuccessCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mFindPasswordActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mFindPasswordActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mFindPasswordActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }



    public void getPhoneSMS() {
        mFindPasswordActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_SENDSMS, mFindPasswordActivityView.getParamentersSend(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mFindPasswordActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mFindPasswordActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mFindPasswordActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo userVo = gson.fromJson(result, CallBackVo.class);
                if (userVo.getCode() == 0) {
                    mFindPasswordActivityView.excuteSuccessGetCodeCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mFindPasswordActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mFindPasswordActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mFindPasswordActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

}

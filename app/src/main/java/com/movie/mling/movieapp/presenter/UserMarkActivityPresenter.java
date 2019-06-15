package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.UserMarkActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.UserMarkBean;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import cz.msebera.android.httpclient.Header;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2019/2/16 14:32
 */

public class UserMarkActivityPresenter {
    private UserMarkActivityView mUserMarkActivityView;

    public UserMarkActivityPresenter(UserMarkActivityView mUserMarkActivityView) {
        this.mUserMarkActivityView = mUserMarkActivityView;
    }


    public void getMarkList() {
        mUserMarkActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_SERVICE_NOT, mUserMarkActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mUserMarkActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mUserMarkActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mUserMarkActivityView.closeProgress();
                Gson gson = new Gson();
                UserMarkBean userVo = gson.fromJson(result, UserMarkBean.class);
                if (userVo.getCode() == 0) {
                    mUserMarkActivityView.excuteSuccessCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mUserMarkActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mUserMarkActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mUserMarkActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }
}

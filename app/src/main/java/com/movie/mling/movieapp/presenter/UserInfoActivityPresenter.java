package com.movie.mling.movieapp.presenter;

import android.os.Environment;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.UserInfoActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.UserVo;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import java.io.File;

import cz.msebera.android.httpclient.Header;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2018/3/1 19:54
 */

public class UserInfoActivityPresenter {
    private UserInfoActivityView mUserInfoActivityView;

    public UserInfoActivityPresenter(UserInfoActivityView mUserInfoActivityView) {
        this.mUserInfoActivityView = mUserInfoActivityView;
    }

    public void getUserInfo() {
        mUserInfoActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_INFO, mUserInfoActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mUserInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mUserInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mUserInfoActivityView.closeProgress();
                Gson gson = new Gson();
                UserVo userVo = gson.fromJson(result, UserVo.class);
                if (userVo.getCode() == 0) {
                    mUserInfoActivityView.excuteSuccessCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mUserInfoActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mUserInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mUserInfoActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

    public void getUserMsgApply() {
        mUserInfoActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_MSG_APPLY, mUserInfoActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mUserInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mUserInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mUserInfoActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo userVo = gson.fromJson(result, CallBackVo.class);
                if (userVo.getCode() == 0) {
                    mUserInfoActivityView.excuteSuccessHHCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mUserInfoActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mUserInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mUserInfoActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

    public void downloadFilePDF() {
        mUserInfoActivityView.showProgress();
        final String path = Environment.getExternalStorageDirectory() +
                "/download/" + "sermml.pdf";
        HttpUtil.get("http://cdn.23so.cn/web/upload/201804/12/8484370a6d472dd322f4ecf12ff54958565fe3fd.pdf", new FileAsyncHttpResponseHandler(new File(path)) {
            @Override
            public void onStart() {
                super.onStart();
                mUserInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mUserInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                mUserInfoActivityView.excuteSuccessPDFCallBack(path);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable error, File file) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mUserInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mUserInfoActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }
}

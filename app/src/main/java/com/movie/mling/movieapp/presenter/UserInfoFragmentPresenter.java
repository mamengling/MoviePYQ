package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.iactivityview.UserInfoFragmentView;
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
 * 创建时间 ：2018/2/27 10:57
 * $DESE$
 */
public class UserInfoFragmentPresenter {

    private UserInfoFragmentView mUserInfoFragmentView;

    public UserInfoFragmentPresenter(UserInfoFragmentView mUserInfoFragmentView) {
        this.mUserInfoFragmentView = mUserInfoFragmentView;
    }

    public void postSaveUserInfo() {
        mUserInfoFragmentView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_SAVE, mUserInfoFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mUserInfoFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mUserInfoFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mUserInfoFragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo userVo = gson.fromJson(result, CallBackVo.class);
                if (userVo.getCode() == 0) {
                    mUserInfoFragmentView.excuteSuccessCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mUserInfoFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mUserInfoFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mUserInfoFragmentView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

    public void postSaveImage(RequestParams params) {
        mUserInfoFragmentView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_SAVE_IMAGE,params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mUserInfoFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mUserInfoFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mUserInfoFragmentView.closeProgress();
//                Gson gson = new Gson();
//                UserVo userVo = gson.fromJson(result, UserVo.class);
//                if (userVo.getCode() == 0) {
//                    mUserInfoFragmentView.excuteSuccessCallBack(userVo);
//                } else {
//                    CallBackVo mCallBackVo = new CallBackVo();
//                    mCallBackVo.setCode(userVo.getCode());
//                    mCallBackVo.setMessage(userVo.getMessage());
//                    mCallBackVo.setData(null);
//                    mUserInfoFragmentView.excuteFailedCallBack(mCallBackVo);
//                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mUserInfoFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mUserInfoFragmentView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

    public void postUserInfo() {
        mUserInfoFragmentView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_INFO, mUserInfoFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mUserInfoFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mUserInfoFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mUserInfoFragmentView.closeProgress();
                Gson gson = new Gson();
                UserVo userVo = gson.fromJson(result, UserVo.class);
                if (userVo.getCode() == 0) {
                    mUserInfoFragmentView.excuteSuccessUserInfoCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mUserInfoFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mUserInfoFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mUserInfoFragmentView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

    public void getPhoneSMS() {
        mUserInfoFragmentView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_SENDSMS, mUserInfoFragmentView.getParamentersSend(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mUserInfoFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mUserInfoFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mUserInfoFragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo userVo = gson.fromJson(result, CallBackVo.class);
                if (userVo.getCode() == 0) {
                    mUserInfoFragmentView.excuteSuccessGetCodeCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mUserInfoFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mUserInfoFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mUserInfoFragmentView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }
}

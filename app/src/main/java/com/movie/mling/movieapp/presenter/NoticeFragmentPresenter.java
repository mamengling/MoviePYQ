package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.NoticeFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MsgNoticeBean;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import cz.msebera.android.httpclient.Header;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2018/3/20 22:41
 */

public class NoticeFragmentPresenter {
    private NoticeFragmentView mNoticeFragmentView;

    public NoticeFragmentPresenter(NoticeFragmentView mNoticeFragmentView) {
        this.mNoticeFragmentView = mNoticeFragmentView;
    }

    public void getMsgList() {
        mNoticeFragmentView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_MSG_LIST, mNoticeFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mNoticeFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mNoticeFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mNoticeFragmentView.closeProgress();
                Gson gson = new Gson();
                MsgNoticeBean userVo = gson.fromJson(result, MsgNoticeBean.class);
                if (userVo.getCode() == 0) {
                    mNoticeFragmentView.excuteSuccessCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mNoticeFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mNoticeFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mNoticeFragmentView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

    public void getMsgDo() {
        mNoticeFragmentView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_MSG_DO, mNoticeFragmentView.getParamentersDo(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mNoticeFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mNoticeFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mNoticeFragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo userVo = gson.fromJson(result, CallBackVo.class);
                if (userVo.getCode() == 0) {
                    mNoticeFragmentView.excuteSuccessDoCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mNoticeFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mNoticeFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mNoticeFragmentView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }
}

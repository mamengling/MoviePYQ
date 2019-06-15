package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.ChangeWorkActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.WorkBean;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import cz.msebera.android.httpclient.Header;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/15 13:53
 * $DESE$
 */
public class ChangeWorkActivityPresenter {
    private ChangeWorkActivityView mChangeWorkActivityView;

    public ChangeWorkActivityPresenter(ChangeWorkActivityView mChangeWorkActivityView) {
        this.mChangeWorkActivityView = mChangeWorkActivityView;
    }

    public void getUserWorkList() {
        mChangeWorkActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_CATE, mChangeWorkActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mChangeWorkActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mChangeWorkActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mChangeWorkActivityView.closeProgress();
                Gson gson = new Gson();
                WorkBean userVo = gson.fromJson(result, WorkBean.class);
                if (userVo.getCode() == 0) {
                    mChangeWorkActivityView.excuteSuccessCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mChangeWorkActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mChangeWorkActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mChangeWorkActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }
}

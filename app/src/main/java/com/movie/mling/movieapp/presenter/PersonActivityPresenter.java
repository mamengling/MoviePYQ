package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.PersonActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.NearBean;
import com.movie.mling.movieapp.mode.bean.PersonBean;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import cz.msebera.android.httpclient.Header;

/**
 * Created by MLing on 2018/4/17 0017.
 */

public class PersonActivityPresenter {
    private PersonActivityView mPersonActivityView;

    public PersonActivityPresenter(PersonActivityView mPersonActivityView) {
        this.mPersonActivityView = mPersonActivityView;
    }


    public void getNearList() {
        mPersonActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_NEAR_USER, mPersonActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mPersonActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mPersonActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString() + "?" + mPersonActivityView.getParamenters());
                mPersonActivityView.closeProgress();
                Gson gson = new Gson();
                PersonBean userVo = gson.fromJson(result, PersonBean.class);
                if (userVo.getCode() == 0) {
                    mPersonActivityView.excuteSuccessBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mPersonActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mPersonActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mPersonActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

}

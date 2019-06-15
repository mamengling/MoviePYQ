package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.ActorInfoActivityView;
import com.movie.mling.movieapp.iactivityview.SearchActorActivityView;
import com.movie.mling.movieapp.mode.bean.ActorBean;
import com.movie.mling.movieapp.mode.bean.ActorInfoBean;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import cz.msebera.android.httpclient.Header;

/**
 * Created by MLing on 2018/5/16 0016.
 */

public class SearchActorActivityPresenter {
    private SearchActorActivityView mSearchActorActivityView;

    public SearchActorActivityPresenter(SearchActorActivityView mSearchActorActivityView) {
        this.mSearchActorActivityView = mSearchActorActivityView;
    }


    public void getSearchList(String url) {
        mSearchActorActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + url, mSearchActorActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mSearchActorActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mSearchActorActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mSearchActorActivityView.closeProgress();
                Gson gson = new Gson();
                ActorBean userVo = gson.fromJson(result, ActorBean.class);
                if (userVo.getCode() == 0) {
                    mSearchActorActivityView.excuteSuccessCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mSearchActorActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mSearchActorActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mSearchActorActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }


}

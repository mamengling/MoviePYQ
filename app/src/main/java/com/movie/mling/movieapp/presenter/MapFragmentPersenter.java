package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.MapFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MapMovieBean;
import com.movie.mling.movieapp.mode.bean.NearBean;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import cz.msebera.android.httpclient.Header;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/9 09:46
 * $DESE$
 */
public class MapFragmentPersenter {
    private MapFragmentView mMapFragmentView;

    public MapFragmentPersenter(MapFragmentView mMapFragmentView) {
        this.mMapFragmentView = mMapFragmentView;
    }

    public void getNearList() {
        mMapFragmentView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_NEAR, mMapFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mMapFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mMapFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString() + "?" + mMapFragmentView.getParamenters());
                mMapFragmentView.closeProgress();
                Gson gson = new Gson();
                NearBean userVo = gson.fromJson(result, NearBean.class);
                if (userVo.getCode() == 0) {
                    mMapFragmentView.excuteSuccessGetListCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mMapFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mMapFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mMapFragmentView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

    public void getMovieList() {
        mMapFragmentView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_LOCATION_MOVIE_LIST, mMapFragmentView.getMovieParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mMapFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mMapFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mMapFragmentView.closeProgress();
                Gson gson = new Gson();
                MapMovieBean userVo = gson.fromJson(result, MapMovieBean.class);
                if (userVo.getCode() == 0) {
                    mMapFragmentView.excuteSuccessMovieListCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mMapFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mMapFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mMapFragmentView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }
}

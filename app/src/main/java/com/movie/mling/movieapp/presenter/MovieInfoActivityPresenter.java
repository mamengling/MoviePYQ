package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.MovieInfoActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MovieInfoBean;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import cz.msebera.android.httpclient.Header;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/9 12:39
 * $DESE$
 */
public class MovieInfoActivityPresenter {
    private MovieInfoActivityView mMovieInfoActivityView;

    public MovieInfoActivityPresenter(MovieInfoActivityView mMovieInfoActivityView) {
        this.mMovieInfoActivityView = mMovieInfoActivityView;
    }

    public void getMovieInfo() {
        mMovieInfoActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_MOVIE_INFO, mMovieInfoActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mMovieInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mMovieInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mMovieInfoActivityView.closeProgress();
                Gson gson = new Gson();
                MovieInfoBean userVo = gson.fromJson(result, MovieInfoBean.class);
                if (userVo.getCode() == 0) {
                    mMovieInfoActivityView.excuteSuccessCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mMovieInfoActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mMovieInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mMovieInfoActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }
    public void getMovieFavInfo() {
        mMovieInfoActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_COLLECT_MOVICE, mMovieInfoActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mMovieInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mMovieInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mMovieInfoActivityView.closeProgress();
                Gson gson = new Gson();
                MovieInfoBean userVo = gson.fromJson(result, MovieInfoBean.class);
                if (userVo.getCode() == 0) {
                    mMovieInfoActivityView.excuteSuccessFavCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mMovieInfoActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mMovieInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mMovieInfoActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

    public void getMovieDigInfo() {
        mMovieInfoActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_SERVICE_DIG, mMovieInfoActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mMovieInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mMovieInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mMovieInfoActivityView.closeProgress();
                Gson gson = new Gson();
                MovieInfoBean userVo = gson.fromJson(result, MovieInfoBean.class);
                if (userVo.getCode() == 0) {
                    mMovieInfoActivityView.excuteSuccessDigCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mMovieInfoActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mMovieInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mMovieInfoActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }
    public void getMoviePaoInfo() {
        mMovieInfoActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_SERVICE_PAO, mMovieInfoActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mMovieInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mMovieInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mMovieInfoActivityView.closeProgress();
                Gson gson = new Gson();
                MovieInfoBean userVo = gson.fromJson(result, MovieInfoBean.class);
                if (userVo.getCode() == 0) {
                    mMovieInfoActivityView.excuteSuccessPaoCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mMovieInfoActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mMovieInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mMovieInfoActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }
}

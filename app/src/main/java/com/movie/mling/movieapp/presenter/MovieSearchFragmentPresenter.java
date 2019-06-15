package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.SearchSuccessActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MovieBean;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import cz.msebera.android.httpclient.Header;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/2 09:55
 * $DESE$
 */
public class MovieSearchFragmentPresenter {
    private SearchSuccessActivityView mMovieListFragmentView;

    public MovieSearchFragmentPresenter(SearchSuccessActivityView mMovieListFragmentView) {
        this.mMovieListFragmentView = mMovieListFragmentView;
    }

    public void getMovieList(String url) {
        mMovieListFragmentView.showProgress();
        HttpUtil.post(Constants.BASE_URL + url, mMovieListFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mMovieListFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mMovieListFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mMovieListFragmentView.closeProgress();
                Gson gson = new Gson();
                MovieBean userVo = gson.fromJson(result, MovieBean.class);
                if (userVo.getCode() == 0) {
                    mMovieListFragmentView.excuteSuccessCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mMovieListFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mMovieListFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mMovieListFragmentView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }
}

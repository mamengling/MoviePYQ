package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.base.MovieBannerBean;
import com.movie.mling.movieapp.iactivityview.MovieNewListFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MovieBean;
import com.movie.mling.movieapp.mode.bean.MovieNewBean;
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
public class MovieNewListFragmentPresenter {
    private MovieNewListFragmentView mMovieListFragmentView;

    public MovieNewListFragmentPresenter(MovieNewListFragmentView mMovieListFragmentView) {
        this.mMovieListFragmentView = mMovieListFragmentView;
    }

    /**
     * 该接口用于请求 首页含有banner的 数据
     *
     * @param userVoBanner
     * @param url
     */
    public void getMovieList(final MovieBannerBean userVoBanner, String url) {
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
                    MovieBean.DataBean.ListBean bean = new MovieBean.DataBean.ListBean();
                    bean.setViewType(1);
                    bean.setListBannaer(userVoBanner.getData().getList());
                    mMovieListFragmentView.excuteSuccessCallBack(bean, userVo);
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

    /**
     * 该接口 用于请求工作总结中的数据 不含banner
     *
     * @param url
     */
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
                MovieNewBean userVo = gson.fromJson(result, MovieNewBean.class);
                if (userVo.getCode() == 0) {
                    mMovieListFragmentView.excuteSuccessDataCallBack(null, userVo);
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

    /**
     * 该接口用于请求 首页面banner轮播图
     *
     * @param url
     */
    public void getMovieBannerList(String url) {
        mMovieListFragmentView.showProgress();
        HttpUtil.post(Constants.BASE_URL + url, mMovieListFragmentView.getParamentersBanner(), new AsyncHttpResponseHandler() {
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
                MovieBannerBean userVo = gson.fromJson(result, MovieBannerBean.class);
                if (userVo.getCode() == 0) {
                    mMovieListFragmentView.excuteSuccessCallBackBanner(userVo);
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

    /**
     * 该接口用于请求已跑记录页面含有banner的数据
     *
     * @param userVoBanner
     * @param url
     */
    public void getMovieDataList(final MovieBannerBean userVoBanner, String url) {
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
                MovieNewBean userVo = gson.fromJson(result, MovieNewBean.class);
                if (userVo.getCode() == 0) {
                    MovieNewBean.DataBean.ListBean bean = new MovieNewBean.DataBean.ListBean();
                    bean.setViewType(1);
                    bean.setListBannaer(userVoBanner.getData().getList());
                    mMovieListFragmentView.excuteSuccessDataCallBack(bean, userVo);
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

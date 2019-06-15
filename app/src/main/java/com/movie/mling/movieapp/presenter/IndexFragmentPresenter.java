package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.IndexFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.UserVo;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import cz.msebera.android.httpclient.Header;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/26 13:14
 * $DESE$
 */
public class IndexFragmentPresenter {

    private IndexFragmentView mIndexFragmentView;

    public IndexFragmentPresenter(IndexFragmentView mIndexFragmentView) {
        this.mIndexFragmentView = mIndexFragmentView;
    }

    public void getUserLogin() {
        mIndexFragmentView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_LOGIN, mIndexFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mIndexFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mIndexFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result,this.getRequestURI().toString());
                mIndexFragmentView.closeProgress();
                Gson gson = new Gson();
                UserVo userVo = gson.fromJson(result, UserVo.class);
                if (userVo.getCode() == 0) {
                    mIndexFragmentView.excuteSuccessCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mIndexFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mIndexFragmentView.closeProgress();
                JsonLog.printJson(TAG + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mIndexFragmentView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

}

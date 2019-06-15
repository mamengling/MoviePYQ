package com.movie.mling.movieapp.presenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.movie.mling.movieapp.iactivityview.ImageInfoActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.ImageInfoBean;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.HttpUtil;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.common.log.klog.JsonLog;

import cz.msebera.android.httpclient.Header;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/28 15:10
 * $DESE$
 */
public class ImageInfoActivityPresenter {

    private ImageInfoActivityView mImageInfoActivityView;

    public ImageInfoActivityPresenter(ImageInfoActivityView mImageInfoActivityView) {
        this.mImageInfoActivityView = mImageInfoActivityView;
    }

    public void getUserImageList() {
        mImageInfoActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_IMAGE_PIC, mImageInfoActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mImageInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mImageInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mImageInfoActivityView.closeProgress();
                Gson gson = new Gson();
                ImageInfoBean userVo = gson.fromJson(result, ImageInfoBean.class);
                if (userVo.getCode() == 0) {
                    mImageInfoActivityView.excuteSuccessGetCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mImageInfoActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mImageInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mImageInfoActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

    public void getSetImageFm() {
        mImageInfoActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_IMAGE_SET, mImageInfoActivityView.getParamentersSetImage(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mImageInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mImageInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mImageInfoActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo userVo = gson.fromJson(result, CallBackVo.class);
                if (userVo.getCode() == 0) {
                    mImageInfoActivityView.excuteSuccessSetCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mImageInfoActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mImageInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mImageInfoActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }

    public void getDeleteImage() {
        mImageInfoActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_IMAGE_DELETE, mImageInfoActivityView.getParamentersDeleteImage(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mImageInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mImageInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mImageInfoActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo userVo = gson.fromJson(result, CallBackVo.class);
                if (userVo.getCode() == 0) {
                    mImageInfoActivityView.excuteSuccessSetCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mImageInfoActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mImageInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mImageInfoActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }
    public void getPutImage() {
        mImageInfoActivityView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_USER_IMAGE_PIC_ADD, mImageInfoActivityView.getParamentersPutImage(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mImageInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mImageInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("putUserInfo", result);
                JsonLog.printJson("putUserInfo", result, this.getRequestURI().toString());
                mImageInfoActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo userVo = gson.fromJson(result, CallBackVo.class);
                if (userVo.getCode() == 0) {
                    mImageInfoActivityView.excuteSuccessPutCallBack(userVo);
                } else {
                    CallBackVo mCallBackVo = new CallBackVo();
                    mCallBackVo.setCode(userVo.getCode());
                    mCallBackVo.setMessage(userVo.getMessage());
                    mCallBackVo.setData(null);
                    mImageInfoActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("putUserInfo", "-----------------" + statusCode + "");
                LogUtil.i("putUserInfo", "-----------------" + error.getMessage() + "");
                mImageInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                CallBackVo mCallBackVo = new CallBackVo();
                mCallBackVo.setCode(404);
                mCallBackVo.setMessage("别着急哦~");
                mCallBackVo.setData(null);
                mImageInfoActivityView.excuteFailedCallBack(mCallBackVo);
            }
        });
    }
}

package com.movie.mling.movieapp.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.movie.mling.movieapp.iactivityview.ChangeLabelActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.LabelBean;
import com.movie.mling.movieapp.utils.common.AppMethod;

/**
 * Created by MLing on 2018/5/15 0015.
 */

public class ChangeLabelActivityPresenter{

    private ChangeLabelActivityView mChangeLabelActivityView;

    public ChangeLabelActivityPresenter(ChangeLabelActivityView mChangeLabelActivityView) {
        this.mChangeLabelActivityView = mChangeLabelActivityView;
    }

    public void getJsonString(Context mContext, String fileName) {
        String result = AppMethod.initJsonData(mContext, fileName);
        try {
            Gson gson = new Gson();
            LabelBean bean = gson.fromJson(result, LabelBean.class);
            mChangeLabelActivityView.excuteSuccessCallBack(bean);
        } catch (Exception e) {
            CallBackVo mCallBackVo = new CallBackVo();
            mCallBackVo.setCode(404);
            mCallBackVo.setMessage(e.getMessage());
            mCallBackVo.setData(null);
            mChangeLabelActivityView.excuteFailedCallBack(mCallBackVo);
        }

    }


}

package com.movie.mling.movieapp.iactivityview;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.ActorBean;
import com.movie.mling.movieapp.mode.bean.ActorInfoBean;
import com.movie.mling.movieapp.mode.bean.CallBackVo;

/**
 * Created by MLing on 2018/4/27 0027.
 */

public interface ActorInfoActivityView extends IBaseActView {
    public RequestParams getParamentersFeedback();

    void excuteSuccessCallBack(ActorInfoBean userVo);

    void excuteSuccessFavCallBack(CallBackVo mCallBackVo);

    void excuteSuccessFeedbackCallBack(CallBackVo mCallBackVo);
}

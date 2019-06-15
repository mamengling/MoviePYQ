package com.movie.mling.movieapp.iactivityview;

import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.ActorBean;
import com.movie.mling.movieapp.mode.bean.CallBackVo;

/**
 * Created by MLing on 2018/4/27 0027.
 */

public interface ActorFragmentView extends IBaseActView {
    void excuteSuccessCallBack(ActorBean userVo);
}

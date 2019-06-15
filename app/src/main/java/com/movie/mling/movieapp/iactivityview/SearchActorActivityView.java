package com.movie.mling.movieapp.iactivityview;

import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.ActorBean;

/**
 * Created by MLing on 2018/5/16 0016.
 */

public interface SearchActorActivityView extends IBaseActView {
    void excuteSuccessCallBack(ActorBean userVo);
}

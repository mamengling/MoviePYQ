package com.movie.mling.movieapp.iactivityview;

import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.ActorInfoBean;
import com.movie.mling.movieapp.mode.bean.LabelBean;

/**
 * Created by MLing on 2018/5/15 0015.
 */

public interface ChangeLabelActivityView extends IBaseActView{
    void excuteSuccessCallBack(LabelBean userVo);
}

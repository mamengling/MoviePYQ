package com.movie.mling.movieapp.iactivityview;

import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.NearBean;
import com.movie.mling.movieapp.mode.bean.PersonBean;

/**
 * Created by MLing on 2018/4/17 0017.
 */

public interface PersonActivityView extends IBaseActView{
    void excuteSuccessBack(PersonBean userVo);
}

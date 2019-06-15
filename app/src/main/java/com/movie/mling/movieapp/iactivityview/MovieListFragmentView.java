package com.movie.mling.movieapp.iactivityview;

import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.MovieBean;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/2 09:54
 * $DESE$
 */
public interface MovieListFragmentView extends IBaseActView {
    void excuteSuccessCallBack(MovieBean userVo);
}

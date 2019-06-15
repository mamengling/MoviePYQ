package com.movie.mling.movieapp.iactivityview;


import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.base.MovieBannerBean;
import com.movie.mling.movieapp.mode.bean.MovieBean;
import com.movie.mling.movieapp.mode.bean.MovieNewBean;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/2 09:54
 * $DESE$
 */
public interface MovieNewListFragmentView extends IBaseActView {
    public RequestParams getParamentersBanner();
    void excuteSuccessCallBack(MovieBean.DataBean.ListBean bean,MovieBean userVo);

    void excuteSuccessDataCallBack(MovieNewBean.DataBean.ListBean bean, MovieNewBean userVo);

    void excuteSuccessCallBackBanner(MovieBannerBean userVo);
}

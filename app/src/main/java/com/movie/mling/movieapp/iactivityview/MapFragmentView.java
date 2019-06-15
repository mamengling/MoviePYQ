package com.movie.mling.movieapp.iactivityview;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.MapMovieBean;
import com.movie.mling.movieapp.mode.bean.NearBean;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/9 09:44
 * $DESE$
 */
public interface MapFragmentView extends IBaseActView {
    /**
     * 获取参数
     *
     * @return
     */
    public RequestParams getMovieParamenters();
    /**
     * 成功回调
     */
    public void excuteSuccessGetListCallBack(NearBean mCallBackVo);
    /**
     * 成功回调
     */
    public void excuteSuccessMovieListCallBack(MapMovieBean mCallBackVo);
}

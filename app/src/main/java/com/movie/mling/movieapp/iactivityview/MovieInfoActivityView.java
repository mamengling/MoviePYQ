package com.movie.mling.movieapp.iactivityview;

import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.MovieInfoBean;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/9 12:39
 * $DESE$
 */
public interface MovieInfoActivityView extends IBaseActView {

    /**
     * 成功回调
     */
    public void excuteSuccessCallBack(MovieInfoBean mCallBackVo);
    /**
     * 成功回调
     */
    public void excuteSuccessFavCallBack(MovieInfoBean mCallBackVo);
    /**
     * 成功回调
     */
    public void excuteSuccessDigCallBack(MovieInfoBean mCallBackVo);
    /**
     * 成功回调
     */
    public void excuteSuccessPaoCallBack(MovieInfoBean mCallBackVo);
}

package com.movie.mling.movieapp.iactivityview;


import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/1 14:46
 * $DESE$
 */
public interface MineFragmentView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo userVo);
    void excuteSuccessAgainCallBack(CallBackVo userVo);
}

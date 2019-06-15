package com.movie.mling.movieapp.iactivityview;

import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.UserVo;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/28 11:12
 * $DESE$
 */
public interface AboutFragmentView extends IBaseActView {
    /**
     * 成功回调
     */
    public void excuteSuccessGetCallBack(UserVo mCallBackVo);
    /**
     * 成功回调
     */
    public void excuteSuccessPutCallBack(CallBackVo mCallBackVo);
}

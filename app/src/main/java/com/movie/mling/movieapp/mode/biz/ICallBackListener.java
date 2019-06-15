package com.movie.mling.movieapp.mode.biz;

import com.movie.mling.movieapp.mode.bean.CallBackVo;

/**
 * 说明：回调监听接口
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/6/9 17:03.
 */
public interface ICallBackListener{

    public void onSuccess(CallBackVo mCallBackVo);
    public void onFaild(CallBackVo mCallBackVo);

}

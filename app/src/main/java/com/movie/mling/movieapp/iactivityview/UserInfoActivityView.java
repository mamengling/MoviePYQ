package com.movie.mling.movieapp.iactivityview;


import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.UserVo;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2018/3/1 19:50
 */

public interface UserInfoActivityView extends IBaseActView {

    void excuteSuccessCallBack(UserVo mCallBackVo);

    void excuteSuccessHHCallBack(CallBackVo mCallBackVo);

    void excuteSuccessPDFCallBack(String mCallBackVo);
}

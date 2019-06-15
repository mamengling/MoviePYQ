package com.movie.mling.movieapp.iactivityview;


import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.UserVo;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/27 10:56
 * $DESE$
 */
public interface UserInfoFragmentView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo mCallBackVo);
    void excuteSuccessUserInfoCallBack(UserVo mCallBackVo);
    void excuteSuccessUserImageCallBack(UserVo mCallBackVo);
    public void excuteSuccessGetCodeCallBack(CallBackVo mCallBackVo);
    public RequestParams getParamentersSend();
}

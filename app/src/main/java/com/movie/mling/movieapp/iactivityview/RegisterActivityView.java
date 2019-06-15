package com.movie.mling.movieapp.iactivityview;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/19 11:21
 * $DESE$
 */
public interface RegisterActivityView extends IBaseActView{
    /**
     * 获取参数
     *
     * @return
     */
    public RequestParams getParamentersShow();
    /**
     * 获取参数
     *
     * @return
     */
    public RequestParams getParamentersSend();
    /**
     * 成功回调
     */
    public void excuteSuccessGetCodeCallBack(CallBackVo mCallBackVo);
    /**
     * 成功回调
     */
    public void excuteSuccessGetRegisterCallBack(CallBackVo mCallBackVo);
    /**
     * 成功回调
     */
    public void excuteSuccessGetChowCodeCallBack(CallBackVo mCallBackVo);
}

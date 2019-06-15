package com.movie.mling.movieapp.iactivityview;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/4/2 16:05
 * $DESE$
 */
public interface FindPasswordActivityView extends IBaseActView {
    /**
     * 获取参数
     *
     * @return
     */
    public RequestParams getParamentersSend();

    void excuteSuccessCallBack(CallBackVo mCallBackVo);


    /**
     * 成功回调
     */
    public void excuteSuccessGetCodeCallBack(CallBackVo mCallBackVo);
}

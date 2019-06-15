package com.movie.mling.movieapp.base;


import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.mode.bean.CallBackVo;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/8/2 10:38
 * $DESE$
 */
public interface IBaseActView {
    /**
     * 获取参数
     *
     * @return
     */
    public RequestParams getParamenters();

    /**
     * 显示操作进度
     */
    public void showProgress();

    /**
     * 关闭进度
     */
    public void closeProgress();

    /**
     * 失败回调
     */
    public void excuteFailedCallBack(CallBackVo mCallBackVo);
}

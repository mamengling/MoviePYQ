package com.movie.mling.movieapp.iactivityview;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/15 15:52
 * $DESE$
 */
public interface IndexActivityView extends IBaseActView {
    /**
     * 获取参数
     *
     * @return
     */
    public RequestParams getParamentersCount();
    void excuteSuccessCallBack(CallBackVo userVo);
    void excuteSuccessCountCallBack(CallBackVo userVo);
}

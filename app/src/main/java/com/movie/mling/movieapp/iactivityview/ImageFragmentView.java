package com.movie.mling.movieapp.iactivityview;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.ImageBean;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/28 13:45
 * $DESE$
 */
public interface ImageFragmentView extends IBaseActView {
    /**
     * 获取参数
     *
     * @return
     */
    public RequestParams getParamentersSetImage();

    /**
     * 获取参数
     *
     * @return
     */
    public RequestParams getParamentersAddImage();

    void excuteSuccessCallBack(ImageBean mCallBackVo);

    /**
     * 成功回调
     *
     * @return
     */
    void excuteSuccessSetCallBack(CallBackVo mCallBackVo);
}

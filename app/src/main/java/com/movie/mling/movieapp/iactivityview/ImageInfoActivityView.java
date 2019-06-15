package com.movie.mling.movieapp.iactivityview;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.ImageInfoBean;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/28 15:10
 * $DESE$
 */
public interface ImageInfoActivityView extends IBaseActView {
    /**
     * 获取参数
     *
     * @return
     */
    public RequestParams getParamentersPutImage();
    /**
     * 获取参数
     *
     * @return
     */
    public RequestParams getParamentersDeleteImage();
    /**
     * 获取参数
     *
     * @return
     */
    public RequestParams getParamentersSetImage();
    /**
     * 成功回调
     *
     * @return
     */
    void excuteSuccessGetCallBack(ImageInfoBean mCallBackVo);
    /**
     * 成功回调
     *
     * @return
     */
    void excuteSuccessSetCallBack(CallBackVo mCallBackVo);
    /**
     * 成功回调
     *
     * @return
     */
    void excuteSuccessPutCallBack(CallBackVo mCallBackVo);
}

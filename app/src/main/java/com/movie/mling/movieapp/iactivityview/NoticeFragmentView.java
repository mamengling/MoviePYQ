package com.movie.mling.movieapp.iactivityview;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MsgNoticeBean;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2018/3/20 22:40
 */

public interface NoticeFragmentView extends IBaseActView {
    /**
     * 获取参数
     *
     * @return
     */
    public RequestParams getParamentersDo();
    void excuteSuccessCallBack(MsgNoticeBean userVo);
    void excuteSuccessDoCallBack(CallBackVo mCallBackVo);
}

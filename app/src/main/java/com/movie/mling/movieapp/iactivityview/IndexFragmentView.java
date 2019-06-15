package com.movie.mling.movieapp.iactivityview;


import com.movie.mling.movieapp.base.IBaseActView;
import com.movie.mling.movieapp.mode.bean.UserVo;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/26 12:58
 * $DESE$
 */
public interface IndexFragmentView extends IBaseActView {
    /**
     * 成功回调
     */
    public void excuteSuccessCallBack(UserVo userVo);
}

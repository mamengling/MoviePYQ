package com.movie.mling.movieapp.mode.biz;


import java.util.Map;

/**
 * 说明：业务接口
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/6/9 17:03.
 */
public interface IBusiness {

    /**
     * 注册
     *
     * @param请求参数：封装为bean
     * @param mICallBackListener:回调接口
     */
    public void register(Map<String, String> obj, ICallBackListener mICallBackListener);

    /**
     * 登录
     *
     * @param obj:请求参数，可根据实际需求定义
     * @param mICallBackListener：回调接口
     */
    public void loginAct(Map<String, String> obj, ICallBackListener mICallBackListener);

    /**
     * 发送验证码
     *
     * @param obj:请求参数，可根据实际需求定义
     * @param mICallBackListener：回调接口
     */
    public void loginSendSms(Map<String, String> obj, ICallBackListener mICallBackListener);

    /**
     * 忘记密码
     *
     * @param obj:请求参数，可根据实际需求定义
     * @param mICallBackListener：回调接口
     */
    public void forgetPwd(Object obj, ICallBackListener mICallBackListener);

    /**
     * 意见反馈
     *
     * @param obj:请求参数，可根据实际需求定义
     * @param mICallBackListener：回调接口
     */
    public void feedBack(Object obj, ICallBackListener mICallBackListener);

    public void getData(Map<String, String> obj, ICallBackListener mICallBackListener);
}

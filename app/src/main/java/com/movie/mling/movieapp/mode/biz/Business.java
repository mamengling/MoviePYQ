package com.movie.mling.movieapp.mode.biz;


/**
 * 说明：业务接口实现类,主要逻辑及操作在这里实现
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/6/9 17:00.
 */

//public class Business implements IBusiness {
//
//    private static final String TAG = Business.class.getSimpleName();
//
//    @Override
//    public void register(Map<String, String> obj, final ICallBackListener mICallBackListener) {
//
//        IServiceAPI mIServiceAPI = HttpDao.getInstance().getIServiceAPI();
//        mIServiceAPI.register(obj)
//                // Subscriber前面执行的代码都是在I/O线程中运行
//                .subscribeOn(Schedulers.io())
//                // 操作observeOn之后操作主线程中运行.
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(HttpDao.getInstance().createSubscriber(mICallBackListener));
//
//    }
//
//    @Override
//    public void loginAct(Map<String, String> obj, ICallBackListener mICallBackListener) {
//
//    }
//
//    @Override
//    public void loginSendSms(Map<String, String> obj, ICallBackListener mICallBackListener) {
//
//    }
//
//    @Override
//    public void forgetPwd(Object obj, ICallBackListener mICallBackListener) {
//        // TODO
//    }
//
//    @Override
//    public void feedBack(Object obj, ICallBackListener mICallBackListener) {
//        // TODO
//    }
//
//    @Override
//    public void getData(Map<String,String> obj, ICallBackListener mICallBackListener) {
//        IServiceAPI mIServiceAPI =  HttpDao.getInstance().getIServiceAPI();
//        mIServiceAPI.register(obj)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(HttpDao.getInstance().createSubscriber(mICallBackListener));
//    }
//
//
//}

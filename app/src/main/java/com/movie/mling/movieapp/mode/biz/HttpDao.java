package com.movie.mling.movieapp.mode.biz;


/**
 * 说明：对 retrofit 以及 RXAndroid 中重复功能进行提取封装为公用模块
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/6/9 17:01.
 */
//public class HttpDao {
//
//    private static final String TAG = HttpDao.class.getSimpleName();
//    private static HttpDao mHttpDao = null;
//
//    private HttpDao() {
//    }
//
//    public static HttpDao getInstance() {
//        if (mHttpDao == null) {
//            mHttpDao = new HttpDao();
//        }
//        return mHttpDao;
//    }
//
//    /**
//     * 获取 IServiceAPI实列
//     *
//     * @return IServiceAPI
//     */
//    public IServiceAPI getIServiceAPI() {
//        // 使用自定义转换器
//        Gson mGson = new GsonBuilder()
//                .registerTypeAdapter(String.class, new DeserializerData())
//                .create();
//        Retrofit mRetrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
////                .addConverterFactory(MyGsonConverter.create())
//                .addConverterFactory(GsonConverterFactory.create(mGson))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        return mRetrofit.create(IServiceAPI.class);
//    }
//
//    /**
//     * 创建 Subscriber
//     *
//     * @param mICallBackListener
//     * @return Subscriber
//     */
//    public Subscriber createSubscriber(final ICallBackListener mICallBackListener) {
//        Subscriber mSubscriber = new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                Log.i(TAG, "[onCompleted]");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                JsonLog.printJson(TAG + "[onError]", e.getMessage(), "");
//                CallBackVo mCallBackVo = new CallBackVo();
//                mCallBackVo.setCode(404);
//                mCallBackVo.setMessage("别着急哦~");
//                mCallBackVo.setData(null);
//                mICallBackListener.onFaild(mCallBackVo);
//                return;
//            }
//
//            @Override
//            public void onNext(String s) {
//                JsonLog.printJson(TAG + "[onNext]", s, "");
//                Gson gosn = new Gson();
//                CallBackVo mCallBackVo = gosn.fromJson(s, CallBackVo.class);
//                LogUtil.i(mCallBackVo.getCode());
//                LogUtil.i(mCallBackVo.getMessage());
//                LogUtil.i(mCallBackVo.getData());
//                if (mCallBackVo.getCode() == 0) {
//                    mICallBackListener.onSuccess(mCallBackVo);
//                } else {
//                    mICallBackListener.onFaild(mCallBackVo);
//                }
//            }
//        };
//        return mSubscriber;
//    }
//}


package com.movie.mling.movieapp.mode.biz;


/**
 * Created by zhougang on 2016/10/19.
 */
//public class HttpManager {
//
//    //public static final String BASE_URL = "http://hltm-api.tomoya.cn";
//
//    //请求金融
//    public static final String BASE_URL = Constants.BASE_URL;
//    //短缓存有效期为1分钟
//    // public static final int CACHE_STALE_SHORT = 60;
//    //长缓存有效期为7天
//    public static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;
//    //public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=";
//    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
//    //  public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_LONG;
//    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
//    // public static final String CACHE_CONTROL_NETWORK = "max-age=0";
//
//    private static OkHttpClient mOkHttpClient;
//    private volatile static HttpManager INSTANCE;
//    private static HttpService sApiService;
//
//    //设置连接超时的值
//    private static final int TIMEOUT = 2000;
//
//    private HttpManager() {
//        initOkHttpClient();
//        initRetrofit();
//    }
//
//    private void initRetrofit() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(mOkHttpClient)
//                .addConverterFactory(CustomGsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        sApiService = retrofit.create(HttpService.class);
//    }
//
//
//    public static HttpManager getHttpManager() {
//        if (null == INSTANCE) {
//            synchronized (HttpManager.class) {
//                if (null == INSTANCE) {
//                    INSTANCE = new HttpManager();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    public HttpService getHttpService() {
//        return sApiService;
//    }
//
//    private void initOkHttpClient() {
//
////        日志输出拦截器
////        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
////        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
////        格式化的日志输出
//        HttpLoggingInterceptorM formattedLoggingInterceptor = new HttpLoggingInterceptorM(new LogInterceptor("OkHttp"));
//        formattedLoggingInterceptor.setLevel(HttpLoggingInterceptorM.Level.BODY);
//
//        if (mOkHttpClient == null) {
//            synchronized (HttpManager.class) {
//                if (mOkHttpClient == null) {
//                    mOkHttpClient = new OkHttpClient.Builder()
//                            .addInterceptor(formattedLoggingInterceptor)   // 通过配置拦截器 ：在转换成对象前对json字符串进行监控
//                            .retryOnConnectionFailure(true)
//                            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
//                            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
//                            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
//                            .build();
//                }
//            }
//        }
//
//    }
//}

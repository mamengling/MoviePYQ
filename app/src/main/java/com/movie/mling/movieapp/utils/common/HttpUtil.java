package com.movie.mling.movieapp.utils.common;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.utils.common.log.LogUtil;

/**
 * Created by MLing on 2016/7/20.
 */
public class HttpUtil {

    private static AsyncHttpClient httpclient = new AsyncHttpClient();

    static {
        httpclient.setTimeout(6000 * 10);

    }

    public static void get(String urlString, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象

    {
        LogUtil.i("HttpUtil", urlString);
        httpclient.get(urlString, res);
    }

    public static void get(String urlString, RequestParams params,
                           AsyncHttpResponseHandler res) // url里面带参数
    {
        LogUtil.i("HttpUtil", urlString + "?" + params.toString());
        httpclient.get(urlString, params, res);
    }


    public static void get(String urlString, JsonHttpResponseHandler res) // 不带参数，获取json对象或者数组
    {
        LogUtil.i("HttpUtil", urlString);
        httpclient.get(urlString, res);
    }

    public static void get(String urlString, RequestParams params,
                           JsonHttpResponseHandler res) // 带参数，获取json对象或者数组
    {
        LogUtil.i("HttpUtil", urlString + "?" + params.toString());
        httpclient.get(urlString, params, res);
    }

    public static void get(String uString, BinaryHttpResponseHandler bHandler) // 下载数据使用，会返回byte数据
    {
        LogUtil.i("HttpUtil", uString);
        httpclient.get(uString, bHandler);
    }

    public static void get(String uString, FileAsyncHttpResponseHandler bHandler) // 下载数据使用，会返回byte数据
    {
        LogUtil.i("HttpUtil", uString);
        httpclient.get(uString, bHandler);
    }

    public static void post(String uString,
                            JsonHttpResponseHandler bHandler) // post数据使用，会返回json数据
    {
        LogUtil.i("HttpUtil", uString);
        httpclient.post(uString, bHandler);
    }

    public static void post(String uString, RequestParams params,
                            AsyncHttpResponseHandler res) // post数据使用，返回普通的手
    {
        LogUtil.i("HttpUtil", uString + "?" + params.toString());
        httpclient.post(uString, params, res);
    }

    public static void post(String uString,
                            AsyncHttpResponseHandler res) // post数据使用，返回普通的手
    {
        LogUtil.i("HttpUtil", uString);
        httpclient.post(uString, res);
    }

    public static AsyncHttpClient getClient() {
        return httpclient;
    }
}

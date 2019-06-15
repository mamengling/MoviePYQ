package com.movie.mling.movieapp.utils.common;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * 作者：MLing on 2016/10/12 09:00
 * 邮箱：mamenglingkl1314@163.com
 */
public class FirstLocation {
    private static FirstLocation location;
    private Context mContext;
    private AMapLocationClient locationClient;

    public FirstLocation(Context mContext) {
        this.mContext = mContext;
        initLocation();
    }

    public static FirstLocation getInstance(Context context) {
        if (location == null) {
            location = new FirstLocation(context);
        }
        return location;
    }

    private AMapLocationClientOption initLocation() {
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置为高精度定位模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(5000);
        return mLocationOption;
    }

    public void InitLocation(AMapLocationListener locationListener) {
        //初始化client
        locationClient = new AMapLocationClient(mContext);
        //设置定位参数
        locationClient.setLocationOption(initLocation());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
        locationClient.startLocation();
    }


}

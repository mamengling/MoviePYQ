package com.movie.mling.movieapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.MapMovieListAdapter;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.iactivityview.MapFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MapMovieBean;
import com.movie.mling.movieapp.mode.bean.NearBean;
import com.movie.mling.movieapp.mould.MovieInfoActivity;
import com.movie.mling.movieapp.mould.UserInfoActivity;
import com.movie.mling.movieapp.presenter.MapFragmentPersenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/20 18:03
 * $DESE$
 */
public class MapNewFragment extends BaseFragment implements MapFragmentView,View.OnClickListener, LocationSource,
        AMapLocationListener,AMap.OnMarkerClickListener {
    private MapFragmentPersenter mMapFragmentPersenter;
    private MapView mapView;
    private LinearLayout line_location_message;
    private TextView location_city;
    private TextView location_content;
    private ImageView image_to_gaode;
    private RadioGroup radio_group;
    private RadioButton tv_location_ju;
    private RadioButton tv_location_person;
    private GridView grid_view;
    private MapMovieListAdapter mAdapter;
    private List<MapMovieBean.DataBean> mList = new ArrayList<>();
    private View viewHead;
    private View pickpic;
    private View rootView;
    private AMap aMap;
    private String keytype="100";

    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private int intentFlag;
    private String intentLng;
    private String intentLat;
    private String lat;
    private String lon;
    private NearBean.DataBean dataBean;
    private NearBean mCallBackVo;
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mMapFragmentPersenter = new MapFragmentPersenter(this);
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.fragment_new_map;
    }
    @Override
    protected void onCreateViewContent(View view, @Nullable Bundle savedInstanceState) {
        super.onCreateViewContent(view, savedInstanceState);
        mapView = view.findViewById(R.id.map_view);
        line_location_message = view.findViewById(R.id.line_location_message);
        mapView.onCreate(savedInstanceState);//必须要写
        init();
    }
    @Override
    protected void onCreateViewContent(View view) {
        rootView = view.findViewById(R.id.rootView);
        pickpic = getLayoutInflater().inflate(R.layout.register_pick_pic, null);
        view.findViewById(R.id.tv_location_on_view).setOnClickListener(this);
        view.findViewById(R.id.tv_location_person).setOnClickListener(this);
        view.findViewById(R.id.tv_location_ju).setOnClickListener(this);
        pickpic.findViewById(R.id.pic_photo).setOnClickListener(this);
        pickpic.findViewById(R.id.pic_tuku).setOnClickListener(this);
        pickpic.findViewById(R.id.pic_cancel).setOnClickListener(this);
        location_city = view.findViewById(R.id.location_city);
        location_content = view.findViewById(R.id.location_content);
        radio_group = view.findViewById(R.id.radio_group);
        tv_location_ju = view.findViewById(R.id.tv_location_ju);
        tv_location_person = view.findViewById(R.id.tv_location_person);
        view.findViewById(R.id.image_to_gaode).setOnClickListener(this);
        grid_view = view.findViewById(R.id.grid_view);
    }

    @Override
    protected void fromNetGetData() {
        mMapFragmentPersenter.getNearList();
    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    protected void setListener() {
        mAdapter = new MapMovieListAdapter(mList, getActivity());
        grid_view.setAdapter(mAdapter);
        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String, String> map = new HashMap<>();
                map.put("movieID", mList.get(i).getId());
                ActivityAnim.intentActivity(getActivity(), MovieInfoActivity.class, map);
            }
        });
    }
    /**
     * * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        setUpMap();
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.mipmap.location_marker));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // aMap.setMyLocationType()
    }



    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_NEAR);
        params.put("token", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_TOKEN, ""));
        params.put("keytype", keytype);
        params.put("lng", (intentFlag == 101) ? intentLng + "" : lon + "");
        params.put("lat", (intentFlag == 101) ? intentLat + "" : lat + "");
        return params;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {

    }

    @Override
    public RequestParams getMovieParamenters() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_LOCATION_MOVIE_LIST);
        params.put("token", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_TOKEN, ""));
        params.put("lid", dataBean.getId());
        return params;
    }

    @Override
    public void excuteSuccessGetListCallBack(NearBean mCallBackVo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().size() > 0) {
            this.mCallBackVo = mCallBackVo;
            uiHandler.sendEmptyMessage(101);
        } else {
//            listMarker.clear();
            aMap.clear();
        }
    }

    @Override
    public void excuteSuccessMovieListCallBack(MapMovieBean mCallBackVo) {
        line_location_message.setVisibility(View.VISIBLE);
        location_city.setText(dataBean.getCity());
        location_content.setText(dataBean.getLname());
        if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().size() > 0) {
            mList.clear();
            mList.addAll(mCallBackVo.getData());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_location_ju:
                keytype = "100";
                LogUtil.i("huoqu", "剧剧剧剧剧剧剧剧剧剧剧剧剧");
                mMapFragmentPersenter.getNearList();
                break;
            case R.id.tv_location_person:
                keytype = "1";
                LogUtil.i("huoqu", "人人人人人人人人人人人人人");
                mMapFragmentPersenter.getNearList();
                break;
        }
    }
    /**
     * 跳转线程
     */
    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 101:
//                    setIconMaeker(mCallBackVo);
                    line_location_message.setVisibility(View.GONE);
                    break;
            }
        }
    };
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                lat = aMapLocation.getLatitude() + "";
                lon = aMapLocation.getLongitude() + "";
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode()+ ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr",errText);
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getActivity());
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mLocationOption.setOnceLocation(false);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }
    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }


    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        dataBean = (NearBean.DataBean) marker.getObject();
//        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_change_location));
//        marker.setIcon(bitmapDescriptor);
//        for (int i = 0; i < listMarker.size(); i++) {
//            NearBean.DataBean item = (NearBean.DataBean) listMarker.get(i).getObject();
//            if (!TextUtils.equals(dataBean.getId(), item.getId()) && !TextUtils.equals(dataBean.getId(), intentLid)) {
//                BitmapDescriptor bit = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.mine_film_icon));
//                listMarker.get(i).setIcon(bit);
//            }
//        }
        if (TextUtils.equals("100", dataBean.getKeytype())) {
            mMapFragmentPersenter.getMovieList();
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("user_uid", dataBean.getId());
            ActivityAnim.intentActivity(getActivity(), UserInfoActivity.class, map);
        }
        return false;
    }
}

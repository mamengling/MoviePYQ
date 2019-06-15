package com.movie.mling.movieapp.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.Projection;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.MapMovieListAdapter;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.iactivityview.MapFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MapMovieBean;
import com.movie.mling.movieapp.mode.bean.NearBean;
import com.movie.mling.movieapp.mould.LoginActivity;
import com.movie.mling.movieapp.mould.MovieInfoActivity;
import com.movie.mling.movieapp.mould.NoticeMessageActivity;
import com.movie.mling.movieapp.mould.UserInfoActivity;
import com.movie.mling.movieapp.presenter.MapFragmentPersenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.DensityUtil;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.CircleImageView;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/26 10:29
 * $DESE$
 */
public class MapFragment extends BaseFragment implements MapFragmentView, AMap.OnMarkerClickListener, View.OnClickListener, LocationSource, AMapLocationListener, AMap.OnCameraChangeListener {
    private MapFragmentPersenter mMapFragmentPersenter;
    private MapView mapView;
    private LinearLayout line_location_message;
    private LinearLayout line_list;
    private TextView location_city;
    private TextView location_content;
    private ImageView image_to_gaode;
    private TextView tv_location_ju;
    private TextView tv_location_person;
    private GridView grid_view;
    private AMap aMap;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private static final int FILL_COLOR = Color.argb(10, 0, 0, 180);
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    private OnLocationChangedListener mListener;
    private String lat;
    private String lon;
    private String keytype = "100";
    private String intentLng;
    private String intentLat;
    private String intentTitle;
    private int intentFlag;
    private String locatiuonid;
    private ArrayList<Marker> listMarker = new ArrayList();
    private ArrayList<Marker> listIntentMarker = new ArrayList();
    private String intentLid;
    private NearBean.DataBean dataBean;
    private MapMovieListAdapter mAdapter;
    private List<MapMovieBean.DataBean> mList = new ArrayList<>();
    private String intentCity;
    private String intentLname;
    private String intentMtitle;
    private String intentMid;
    private String intentleixing;
    private List<MapMovieBean.DataBean> mListItem;
    private Bitmap bitmap;
    private View viewHead;
    private View pickpic;
    private CircleImageView iv_head;
    private NearBean mCallBackVo;
    private AMapLocation mMapLocation;
    private boolean isZoomFlag = true;
    private PopupWindow popupWindow;
    private View rootView;
    private LatLng latLng;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mMapFragmentPersenter = new MapFragmentPersenter(this);
        titleBar.setVisibility(View.GONE);
        intentLng = getActivity().getIntent().getStringExtra("intentLng");
        intentLat = getActivity().getIntent().getStringExtra("intentLat");
        intentTitle = getActivity().getIntent().getStringExtra("intentTitle");
        intentLid = getActivity().getIntent().getStringExtra("intentLid");
        intentCity = getActivity().getIntent().getStringExtra("intentCity");
        intentLname = getActivity().getIntent().getStringExtra("intentLname");
        intentMtitle = getActivity().getIntent().getStringExtra("intentMtitle");
        intentMid = getActivity().getIntent().getStringExtra("intentMid");
        intentleixing = getActivity().getIntent().getStringExtra("intentleixing");
        intentFlag = getActivity().getIntent().getIntExtra("intentFlag", 0);
        lon = SharePreferenceUtil.getString(getActivity(), UserConfig.SYS_LONGITUDE, "");
        lat = SharePreferenceUtil.getString(getActivity(), UserConfig.SYS_LATITUDE, "");
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.fragment_map;
    }

    @Override
    protected void onCreateViewContent(View view) {
        rootView = view.findViewById(R.id.rootView);
        pickpic = getLayoutInflater().inflate(R.layout.register_pick_pic, null);
        view.findViewById(R.id.tv_location_on_view).setOnClickListener(this);
        tv_location_person = view.findViewById(R.id.tv_location_person);
        tv_location_ju = view.findViewById(R.id.tv_location_ju);
        line_list = view.findViewById(R.id.line_list);

        pickpic.findViewById(R.id.pic_photo).setOnClickListener(this);
        pickpic.findViewById(R.id.pic_tuku).setOnClickListener(this);
        pickpic.findViewById(R.id.pic_tuku_b).setOnClickListener(this);
        pickpic.findViewById(R.id.pic_cancel).setOnClickListener(this);

        location_city = view.findViewById(R.id.location_city);
        location_content = view.findViewById(R.id.location_content);
        view.findViewById(R.id.image_to_gaode).setOnClickListener(this);
        grid_view = view.findViewById(R.id.grid_view);
    }

    @Override
    protected void onCreateViewContent(View view, @Nullable Bundle savedInstanceState) {
        super.onCreateViewContent(view, savedInstanceState);
        mapView = view.findViewById(R.id.map_view);
        line_location_message = view.findViewById(R.id.line_location_message);
        viewHead = LayoutInflater.from(getActivity()).inflate(R.layout.pokemon_genius_icon, null);
        iv_head = viewHead.findViewById(R.id.iv_head);
        mapView.onCreate(savedInstanceState);//必须要写
        init();
    }

    /**
     * * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
//        LatLng latLng = new LatLng(36.646614,117.119713);//构造一个位置
//        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,11));
        setUpMap();
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        aMap.setOnCameraChangeListener(this);
        aMap.setOnMarkerClickListener(this);
        aMap.setOnMapTouchListener(new AMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.AXIS_SCROLL:
                        isZoomFlag = true;
                        line_location_message.setVisibility(View.GONE);
                        break;
                }
            }
        });
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
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
        aMap.getUiSettings().setScrollGesturesEnabled(true);
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.moveCamera(CameraUpdateFactory.zoomTo(11));
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
        tv_location_person.setOnClickListener(this);
        tv_location_ju.setOnClickListener(this);
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
        if (intentFlag == 101) {
            isZoomFlag = false;
            tv_location_ju.setVisibility(View.GONE);
            tv_location_person.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        getMapCenterPoint();
    }

    private void setIconMaeker(NearBean mCallBackVo) {
        for (int i = 0; i < listMarker.size(); i++) {
            listMarker.get(i).remove();
        }
        for (int i = 0; i < mCallBackVo.getData().size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(Double.parseDouble(mCallBackVo.getData().get(i).getLat()), Double.parseDouble(mCallBackVo.getData().get(i).getLng())));
            markerOptions.title(mCallBackVo.getData().get(i).getLname());
            markerOptions.visible(true);
            Marker marker = aMap.addMarker(markerOptions);
            if (TextUtils.equals("100", mCallBackVo.getData().get(i).getKeytype())) {
                if (mCallBackVo.getData().get(i).getFilm_count() > 1) {
                    BitmapDescriptor bit = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_more_ju_new));
                    marker.setIcon(bit);
                } else if (TextUtils.equals("1", mCallBackVo.getData().get(i).getFav())) {
                    BitmapDescriptor bit = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_xin));
                    marker.setIcon(bit);
                } else if (TextUtils.equals("1", mCallBackVo.getData().get(i).getPao())) {
                    BitmapDescriptor bit = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_mar_pao_sel));
                    marker.setIcon(bit);
                } else {
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_map_ju));
                    marker.setIcon(bitmapDescriptor);
                }

            } else {
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.mine_user_near));
                marker.setIcon(bitmapDescriptor);
            }
            marker.setObject(mCallBackVo.getData().get(i));
            listMarker.add(marker);
        }
        if (!TextUtils.isEmpty(intentLng) && !TextUtils.isEmpty(intentLat)) {
            setIconMaeker(Double.parseDouble(intentLng), Double.parseDouble(intentLat), intentLname);
        }
    }

    private void setIconMaeker(double lng, double lat, String title) {
        //修改地图的中心点位置
//        CameraPosition cp = aMap.getCameraPosition();
//        CameraPosition cpNew = CameraPosition.fromLatLngZoom(new LatLng(lat, lng), cp.zoom);
//        CameraUpdate cu = CameraUpdateFactory.newCameraPosition(cpNew);
//        aMap.moveCamera(cu);
//        LatLng latLng1=new LatLng(lat,lng);
//        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng1));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(lat, lng));
        markerOptions.title(title);
        markerOptions.visible(true);
        markerOptions.anchor(0.5f, 1);
        aMap.addMarker(markerOptions);
        Marker marker = aMap.addMarker(markerOptions);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.icon_change_location);
        marker.setIcon(bitmapDescriptor);
        NearBean.DataBean item = new NearBean.DataBean();
        item.setKeytype("100");
        item.setId(intentLid);
        item.setLname(title);
        item.setLat(intentLat);
        item.setLng(intentLng);
        item.setCity(intentCity);
        item.setLeixing(intentleixing);
        marker.setObject(item);
        listIntentMarker.add(marker);
        dataBean = (NearBean.DataBean) marker.getObject();
        mMapFragmentPersenter.getMovieList();
    }

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
    }

    @Override
    public void onStop() {
        super.onStop();
        mLocationClient.stopLocation();//停止定位
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端。
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_NEAR);
        params.put("token", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_TOKEN, ""));
        params.put("keytype", "100");
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
            for (int i = 0; i < listMarker.size(); i++) {
                listMarker.get(i).remove();
            }
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
                    setIconMaeker(mCallBackVo);
                    line_location_message.setVisibility(View.GONE);
                    break;
            }
        }
    };

    @Override
    public void excuteSuccessMovieListCallBack(MapMovieBean mCallBackVo) {
        line_location_message.setVisibility(View.VISIBLE);
        location_city.setText(dataBean.getCity());
        location_content.setText(dataBean.getLname());
        if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().size() > 0) {
            mList.clear();
            if (mCallBackVo.getData().size() >= 10) {
                line_list.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(getContext(), 162)));
            } else {
                line_list.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            mList.addAll(mCallBackVo.getData());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        isZoomFlag = false;
        LogUtil.i("marker", marker.getTitle() + "");
        dataBean = (NearBean.DataBean) marker.getObject();
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_change_location));
        marker.setIcon(bitmapDescriptor);
        for (int i = 0; i < listMarker.size(); i++) {
            NearBean.DataBean item = (NearBean.DataBean) listMarker.get(i).getObject();
            if (!TextUtils.equals(dataBean.getId(), item.getId())) {
                if (item.getFilm_count() > 1) {
                    BitmapDescriptor bit = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_more_ju_new));
                    listMarker.get(i).setIcon(bit);
                } else if (TextUtils.equals("1", item.getFav())) {
                    BitmapDescriptor bit = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_xin));
                    listMarker.get(i).setIcon(bit);
                } else if (TextUtils.equals("1", item.getPao())) {
                    BitmapDescriptor bit = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_mar_pao_sel));
                    listMarker.get(i).setIcon(bit);
                } else {
                    BitmapDescriptor bit = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_map_ju));
                    listMarker.get(i).setIcon(bit);
                }
            }
        }
        if (TextUtils.equals("100", dataBean.getKeytype())) {
            mMapFragmentPersenter.getMovieList();
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("user_uid", dataBean.getId());
            ActivityAnim.intentActivity(getActivity(), UserInfoActivity.class, map);
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_location_on_view:

                break;
            case R.id.image_to_gaode:
                initPop();
                break;
            case R.id.pic_photo:
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                invokingBD();
                break;
            case R.id.pic_tuku:
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                invokingGD();
                break;
            case R.id.pic_tuku_b:
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                invokingBD();
                break;
            case R.id.pic_cancel:
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                break;
            case R.id.tv_location_ju:
//                keytype = "100";
//                LogUtil.i("huoqu", "剧剧剧剧剧剧剧剧剧剧剧剧剧");
//                mMapFragmentPersenter.getNearList();
                location();
                break;
            case R.id.tv_location_person:
                if (!SharePreferenceUtil.getBoolean(getContext(), UserConfig.SYS_IS_LOGIN, false)) {
                    //去登陆页
                    ActivityAnim.intentActivity(getActivity(), LoginActivity.class, null);
                } else {
                    ActivityAnim.intentActivity(getActivity(), NoticeMessageActivity.class, null);
                }
                break;
        }
    }

    public void invokingBD() {
        //  com.baidu.BaiduMap这是高德地图的包名
        //调起百度地图客户端try {
        Intent intent = null;
        try {
            String uri = "intent://map/direction?origin=latlng:0,0|name:" + "" + "&destination=" + "需要导航的地址" + "&mode=drivingion=" + "城市" + "&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end";
            intent = Intent.getIntent(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (isInstallByread("com.baidu.BaiduMap")) {
            startActivity(intent); //启动调用
            Log.e("GasStation", "百度地图客户端已经安装");
        } else {
            Toast.makeText(mContext, "没有安装百度地图客户端", Toast.LENGTH_SHORT).show();
        }
    }

    public void invokingGD() {
//        Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("androidamap://navi?sourceApplication=朋影圈&" + "&dev=0"));
//        intent.setPackage("com.autonavi.minimap");
//        intent.setData(Uri.parse("androidamap://poi?sourceApplication=朋影圈&keywords=" + dataBean.getLname()));
//        if (isInstallByread("com.autonavi.minimap")) {
//            startActivity(intent);
//            Log.e("GasStation", "高德地图客户端已经安装");
//        } else {
//            Toast.makeText(mContext, "没有安装高德地图客户端", Toast.LENGTH_SHORT).show();
//        }
        try {
            Intent intentTo = Intent.getIntent("androidamap://route?sourceApplication=softname&sname=我的位置&dlat=" + dataBean.getLat() + "&dlon=" + dataBean.getLng() + "&dname=" + dataBean.getLname() + "&dev=0&m=0&t=1");
            if (isInstallByread("com.autonavi.minimap")) {
                startActivity(intentTo);
                Log.e("TAG", "高德地图客户端已经安装");
            } else {
                Toast.makeText(mContext, "没有安装高德地图客户端", Toast.LENGTH_SHORT).show();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否安装目标应用
     *
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(getActivity());
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mLocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);
            //设置是否只定位一次,默认为false
            mLocationOption.setOnceLocation(false);
            //设置是否强制刷新WIFI，默认为强制刷新start_image.png.png
            mLocationOption.setWifiActiveScan(true);
            //设置是否允许模拟位置,默认为false，不允许模拟位置
            mLocationOption.setMockEnable(false);
            //设置定位间隔,单位毫秒,默认为2000ms
            mLocationOption.setInterval(5000);
            //设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mLocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                mMapLocation = aMapLocation;
                if (intentFlag != 101) {
                    LogUtil.i("intentFlag", "===============================");
                    mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                }
                lat = aMapLocation.getLatitude() + "";
                lon = aMapLocation.getLongitude() + "";
                latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                Log.e("pcw", "lat : " + lat + " lon : " + lon);
//                SharePreferenceUtil.setValue(mContext, UserConfig.SYS_LATITUDE, aMapLocation.getLatitude() + "");
//                SharePreferenceUtil.setValue(mContext, UserConfig.SYS_LONGITUDE, aMapLocation.getLongitude() + "");
                Log.e("pcw", "Country : " + aMapLocation.getCountry() + " province : " + aMapLocation.getProvince() + " City : " + aMapLocation.getCity() + " District : " + aMapLocation.getDistrict());
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }


    public void location() {
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
    }

    /**
     * by moos on 2017/09/05
     * func:获取屏幕中心的经纬度坐标
     *
     * @return
     */

    public LatLng getMapCenterPoint() {
        int left = mapView.getLeft();
        int top = mapView.getTop();
        int right = mapView.getRight();
        int bottom = mapView.getBottom();
        // 获得屏幕点击的位置
        int x = (int) (mapView.getX() + (right - left) / 2);
        int y = (int) (mapView.getY() + (bottom - top) / 2);
        Projection projection = aMap.getProjection();
        LatLng pt = projection.fromScreenLocation(new Point(x, y));
        lat = pt.latitude + "";
        lon = pt.longitude + "";
        Log.v("pcw", "lat666 : " + pt.latitude + " lon666 : " + pt.longitude);
        if (isZoomFlag) {
            mMapFragmentPersenter.getNearList();
        }
        return pt;
    }


    /**
     * 弹窗
     */
    public void initPop() {
        popupWindow = new PopupWindow(pickpic, DrawerLayout.LayoutParams.MATCH_PARENT,
                DrawerLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new poponDismissListener());
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.5f);
        popupWindow.setAnimationStyle(R.style.Animations_BottomPush);
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

    public class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            backgroundAlpha(1f);
        }

    }
}

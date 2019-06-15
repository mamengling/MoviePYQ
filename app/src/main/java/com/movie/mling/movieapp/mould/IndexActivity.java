package com.movie.mling.movieapp.mould;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.fragment.IndexFragment;
import com.movie.mling.movieapp.fragment.MapFragment;
import com.movie.mling.movieapp.fragment.PushNotificationFragment;
import com.movie.mling.movieapp.fragment.YanyuanFragment;
import com.movie.mling.movieapp.fragment.UserFragment;
import com.movie.mling.movieapp.fragment.ZiXuanFragment;
import com.movie.mling.movieapp.iactivityview.IndexActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.presenter.IndexActivityPersenter;
import com.movie.mling.movieapp.utils.common.AppManager;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.FirstLocation;
import com.movie.mling.movieapp.utils.common.MD5Utils;
import com.movie.mling.movieapp.utils.common.MainPermissionsUtils;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.CustomButtonDialog;
import com.movie.mling.movieapp.utils.widget.TitleBar;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/26 09:59
 * $DESE$
 */
public class IndexActivity extends BaseCompatActivity implements IndexActivityView {
    private IndexActivityPersenter mIndexActivityPersenter;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4,radioButtonZixun;
    private boolean mIsExit;
    private static final int BAIDU_READ_PHONE_STATE = 100;
    public static final String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String RECEIVE_BOOT_COMPLETED = Manifest.permission.RECEIVE_BOOT_COMPLETED;
    private static final String[] requestPermissions = {
            ACCESS_COARSE_LOCATION,
            ACCESS_FINE_LOCATION,
    };
    private int intentFlag;
    private Bundle bundle;
    private int intenttype;
    private String intentid;
    private String adType;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mIndexActivityPersenter = new IndexActivityPersenter(this);
        titleBar.setVisibility(View.GONE);

    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_index;
    }


    @Override
    protected void onCreateViewContent(View view) {
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        radioButton1 = (RadioButton) view.findViewById(R.id.radiobutton0);
        radioButton2 = (RadioButton) view.findViewById(R.id.radiobutton1);
        radioButton3 = (RadioButton) view.findViewById(R.id.radiobutton2);
        radioButton4 = (RadioButton) view.findViewById(R.id.radiobutton3);
        radioButtonZixun = (RadioButton) view.findViewById(R.id.radiobutton_zixun);
    }

    @Override
    protected void getExras() {
        intentFlag = getIntent().getIntExtra("intentFlag", 0);
        intenttype = getIntent().getIntExtra("intenttype", 0);
        intentid = getIntent().getStringExtra("intentid");
        adType = getIntent().getStringExtra("adType");
        bundle = getIntent().getExtras();
    }

    @Override
    protected void setListener() {
        LogUtil.i("JIGUANG", JPushInterface.getRegistrationID(this));
//        if (intentFlag == 101) {
//            toogleFragment(MapFragment.class);
//            radioButton2.setChecked(true);
//        } else
            if (intentFlag == 102) {
            toogleFragment(YanyuanFragment.class);
            radioButton3.setChecked(true);
        } else if (intentFlag == 104) {
            toogleFragment(UserFragment.class);
            radioButton4.setChecked(true);
        } else {
            toogleFragment(ZiXuanFragment.class);
            radioButtonZixun.setChecked(true);
        }
//        FirstLocation.getInstance(IndexActivity.this).InitLocation(mLocationListener);
        radioGroup.setOnCheckedChangeListener(new OnTabListener());
        comeFromPushIntentJump();
        if (intenttype == 202) {
            starWelcome();
        }
    }

    private void starWelcome() {
        if (TextUtils.equals("user", adType)) {
            Map<String, String> map = new HashMap<>();
            map.put("actorID", intentid);
            ActivityAnim.intentActivity(this, ActorInfoActivity.class, map);
        } else if (TextUtils.equals("user", adType)) {
            Map<String, String> map = new HashMap<>();
            map.put("movieID", intentid);
            ActivityAnim.intentActivity(this, MovieInfoActivity.class, map);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("loadUrl", "https://yingq.cc/news/info?id=" + intentid);
            map.put("title", "");
            ActivityAnim.intentActivity(this, WebviewDefulitActivity.class, map);
        }
    }

    @Override
    protected void fromNetGetData() {
        mIndexActivityPersenter.getMsgCount();
    }

    @Override
    protected void fromNotMsgReference() {

    }

    public void toogleFragment(Class<? extends Fragment> c) {
        FragmentManager manager = getSupportFragmentManager();
        String tag = c.getName();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(tag);

        if (fragment == null) {
            try {
                fragment = c.newInstance();
                // 替换时保留Fragment,以便复用
                transaction.add(R.id.content_frame, fragment, tag);
            } catch (Exception e) {
                // ignore
            }
        } else {
            // nothing
        }
        // 遍历存在的Fragment,隐藏其他Fragment
        List<Fragment> fragments = manager.getFragments();
        if (fragments != null)
            for (Fragment fm : fragments)
                if (!fm.equals(fragment))
                    transaction.hide(fm);

        transaction.show(fragment);
        transaction.commit();
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_UPDATE_LOCATION);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        String timestamp = AppMethod.getSecondTimestampTwo();
        params.put("timestamp", timestamp);
        params.put("lng", SharePreferenceUtil.getString(this, UserConfig.SYS_LONGITUDE, ""));
        params.put("lat", SharePreferenceUtil.getString(this, UserConfig.SYS_LATITUDE, ""));
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
    public RequestParams getParamentersCount() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_MSG_COUNT);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        return params;
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo userVo) {

    }

    @Override
    public void excuteSuccessCountCallBack(CallBackVo userVo) {

    }

    protected class OnTabListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radiobutton0:// 首页
                    toogleFragment(IndexFragment.class);
                    break;
                case R.id.radiobutton1:// 演员
                    toogleFragment(YanyuanFragment.class);
//                    toogleFragment(MapFragment.class);
//                    if (MainPermissionsUtils.checkPermissions(IndexActivity.this, requestPermissions)) {
//                        FirstLocation.getInstance(IndexActivity.this).InitLocation(mLocationListener);
//                        mIndexActivityPersenter.getUpdateLocation();
//                    } else {
//                        requestPermission(requestPermissions, 102);
//                    }

                    break;
                case R.id.radiobutton_zixun:// 咨询
                    toogleFragment(ZiXuanFragment.class);
                    break;
                case R.id.radiobutton2:// 通知
                    toogleFragment(PushNotificationFragment.class);
                    break;
                case R.id.radiobutton3:// 我的
                    if (!SharePreferenceUtil.getBoolean(IndexActivity.this, UserConfig.SYS_IS_LOGIN, false)) {
                        //去登陆页
                        ActivityAnim.intentActivity(IndexActivity.this, LoginActivity.class, null);
                    } else {
                        toogleFragment(UserFragment.class);
                    }
                    break;
            }
        }

    }

    /**
     * 定位监听
     */
    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                //解析定位结果
                String city = loc.getCity();
                SharePreferenceUtil.setValue(mContext, UserConfig.SYS_LOCATION_CITY, city);
                SharePreferenceUtil.setValue(mContext, UserConfig.SYS_LATITUDE, loc.getLatitude() + "");
                SharePreferenceUtil.setValue(mContext, UserConfig.SYS_LONGITUDE, loc.getLongitude() + "");
                Log.e("pcw", "當前经度" + loc.getLongitude() + "当前维度：" + loc.getLatitude() + "城市：" + city);
            } else {
                Toast.makeText(mContext, "定位失败，请打开位置权限", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    /**
     * 双击返回键退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                AppManager.getAppManager().AppExit(this);
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * 请求权限
     *
     * @param permissions 请求的权限
     * @param requestCode 请求权限的请求码
     */
    public void requestPermission(String[] permissions, int requestCode) {
        if (MainPermissionsUtils.checkPermissions(this, permissions)) {
            FirstLocation.getInstance(IndexActivity.this).InitLocation(mLocationListener);
            mIndexActivityPersenter.getUpdateLocation();
        } else {
            ActivityCompat.requestPermissions(this, permissions, requestCode);
        }
    }


    /**
     * 系统请求权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 102:
                if (MainPermissionsUtils.verifyPermissions(grantResults)) {
                    FirstLocation.getInstance(IndexActivity.this).InitLocation(mLocationListener);
                    mIndexActivityPersenter.getUpdateLocation();
                } else {
                    final CustomButtonDialog dialog = new CustomButtonDialog(this);
                    dialog.setText("提示信息?\n当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。");
                    dialog.setLeftButtonText("取消");
                    dialog.setLeftButtonTextColor(R.color.colorAccent);
                    dialog.setRightButtonText("确定");
                    dialog.setRightButtonTextColor(R.color.colorAccent);
                    dialog.setButtonListener(new CustomButtonDialog.OnButtonListener() {
                        @Override
                        public void onLeftButtonClick(CustomButtonDialog var1) {
                            dialog.cancel();
                        }

                        @Override
                        public void onRightButtonClick(CustomButtonDialog var1) {
                            dialog.cancel();
                            MainPermissionsUtils.startAppSettings(IndexActivity.this);
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("Indexactivity");
        //MobclickAgent.onResume(mContext); // BaseActivity中已经统一调用，此处无需再调用
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("Indexactivity");
        //MobclickAgent.onPause(mContext); // BaseActivity中已经统一调用，此处无需再调用
    }

    private void comeFromPushIntentJump() {
        if (bundle != null) {
            int comeFromPushIntent = bundle.getInt("comeFromPushIntent", 0);
            String comeFromPushIntentJSON = bundle.getString("comeFromPushIntentJSON");
            LogUtil.i("comeFromPushIntentJSON", comeFromPushIntent + "");
            LogUtil.i("comeFromPushIntentJSON", comeFromPushIntentJSON);
            if (comeFromPushIntent == 101) {
                try {
                    JSONObject object = new JSONObject(comeFromPushIntentJSON);
                    String message = object.optString("keytype");
                    LogUtil.i("comeFromPushIntentJSON", message);
                    switch (message) {
                        case "huhuan"://跳转至企业端 面试邀约-待面试页面
                            ActivityAnim.intentActivity(this, RequestMessageActivity.class, null);
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } else {
            LogUtil.i("comeFromPushIntentJSON", bundle);
        }
    }
}

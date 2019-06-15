package com.movie.mling.movieapp.mould;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.StartActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.InitBean;
import com.movie.mling.movieapp.presenter.StartActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.FirstLocation;
import com.movie.mling.movieapp.utils.common.MainPermissionsUtils;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.runtimepermissions.PermissionsManager;
import com.movie.mling.movieapp.utils.runtimepermissions.PermissionsResultAction;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.CustomButtonDialog;
import com.movie.mling.movieapp.utils.widget.TitleBar;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/6/15 16:38
 * $DESE$
 */
public class StartActivity extends BaseCompatActivity implements StartActivityView {
    private StartActivityPresenter mStartActivityPresenter;
    private static final int TIME = 1500;
    private static final int GO_HOME = 1003;
    private static final int GO_LOGIN = 1001;
    private static final int GO_GUIDE = 1002;
    private Jump_Handler handler;
    private ImageView imageView;
    private boolean hasLocation = false;
    private static final int BAIDU_READ_PHONE_STATE = 100;
    public static final String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String RECEIVE_BOOT_COMPLETED = Manifest.permission.RECEIVE_BOOT_COMPLETED;
    public static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String[] requestPermissions = {
            ACCESS_COARSE_LOCATION,
            ACCESS_FINE_LOCATION,
            READ_PHONE_STATE,
            RECEIVE_BOOT_COMPLETED,
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE
    };
    private InitBean.DataBean.AdBean imangs;


    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mStartActivityPresenter = new StartActivityPresenter(this);
        titleBar.setVisibility(View.GONE);
        handler = new Jump_Handler();
        requestPermissions();
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
                SharePreferenceUtil.setValue(mContext, UserConfig.SYS_LATITUDE, loc.getLatitude() + "39.916346083311495");
                SharePreferenceUtil.setValue(mContext, UserConfig.SYS_LONGITUDE, loc.getLongitude() + "116.40460491176925");
                Log.e("pcw", "當前经度" + loc.getLongitude() + "当前维度：" + loc.getLatitude() + "城市：" + city);
            } else {
                Toast.makeText(mContext, "定位失败，请打开位置权限", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @TargetApi(23)
    private void requestPermissions() {
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, requestPermissions, new PermissionsResultAction() {
            @Override
            public void onGranted() {
                jumpAty();
                FirstLocation.getInstance(StartActivity.this).InitLocation(mLocationListener);
            }

            @Override
            public void onDenied(String permission) {
                jumpAty();
            }
        });
    }

    /**
     * 请求权限
     *
     * @param permissions 请求的权限
     * @param requestCode 请求权限的请求码
     */
    public void requestPermission(String[] permissions, int requestCode) {
        if (MainPermissionsUtils.checkPermissions(this, permissions)) {
            SharePreferenceUtil.setValue(this, UserConfig.USER_DEVICE_IMEI, AppMethod.getDeviceIMEIOnley(this));
            FirstLocation.getInstance(this).InitLocation(mLocationListener);
            jumpAty();
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
                    SharePreferenceUtil.setValue(this, UserConfig.USER_DEVICE_IMEI, AppMethod.getDeviceIMEIOnley(this));
                    FirstLocation.getInstance(this).InitLocation(mLocationListener);
                    jumpAty();
                } else {
                    final CustomButtonDialog dialog = new CustomButtonDialog(StartActivity.this);
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
                            MainPermissionsUtils.startAppSettings(StartActivity.this);
                        }
                    });
                }
                break;
            default:
                FirstLocation.getInstance(this).InitLocation(mLocationListener);
                jumpAty();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
    }

    /**
     * activity跳转判断
     */
    private void jumpAty() {
//        if (SharePreferenceUtil.getBoolean(this, UserConfig.KEY_IS_FIRS_TIN, false)) {
        //去首页
//            if (SharePreferenceUtil.getBoolean(StartActivity.this, UserConfig.SYS_IS_LOGIN, false)) {
        handler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
        //去登陆页
//            } else {
//                handler.sendEmptyMessageDelayed(GO_LOGIN, TIME);
//            }
//        } else {
//            handler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
//        }
    }

    @Override
    protected int onCreateViewId() {
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        return R.layout.activity_start;
    }

    @Override
    protected void onCreateViewContent(View view) {
        imageView = view.findViewById(R.id.imageview);
    }

    @Override
    protected void getExras() {

    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void fromNetGetData() {
        mStartActivityPresenter.getInitConfig();
    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public void excuteSuccessCallBack(InitBean mCallBackVo) {
        if (mCallBackVo.getData() != null && mCallBackVo.getData().getAd() != null){
            imangs = mCallBackVo.getData().getAd();
        }
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_CONFIG_INIT);
        params.put("version", AppMethod.getVersionCode(this));
        params.put("os", "Android");
        return params;
    }

    @Override
    public void showProgress() {
//        MDialog.getInstance(this).showProgressDialog();
    }

    @Override
    public void closeProgress() {
//        MDialog.getInstance(this).closeProgressDialog();
    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(this).showToast(mCallBackVo.getMessage());
    }

    /**
     * 跳转线程
     */
    class Jump_Handler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_LOGIN:
                    Login();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    }

    /**
     * 跳转到主页
     */
    private void goHome() {
        ActivityAnim.intentActivity(this, IndexActivity.class, null);
        finish();
    }


    /**
     * 跳转到登录
     */
    private void Login() {
        ActivityAnim.intentActivity(this, LoginActivity.class, null);
        finish();
    }

    /**
     * 跳转到引导
     */
    private void goGuide() {
        Intent intent = new Intent(this, WelcomeImageActivity.class);
        intent.putExtra("images", imangs);
        ActivityAnim.intentActivity(this, intent);
        finish();
    }
}

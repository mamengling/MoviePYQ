package com.movie.mling.movieapp.fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.iactivityview.UserInfoFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.UserVo;
import com.movie.mling.movieapp.mould.ChangeWorkActivity;
import com.movie.mling.movieapp.presenter.UserInfoFragmentPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.FirstLocation;
import com.movie.mling.movieapp.utils.common.GlideUtils;
import com.movie.mling.movieapp.utils.common.MainPermissionsUtils;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.CustomButtonDialog;
import com.movie.mling.movieapp.utils.widget.TitleBar;
import com.pizidea.imagepicker.AndroidImagePicker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/26 17:26
 * $DESE$
 */
public class UserInfoFragment extends BaseFragment implements View.OnClickListener, UserInfoFragmentView {
    private UserInfoFragmentPresenter mUserInfoFragmentPresenter;
    private ImageView image_header;
    private EditText edt_user_name;
    private EditText edt_user_sex;
    private TextView edt_user_day;
    private EditText edt_user_height;
    private EditText edt_user_weight;
    private EditText edt_user_nation;
    private TextView edt_user_work;
    private TextView tv_yanzhengma;
    private EditText edt_user_wx;
    private EditText edt_user_phone;
    private EditText edt_user_verify;
    private Button btn_save;
    private TimeCount time;
    private String mUserName = "";
    private String mUserSex = "";
    private String mUserDay = "";
    private String mUserHeight = "";
    private String mUserWeight = "";
    private String mUserNation = "";
    private String mUserWork = "";
    private String mUserWX = "";
    private String mUserPhone = "";
    private String mUserVerify = "";
    private String mUserImage = "";

    private String urlMethod = "";
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String RECEIVE_BOOT_COMPLETED = Manifest.permission.RECEIVE_BOOT_COMPLETED;
    private static final String[] requestPermissions = {
            PERMISSION_READ_EXTERNAL_STORAGE,
            PERMISSION_WRITE_EXTERNAL_STORAGE,
            PERMISSION_CAMERA,
            ACCESS_COARSE_LOCATION,
            ACCESS_FINE_LOCATION,
    };
    private String savePath = "";
    private TimePickerView pvCustomTime;
    private String timeStart;
    private ArrayList<String> strList;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mUserInfoFragmentPresenter = new UserInfoFragmentPresenter(this);
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.fragment_user_info;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //隐藏时所作的事情
        } else {
            //显示时所作的事情
            urlMethod = Constants.APP_USER_INFO;

            mUserInfoFragmentPresenter.postUserInfo();
        }
    }

    @Override
    protected void onCreateViewContent(View view) {
        view.findViewById(R.id.line_header).setOnClickListener(this);
        view.findViewById(R.id.line_user_name).setOnClickListener(this);
        view.findViewById(R.id.line_user_sex).setOnClickListener(this);
        view.findViewById(R.id.line_user_day).setOnClickListener(this);
        view.findViewById(R.id.line_user_height).setOnClickListener(this);
        view.findViewById(R.id.line_user_weight).setOnClickListener(this);
        view.findViewById(R.id.line_user_nation).setOnClickListener(this);
        view.findViewById(R.id.line_user_work).setOnClickListener(this);
        view.findViewById(R.id.line_user_wx).setOnClickListener(this);
        view.findViewById(R.id.line_user_phone).setOnClickListener(this);
        view.findViewById(R.id.line_user_verify).setOnClickListener(this);
        image_header = (ImageView) view.findViewById(R.id.image_header);
        edt_user_name = (EditText) view.findViewById(R.id.edt_user_name);
        edt_user_sex = (EditText) view.findViewById(R.id.edt_user_sex);
        edt_user_day = (TextView) view.findViewById(R.id.edt_user_day);
        edt_user_height = (EditText) view.findViewById(R.id.edt_user_height);
        edt_user_weight = (EditText) view.findViewById(R.id.edt_user_weight);
        edt_user_nation = (EditText) view.findViewById(R.id.edt_user_nation);
        edt_user_work = (TextView) view.findViewById(R.id.edt_user_work);
        edt_user_wx = (EditText) view.findViewById(R.id.edt_user_wx);
        edt_user_phone = (EditText) view.findViewById(R.id.edt_user_phone);
        edt_user_verify = (EditText) view.findViewById(R.id.edt_user_verify);
        view.findViewById(R.id.btn_save).setOnClickListener(this);
        tv_yanzhengma = view.findViewById(R.id.tv_yanzhengma);
    }

    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    protected void setListener() {
        tv_yanzhengma.setOnClickListener(this);
        initCustomTimePicker();
        urlMethod = Constants.APP_USER_INFO;
        mUserInfoFragmentPresenter.postUserInfo();
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.line_header:
                if (MainPermissionsUtils.checkPermissions(getContext(), requestPermissions)) {
                    putImageLogo();
                } else {
                    requestPermission(requestPermissions, 102);
                }
                break;
            case R.id.line_user_name:
                break;
            case R.id.line_user_sex:
                break;
            case R.id.line_user_day:
                pvCustomTime.show();
                break;
            case R.id.line_user_height:
                break;
            case R.id.line_user_weight:
                break;
            case R.id.line_user_nation:
                break;
            case R.id.line_user_work:
                Intent intent = new Intent(getActivity(), ChangeWorkActivity.class);
                intent.putStringArrayListExtra("intentList", strList);
                startActivityForResult(intent, 101);
                break;
            case R.id.line_user_wx:
                break;
            case R.id.line_user_phone:
                break;
            case R.id.line_user_verify:
                break;
            case R.id.tv_yanzhengma:
                isInputSuccessPhone();
                break;
            case R.id.btn_save:
                isInputSuccess();
                FirstLocation.getInstance(getActivity()).InitLocation(mLocationListener);
                urlMethod = Constants.APP_USER_SAVE;
                mUserInfoFragmentPresenter.postSaveUserInfo();
                break;
        }
    }

    @Override
    public void onActivityResultNestedCompat(int requestCode, int resultCode, Intent data) {
        LogUtil.i("titletitle", "102--------------");
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case 101://认证信息
                edt_user_work.setText(data.getStringExtra("textContent"));
                break;
            case 102://艺人信息
                break;
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
                Log.e("yufs", "當前经度" + loc.getLongitude() + "当前维度：" + loc.getLatitude() + "城市：" + city);
            } else {
                Toast.makeText(mContext, "定位失败，请打开位置权限", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void isInputSuccess() {
        mUserName = edt_user_name.getText().toString();
        if (TextUtils.isEmpty(edt_user_sex.getText().toString())){
            mUserSex = "";
        }else if (TextUtils.equals("男",edt_user_sex.getText().toString())){
            mUserSex = "1";
        }else if (TextUtils.equals("女",edt_user_sex.getText().toString())){
            mUserSex = "2";
        }
        mUserDay = edt_user_day.getText().toString();
        mUserHeight = edt_user_height.getText().toString();
        mUserWeight = edt_user_weight.getText().toString();
        mUserNation = edt_user_nation.getText().toString();
        mUserWork = edt_user_work.getText().toString();
        mUserWX = edt_user_wx.getText().toString();
        mUserPhone = edt_user_phone.getText().toString();
        mUserVerify = edt_user_verify.getText().toString();
    }

    /**
     * 请求权限
     *
     * @param permissions 请求的权限
     * @param requestCode 请求权限的请求码
     */
    public void requestPermission(String[] permissions, int requestCode) {
        if (MainPermissionsUtils.checkPermissions(getContext(), permissions)) {
            if (requestCode == 102)
                putImageLogo();
        } else {
            ActivityCompat.requestPermissions(getActivity(), permissions, requestCode);
        }
    }

    private void putImageLogo() {
        AndroidImagePicker.getInstance().pickAndCrop(getActivity(), false, 120, new AndroidImagePicker.OnImageCropCompleteListener() {
            @Override
            public void onImageCropComplete(Bitmap bmp, float ratio) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String pathFolder = Environment.getExternalStorageDirectory().toString() + "/sdcard/";
                savePath = pathFolder + "t_" + sdf.format(new Date()) + ".png";
                Log.e("yufs", "截图保存路径：" + savePath);
                if (null == bmp) {
                    return;
                }
                try {
                    File file = new File(pathFolder);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    FileOutputStream fos = new FileOutputStream(new File(savePath));

                    boolean b = bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    try {
                        fos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    StringBuffer buffer = new StringBuffer();
                    if (b) {
                        buffer.append("截屏成功 ");
                        Log.e("yufs", "截图完成。。。");
                        commitImage(savePath);
                    } else {
                        buffer.append("截屏失败 ");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void commitImage(String path) {
        File file = new File(path);
//        Glide.with(this).load(file).into(image_header);
        GlideUtils.getInstance().LoadfragmentCircleBitmap(UserInfoFragment.this, file, image_header, R.mipmap.ic_image_m);
    }

    private void initCustomTimePicker() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                timeStart = (date.getTime() / 1000) + "";
//                edt_user_day.setText(AgeUtils.getAgeFromBirthTime(getTime(date)) + "");
                edt_user_day.setText(AppMethod.getStrDataTime(timeStart));
            }
        }).setDate(selectedDate)
                .setRangDate(startDate, selectedDate)
                .setLayoutRes(R.layout.xml_pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        TextView ivTitle = (TextView) v.findViewById(R.id.iv_title);
                        ivTitle.setText("出生日期");
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .setDividerColor(0xFF24AD9D)
                .build();

    }

    private static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
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
                    putImageLogo();
                } else {
                    final CustomButtonDialog dialog = new CustomButtonDialog(getActivity());
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
                            MainPermissionsUtils.startAppSettings(getActivity());
                        }
                    });
                }
                break;
        }
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(urlMethod);
        params.put("token", SharePreferenceUtil.getString(getContext(), UserConfig.USER_TOKEN, ""));
        params.put("nickname", mUserName);
        params.put("intro", "");
        params.put("lng", "36.642879");
        params.put("lat", "117.07867");
        params.put("gender",mUserSex);
        params.put("shengao", mUserHeight);
        params.put("tizhong", mUserWeight);
        params.put("zhiye", mUserWork);
        params.put("minzu", mUserNation);
        params.put("shengri", mUserDay);
        params.put("ext_weixin", mUserWX);
        params.put("ext_mobile", mUserPhone);
        params.put("yzm", mUserVerify);
        File file = new File(savePath);
        try {
            if (file != null) {
                params.put("avatar", file);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return params;
    }

    @Override
    public void showProgress() {
//        MDialog.getInstance(getActivity()).showProgressDialog();
    }

    @Override
    public void closeProgress() {
//        MDialog.getInstance(getActivity()).closeProgressDialog();
    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {

    }

    @Override
    public void excuteSuccessCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(getActivity()).showToast("保存成功");
    }

    @Override
    public void excuteSuccessUserInfoCallBack(UserVo mCallBackVo) {
        GlideUtils.getInstance().LoadfragmentCircleBitmap(UserInfoFragment.this, mCallBackVo.getData().getAvatar(), image_header, R.mipmap.icon_user_default_image);
        edt_user_name.setText(mCallBackVo.getData().getNickname());
        edt_user_day.setText(mCallBackVo.getData().getShengri());
        edt_user_height.setText(mCallBackVo.getData().getShengao());
        edt_user_weight.setText(mCallBackVo.getData().getTizhong());
        edt_user_work.setText(mCallBackVo.getData().getZhiye());
        if (!TextUtils.isEmpty(mCallBackVo.getData().getZhiye())) {
            String[] str = mCallBackVo.getData().getZhiye().split(",");
            if (str.length > 0) {
                strList = new ArrayList<>();
                for (int i = 0; i < str.length; i++) {
                    strList.add(str[i]);
                }
            }
        }
        edt_user_wx.setText(mCallBackVo.getData().getExt_weixin());
        edt_user_phone.setText(mCallBackVo.getData().getExt_mobile());
        edt_user_nation.setText(mCallBackVo.getData().getMinzu());
        if (TextUtils.equals("1", mCallBackVo.getData().getGender())) {
            edt_user_sex.setText("男");
        } else if (TextUtils.equals("2", mCallBackVo.getData().getGender())) {
            edt_user_sex.setText("女");
        } else {
            edt_user_sex.setText("");
        }
        SharePreferenceUtil.setValue(getContext(), UserConfig.USER_STATUS, mCallBackVo.getData().getStatus());
    }

    @Override
    public void excuteSuccessUserImageCallBack(UserVo mCallBackVo) {

    }

    @Override
    public void excuteSuccessGetCodeCallBack(CallBackVo mCallBackVo) {
        time.start();
    }

    @Override
    public RequestParams getParamentersSend() {
        RequestParams mapParams = AppMethod.getMapParams(Constants.APP_USER_SENDSMS);
        mapParams.put("username", mUserPhone);
        return mapParams;
    }

    public void isInputSuccessPhone() {
        mUserPhone = edt_user_phone.getText().toString();
        if (TextUtils.isEmpty(mUserPhone)) {
            MDialog.getInstance(getActivity()).showToast("请输入手机号");
            return;
        }
        mUserInfoFragmentPresenter.getPhoneSMS();
    }

    /**
     * 计时器
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            tv_yanzhengma.setText("重新获取");
            tv_yanzhengma.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            tv_yanzhengma.setClickable(false);
            tv_yanzhengma.setText(millisUntilFinished / 1000 + "秒" + "后重发");
        }
    }
}

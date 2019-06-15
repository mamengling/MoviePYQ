package com.movie.mling.movieapp.fragment;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.iactivityview.MineFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mould.EditTextActivity;
import com.movie.mling.movieapp.presenter.MineFragmentPersenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.GlideUtils;
import com.movie.mling.movieapp.utils.common.MainPermissionsUtils;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.CustomButtonDialog;
import com.movie.mling.movieapp.utils.widget.TitleBar;
import com.pizidea.imagepicker.AndroidImagePicker;
import com.pizidea.imagepicker.bean.ImageItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/26 17:28
 * $DESE$
 */
public class MineFragment extends BaseFragment implements View.OnClickListener, MineFragmentView {
    private MineFragmentPersenter mMineFragmentPersenter;
    private LinearLayout line_auth;
    private ScrollView scrollView;
    private TextView tv_authing;
    private TextView tv_authed;
    private Button btn_up;
    private EditText edt_user_auth_name;
    private EditText edt_card_number;
    private EditText edt_company_name;
    private EditText edt_phone;
    private TextView edt_yiren;
    private TextView tv_card_yes;//正面
    private ImageView image_card_yes;//正面
    private TextView tv_card_no;//反面
    private ImageView image_card_no;//反面
    private TextView tv_other;//其他证明材料
    private ImageView image_other;//其他证明材料
    private TextView tv_image_moka;//摩卡
    private ImageView image_moka;//摩卡
    private TextView tv_auth_message;//认证信息
    private String mRealname;
    private String mCardno;
    private File mCarda;
    private File mCardb;
    private String mRemark;
    private File mQita;
    private String mCompany;
    private String mMobile;
    private String mYiren;
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
            READ_PHONE_STATE,
            RECEIVE_BOOT_COMPLETED
    };
    private int changeType;
    private String imagePath;
    private String token;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mMineFragmentPersenter = new MineFragmentPersenter(this);
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void onCreateViewContent(View view) {
        view.findViewById(R.id.line_auth_name).setOnClickListener(this);
        view.findViewById(R.id.line_auth_number).setOnClickListener(this);
        view.findViewById(R.id.line_card_yes).setOnClickListener(this);
        view.findViewById(R.id.line_card_no).setOnClickListener(this);
        view.findViewById(R.id.line_message).setOnClickListener(this);
        view.findViewById(R.id.line_other).setOnClickListener(this);
        view.findViewById(R.id.line_company_name).setOnClickListener(this);
        view.findViewById(R.id.line_phone).setOnClickListener(this);
        view.findViewById(R.id.line_yiren).setOnClickListener(this);
        view.findViewById(R.id.line_moka).setOnClickListener(this);
        view.findViewById(R.id.btn_save).setOnClickListener(this);
        edt_user_auth_name = (EditText) view.findViewById(R.id.edt_user_auth_name);
        edt_card_number = (EditText) view.findViewById(R.id.edt_card_number);
        edt_company_name = (EditText) view.findViewById(R.id.edt_company_name);
        edt_phone = (EditText) view.findViewById(R.id.edt_phone);
        edt_yiren = (TextView) view.findViewById(R.id.edt_yiren);
        tv_card_yes = (TextView) view.findViewById(R.id.tv_card_yes);
        image_card_yes = (ImageView) view.findViewById(R.id.image_card_yes);
        tv_card_no = (TextView) view.findViewById(R.id.tv_card_no);
        image_card_no = (ImageView) view.findViewById(R.id.image_card_no);
        tv_other = (TextView) view.findViewById(R.id.tv_other);
        image_other = (ImageView) view.findViewById(R.id.image_other);
        tv_image_moka = (TextView) view.findViewById(R.id.tv_image_moka);
        image_moka = (ImageView) view.findViewById(R.id.image_moka);
        tv_auth_message = (TextView) view.findViewById(R.id.tv_auth_message);
        line_auth = (LinearLayout) view.findViewById(R.id.line_auth);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        tv_authing = (TextView) view.findViewById(R.id.tv_authing);
        tv_authed = (TextView) view.findViewById(R.id.tv_authed);
        btn_up = (Button) view.findViewById(R.id.btn_up);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //隐藏时所作的事情
        } else {
            //显示时所作的事情
            if (TextUtils.equals("2", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_STATUS, ""))) {
                scrollView.setVisibility(View.GONE);
                line_auth.setVisibility(View.VISIBLE);
                tv_authing.setVisibility(View.VISIBLE);
                tv_authed.setVisibility(View.GONE);
            } else if (TextUtils.equals("1", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_STATUS, ""))) {
                scrollView.setVisibility(View.GONE);
                tv_authing.setText("您已认证！");
                tv_authing.setVisibility(View.VISIBLE);
                line_auth.setVisibility(View.VISIBLE);
            } else {
                scrollView.setVisibility(View.VISIBLE);
                line_auth.setVisibility(View.GONE);
            }
        }
    }
    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    protected void setListener() {
        if (TextUtils.equals("2", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_STATUS, ""))) {
            scrollView.setVisibility(View.GONE);
            line_auth.setVisibility(View.VISIBLE);
            tv_authing.setVisibility(View.VISIBLE);
            tv_authed.setVisibility(View.GONE);
        } else if (TextUtils.equals("1", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_STATUS, ""))) {
            scrollView.setVisibility(View.GONE);
            tv_authing.setText("您已认证！");
            tv_authing.setVisibility(View.VISIBLE);
            line_auth.setVisibility(View.VISIBLE);
        } else {
            scrollView.setVisibility(View.VISIBLE);
            line_auth.setVisibility(View.GONE);
        }
        btn_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.line_auth_name:
                break;
            case R.id.line_auth_number:
                break;
            case R.id.line_card_yes:
                changeType = 101;
                if (MainPermissionsUtils.checkPermissions(getContext(), requestPermissions)) {
                    putImageAuth();
                } else {
                    requestPermission(requestPermissions, changeType);
                }
                break;
            case R.id.line_card_no:
                changeType = 102;
                if (MainPermissionsUtils.checkPermissions(getContext(), requestPermissions)) {
                    putImageAuth();
                } else {
                    requestPermission(requestPermissions, changeType);
                }
                break;
            case R.id.line_message:
                Intent intent = new Intent(getActivity(), EditTextActivity.class);
                intent.putExtra("titleName", "认证信息");
                intent.putExtra("titleEditText", "");
                intent.putExtra("titleContent", "请输入认证信息");
                startActivityForResult(intent, 105);
                break;
            case R.id.line_other:
                changeType = 103;
                if (MainPermissionsUtils.checkPermissions(getContext(), requestPermissions)) {
                    putImageAuth();
                } else {
                    requestPermission(requestPermissions, changeType);
                }
                break;
            case R.id.line_company_name:
                break;
            case R.id.line_phone:
                break;
            case R.id.line_yiren:
                Intent intentY = new Intent(getActivity(), EditTextActivity.class);
                intentY.putExtra("titleName", "负责艺人");
                intentY.putExtra("titleEditText", edt_yiren.getText().toString());
                intentY.putExtra("titleContent", "请输入负责艺人");
                startActivityForResult(intentY, 106);
                break;
            case R.id.line_moka:
                changeType = 104;
                if (MainPermissionsUtils.checkPermissions(getContext(), requestPermissions)) {
                    putImageAuth();
                } else {
                    requestPermission(requestPermissions, changeType);
                }
                break;
            case R.id.btn_save:
                token = Constants.APP_USER_AUTH;
                isInputSuccess();
                break;
            case R.id.btn_up:
                final CustomButtonDialog dialog = new CustomButtonDialog(getActivity());
                dialog.setText("确定要取消或重新认证？");
                dialog.setLeftButtonText("重新认证");
                dialog.setLeftButtonTextColor(R.color.colorAccent);
                dialog.setRightButtonText("取消");
                dialog.setRightButtonTextColor(R.color.colorAccent);
                dialog.setButtonListener(new CustomButtonDialog.OnButtonListener() {
                    @Override
                    public void onLeftButtonClick(CustomButtonDialog var1) {
                        dialog.cancel();
                        token = Constants.APP_USER_AUTH_RESET;
                        mMineFragmentPersenter.getAgainAuth();
                    }

                    @Override
                    public void onRightButtonClick(CustomButtonDialog var1) {
                        dialog.cancel();
                        MainPermissionsUtils.startAppSettings(getActivity());
                    }
                });
                break;
        }
    }

    @Override
    public void onActivityResultNestedCompat(int requestCode, int resultCode, Intent data) {
        LogUtil.i("titletitle", "102--------------");
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case 105://认证信息
                tv_auth_message.setText(data.getStringExtra("editTextContent"));
                break;
            case 106://艺人信息
                edt_yiren.setText(data.getStringExtra("editTextContent"));
                break;
        }
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
                putImageAuth();
        } else {
            ActivityCompat.requestPermissions(getActivity(), permissions, requestCode);
        }
    }

    private void isInputSuccess() {
        mRealname = edt_user_auth_name.getText().toString();
        mCardno = edt_card_number.getText().toString();
        mRemark = tv_auth_message.getText().toString();
        mCompany = edt_company_name.getText().toString();
        mMobile = edt_phone.getText().toString();
        mYiren = edt_yiren.getText().toString();
        mMineFragmentPersenter.getSaveAuth();
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo userVo) {
        MDialog.getInstance(getActivity()).showToast("操作成功");
        scrollView.setVisibility(View.GONE);
        line_auth.setVisibility(View.VISIBLE);
        tv_authing.setVisibility(View.VISIBLE);
        tv_authed.setVisibility(View.GONE);
        SharePreferenceUtil.setValue(getActivity(), UserConfig.USER_STATUS, "2");
    }

    @Override
    public void excuteSuccessAgainCallBack(CallBackVo userVo) {
        MDialog.getInstance(getActivity()).showToast("操作成功");
        scrollView.setVisibility(View.VISIBLE);
        line_auth.setVisibility(View.GONE);
        SharePreferenceUtil.setValue(getActivity(), UserConfig.USER_STATUS, "0");
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(token);
        params.put("token", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_TOKEN, ""));
        params.put("realname", mRealname);
        params.put("cardno", mCardno);
        try {
            params.put("carda", mCarda);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            params.put("cardb", mCardb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        params.put("remark", mRemark);
        try {
            params.put("qita", mQita);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        params.put("company", mCompany);
        params.put("mobile", mMobile);
        params.put("yiren", mYiren);
        return params;
    }

    @Override
    public void showProgress() {
        MDialog.getInstance(getActivity()).showProgressDialog();
    }

    @Override
    public void closeProgress() {
        MDialog.getInstance(getActivity()).closeProgressDialog();
    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(getActivity()).showToast(mCallBackVo.getMessage());
    }

    private void putImageAuth() {
        AndroidImagePicker.getInstance().pickSingle(getActivity(), true, new AndroidImagePicker.OnImagePickCompleteListener() {
            @Override
            public void onImagePickComplete(List<ImageItem> items) {
                if (items != null && items.size() > 0) {
                    switch (changeType) {
                        case 101:
                            imagePath = items.get(0).path;
                            mCarda = new File(imagePath);
                            tv_card_yes.setVisibility(View.GONE);
                            image_card_yes.setVisibility(View.VISIBLE);
                            GlideUtils.getInstance().LoadFragmentBitmap(MineFragment.this, mCarda, image_card_yes, R.mipmap.ic_image_m, R.mipmap.ic_image_m, GlideUtils.LOAD_BITMAP);
                            break;
                        case 102:
                            imagePath = items.get(0).path;
                            mCardb = new File(imagePath);
                            tv_card_no.setVisibility(View.GONE);
                            image_card_no.setVisibility(View.VISIBLE);
                            GlideUtils.getInstance().LoadFragmentBitmap(MineFragment.this, mCardb, image_card_no, R.mipmap.ic_image_m, R.mipmap.ic_image_m, GlideUtils.LOAD_BITMAP);
                            break;
                        case 103:
                            imagePath = items.get(0).path;
                            mQita = new File(imagePath);
                            tv_other.setVisibility(View.GONE);
                            image_other.setVisibility(View.VISIBLE);
                            GlideUtils.getInstance().LoadFragmentBitmap(MineFragment.this, mQita, image_other, R.mipmap.ic_image_m, R.mipmap.ic_image_m, GlideUtils.LOAD_BITMAP);
                            break;
                        case 104:
                            break;
                    }

                }
            }
        });
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
        if (MainPermissionsUtils.verifyPermissions(grantResults)) {
            putImageAuth();
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
    }
}

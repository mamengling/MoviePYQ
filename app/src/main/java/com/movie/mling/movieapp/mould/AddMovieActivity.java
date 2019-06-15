package com.movie.mling.movieapp.mould;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.AddMovieActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.presenter.AddMovieActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.GlideUtils;
import com.movie.mling.movieapp.utils.common.MainPermissionsUtils;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.CustomButtonDialog;
import com.movie.mling.movieapp.utils.widget.TitleBar;
import com.pizidea.imagepicker.AndroidImagePicker;
import com.pizidea.imagepicker.bean.ImageItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/6 13:11
 * $DESE$
 */
public class AddMovieActivity extends BaseCompatActivity implements AddMovieActivityView, View.OnClickListener {
    private AddMovieActivityPresenter mAddMovieActivityPresenter;
    private ImageView image_add;
    private TextInputLayout editText;
    private Button btn_commit;
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
    private String savePath;
    private String newstext;
    private File file;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mAddMovieActivityPresenter = new AddMovieActivityPresenter(this);
        titleBar.setTitleName("发布通告");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(AddMovieActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_movie_add;
    }

    @Override
    protected void onCreateViewContent(View view) {
        image_add = view.findViewById(R.id.image_add);
        editText = view.findViewById(R.id.editText);
        btn_commit = view.findViewById(R.id.btn_commit);
    }

    @Override
    protected void getExras() {

    }

    @Override
    protected void setListener() {
        image_add.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
    }

    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public void excuteSuccessCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(this).showToast("通告发布成功");
        finish();
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_FAV_ADD);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        params.put("newstext", newstext);
        try {
            params.put("titlepic", file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return params;
    }

    @Override
    public void showProgress() {
        MDialog.getInstance(this).showProgressDialog();
    }

    @Override
    public void closeProgress() {
        MDialog.getInstance(this).closeProgressDialog();
    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(this).showToast(mCallBackVo.getMessage());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                inputSuccess();
                break;
            case R.id.image_add:
                if (MainPermissionsUtils.checkPermissions(this, requestPermissions)) {
                    putImage();
                } else {
                    requestPermission(requestPermissions, 101);
                }
                break;
        }
    }

    private void inputSuccess() {
        newstext = editText.getEditText().getText().toString();
        if (TextUtils.isEmpty(newstext)) {
            MDialog.getInstance(this).showToast("请简要描述想要发布的通告");
        }
        if (TextUtils.isEmpty(savePath)) {
            MDialog.getInstance(this).showToast("请上传封面图片");
        }
        mAddMovieActivityPresenter.getMovieList();
    }

    public void putImage() {
        AndroidImagePicker.getInstance().pickSingle(AddMovieActivity.this, true, new AndroidImagePicker.OnImagePickCompleteListener() {
            @Override
            public void onImagePickComplete(List<ImageItem> items) {
                if (items != null && items.size() > 0) {
                    Log.i("yufs", "=====选择了：" + items.get(0).path);
                    savePath = items.get(0).path;
                    file = new File(savePath);
                    GlideUtils.getInstance().LoadBitmap(AddMovieActivity.this, file, image_add, R.mipmap.ic_image_m, R.mipmap.ic_image_m, GlideUtils.LOAD_BITMAP);
                }
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
            if (requestCode == 101) {
                putImage();
            }
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
        if (MainPermissionsUtils.verifyPermissions(grantResults)) {
            putImage();
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
                    MainPermissionsUtils.startAppSettings(AddMovieActivity.this);
                }
            });
        }
    }
}

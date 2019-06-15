package com.movie.mling.movieapp.mould;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.ImageInfoAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.ImageInfoActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.ImageInfoBean;
import com.movie.mling.movieapp.presenter.ImageInfoActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.ImageLoaderUtils;
import com.movie.mling.movieapp.utils.common.MainPermissionsUtils;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.CustomButtonDialog;
import com.movie.mling.movieapp.utils.widget.MGridView;
import com.movie.mling.movieapp.utils.widget.TitleBar;
import com.pizidea.imagepicker.AndroidImagePicker;
import com.pizidea.imagepicker.bean.ImageItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/28 13:37
 * $DESE$
 */
public class ImageInfoActivity extends BaseCompatActivity implements ImageInfoActivityView, View.OnClickListener {
    private ImageInfoActivityPresenter mImageInfoActivityPresenter;
    private ImageView image_fm;
    private TextView tv_name;
    private TextView tv_number;
    private String pic_id;
    private MGridView gridView;
    private ImageInfoAdapter mAdapter;
    private ArrayList<ImageInfoBean.DataBean> mList = new ArrayList<>();
    private List<ImageInfoBean.DataBean> mListItem;
    private String isFM = "";
    private String photo_id;
    private String picUrl;
    private String name;
    private String number;
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
    private File file;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mImageInfoActivityPresenter = new ImageInfoActivityPresenter(this);
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_add_image_view;
    }

    @Override
    protected void onCreateViewContent(View view) {
        view.findViewById(R.id.title_bar_back).setOnClickListener(this);
        view.findViewById(R.id.radiobutton2).setOnClickListener(this);
        view.findViewById(R.id.radiobutton3).setOnClickListener(this);
        view.findViewById(R.id.btn_save_image).setOnClickListener(this);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_number = (TextView) view.findViewById(R.id.tv_number);
        gridView = (MGridView) view.findViewById(R.id.grid_view);
        image_fm = (ImageView) view.findViewById(R.id.image_fm);
    }

    @Override
    protected void getExras() {
        pic_id = getIntent().getStringExtra("pic_id");
        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("number");
    }

    @Override
    protected void setListener() {
        tv_name.setText(name);
        tv_number.setText(number);
        mAdapter = new ImageInfoAdapter(mList, this);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> items = new ArrayList<>();
                items.add(mList.get(position).getTitlepic());
                PictureConfig config = new PictureConfig.Builder()
                        .setListData(items)//图片数据List<String> list
                        .setPosition(position)//图片下标（从第position张图片开始浏览）
                        .setDownloadPath("pictureviewer")//图片下载文件夹地址
                        .setIsShowNumber(true)//是否显示数字下标
                        .needDownload(true)//是否支持图片下载
                        .setPlacrHolder(R.mipmap.ic_launcher)//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                        .build();
                ImagePagerActivity.startActivity(ImageInfoActivity.this, config);
            }
        });
    }

    @Override
    protected void fromNetGetData() {
        mImageInfoActivityPresenter.getUserImageList();
    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_bar_back:
                ActivityAnim.endActivity(this);
                break;
            case R.id.btn_save_image:
                if (MainPermissionsUtils.checkPermissions(this, requestPermissions)) {
                    putImage();
                } else {
                    requestPermission(requestPermissions, 101);
                }
                break;
            case R.id.radiobutton2:
                Intent intentS = new Intent(this, SetImageFmActivity.class);
                intentS.putParcelableArrayListExtra("imagelist", mList);
                intentS.putExtra("flag", true);
                ActivityAnim.startActivityForResult(this, intentS, 101);
                break;
            case R.id.radiobutton3:
                Intent intentD = new Intent(this, SetImageFmActivity.class);
                intentD.putParcelableArrayListExtra("imagelist", mList);
                intentD.putExtra("flag", false);
                ActivityAnim.startActivityForResult(this, intentD, 102);
                break;
        }
    }
    public void putImage() {
        AndroidImagePicker.getInstance().pickSingle(ImageInfoActivity.this, true, new AndroidImagePicker.OnImagePickCompleteListener() {
            @Override
            public void onImagePickComplete(List<ImageItem> items) {
                if (items != null && items.size() > 0) {
                    Log.i("yufs", "=====选择了：" + items.get(0).path);
                    savePath = items.get(0).path;
                    file = new File(savePath);
                    mImageInfoActivityPresenter.getPutImage();
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
    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_IMAGE_PIC);
        params.put("keyid", pic_id);
        return params;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case 101:
                photo_id = data.getStringExtra("picID");
                picUrl = data.getStringExtra("picUrl");
                ImageLoaderUtils.loadImage(image_fm, picUrl, R.mipmap.ic_image_m);
                mImageInfoActivityPresenter.getSetImageFm();
                break;
            case 102:
                photo_id = data.getStringExtra("picID");
                picUrl = data.getStringExtra("picUrl");
                ImageLoaderUtils.loadImage(image_fm, picUrl, R.mipmap.ic_image_m);
                mImageInfoActivityPresenter.getDeleteImage();

                break;
        }
    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {

    }

    @Override
    public RequestParams getParamentersPutImage() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_IMAGE_PIC_ADD);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        params.put("aid", pic_id);
        try {
            params.put("upfile", file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return params;
    }

    @Override
    public RequestParams getParamentersDeleteImage() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_IMAGE_DELETE);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        params.put("id", photo_id);
        return params;
    }

    @Override
    public RequestParams getParamentersSetImage() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_IMAGE_SET);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        params.put("keyid", pic_id);
        params.put("keytype", "cover");
        params.put("title", "");
        params.put("photo_id", photo_id);
        return params;
    }

    @Override
    public void excuteSuccessGetCallBack(ImageInfoBean mCallBackVo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().size() > 0) {
            mListItem = mCallBackVo.getData();
            mList.clear();
            mList.addAll(mListItem);
            mAdapter.notifyDataSetChanged();
            for (int i = 0; i < mList.size(); i++) {
                if (TextUtils.equals("1", mList.get(i).getFm())) {
                    isFM = mList.get(i).getTitlepic();
                }
            }
            LogUtil.i("image", isFM);
            ImageLoaderUtils.loadImage(image_fm, isFM, R.mipmap.icon_hone_default_image);
        } else {

        }
    }

    @Override
    public void excuteSuccessSetCallBack(CallBackVo mCallBackVo) {
        mImageInfoActivityPresenter.getUserImageList();
    }

    @Override
    public void excuteSuccessPutCallBack(CallBackVo mCallBackVo) {
        mImageInfoActivityPresenter.getUserImageList();
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
                    MainPermissionsUtils.startAppSettings(ImageInfoActivity.this);
                }
            });
        }
    }
}

package com.movie.mling.movieapp.mould;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.ImageHUserInfoAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.UserInfoActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.UserVo;
import com.movie.mling.movieapp.presenter.UserInfoActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.GlideUtils;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.CustomButtonDialog;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/1 16:09
 * $DESE$
 */
public class UserInfoActivity extends BaseCompatActivity implements UserInfoActivityView, View.OnClickListener {
    private UserInfoActivityPresenter mUserInfoActivityPresenter;
    private ImageHUserInfoAdapter mAdapter;
    private LinearLayout line_biaoqian;
    private RecyclerView recycler_view;
    private ImageView image_bg_view;
    private ImageView image_view_header;
    private ImageView image_renzheng;
    private TextView tv_user_phone;
    private TextView tv_hh_phone;
    private TextView tv_hh_weixin;
    private TextView tv_user_name;
    private TextView tv_user_content;
    private TextView tv_user_height;
    private TextView tv_user_sex;
    private TextView tv_user_weight;
    private TextView tv_user_age;
    private TextView tv_user_minzu;
    private TextView tv_info;
    private ArrayList<String> strList;
    private String user_uid;
    private String url;
    private String keytype;
    private String xingbie;

    @Override
    protected void titleBarSet(final TitleBar titleBar) {
        mUserInfoActivityPresenter = new UserInfoActivityPresenter(this);
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void onCreateViewContent(View view) {
        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        image_bg_view = (ImageView) view.findViewById(R.id.image_bg_view);
        image_view_header = (ImageView) view.findViewById(R.id.image_view_header);
        image_renzheng = (ImageView) view.findViewById(R.id.image_renzheng);
        tv_user_phone = (TextView) view.findViewById(R.id.tv_user_phone);
        tv_hh_phone = (TextView) view.findViewById(R.id.tv_hh_phone);
        tv_hh_weixin = (TextView) view.findViewById(R.id.tv_hh_weixin);
        tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
        tv_user_content = (TextView) view.findViewById(R.id.tv_user_content);
        tv_user_height = (TextView) view.findViewById(R.id.tv_user_height);
        tv_user_sex = (TextView) view.findViewById(R.id.tv_user_sex);
        tv_user_weight = (TextView) view.findViewById(R.id.tv_user_weight);
        tv_user_age = (TextView) view.findViewById(R.id.tv_user_age);
        tv_user_minzu = (TextView) view.findViewById(R.id.tv_user_minzu);
        tv_info = (TextView) view.findViewById(R.id.tv_info);
        line_biaoqian = (LinearLayout) view.findViewById(R.id.line_biaoqian);
        view.findViewById(R.id.title_bar_back).setOnClickListener(this);
    }

    @Override
    protected void getExras() {
        user_uid = getIntent().getStringExtra("user_uid");
    }

    @Override
    protected void setListener() {
        image_view_header.setOnClickListener(this);
        tv_hh_phone.setOnClickListener(this);
        tv_hh_weixin.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view.setLayoutManager(layoutManager);
        mAdapter = new ImageHUserInfoAdapter(this);
        recycler_view.setAdapter(mAdapter);
    }

    @Override
    protected void fromNetGetData() {
        url = Constants.APP_USER_INFO;
        mUserInfoActivityPresenter.getUserInfo();
    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_hh_phone:
                if (TextUtils.equals(SharePreferenceUtil.getString(UserInfoActivity.this, UserConfig.USER_ID, ""), user_uid)) {
                    MDialog.getInstance(UserInfoActivity.this).showToast("不能与自己互换微信或电话");
                } else {
                    final CustomButtonDialog dialog = new CustomButtonDialog(this);
                    dialog.setText("是否与" + (TextUtils.equals("1", xingbie) ? "他" : "她") + "互换电话？");
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
                            keytype = "2";
                            url = Constants.APP_USER_MSG_APPLY;
                            mUserInfoActivityPresenter.getUserMsgApply();
                        }
                    });
                }
                break;
            case R.id.tv_hh_weixin:
                if (TextUtils.equals(SharePreferenceUtil.getString(UserInfoActivity.this, UserConfig.USER_ID, ""), user_uid)) {
                    MDialog.getInstance(UserInfoActivity.this).showToast("不能与自己互换微信或电话");
                } else {

                    final CustomButtonDialog dialogw = new CustomButtonDialog(this);
                    dialogw.setText("是否与" + (TextUtils.equals("1", xingbie) ? "他" : "她") + "互换微信？");
                    dialogw.setLeftButtonText("取消");
                    dialogw.setLeftButtonTextColor(R.color.colorAccent);
                    dialogw.setRightButtonText("确定");
                    dialogw.setRightButtonTextColor(R.color.colorAccent);
                    dialogw.setButtonListener(new CustomButtonDialog.OnButtonListener() {
                        @Override
                        public void onLeftButtonClick(CustomButtonDialog var1) {
                            dialogw.cancel();
                        }

                        @Override
                        public void onRightButtonClick(CustomButtonDialog var1) {
                            dialogw.cancel();
                            url = Constants.APP_USER_MSG_APPLY;
                            keytype = "1";
                            mUserInfoActivityPresenter.getUserMsgApply();
                        }
                    });
                }
                break;
            case R.id.title_bar_back:
                ActivityAnim.endActivity(UserInfoActivity.this);
                break;
            case R.id.image_view_header:
//                Map<String, String> map = new HashMap<>();
//                map.put("loadUrl", "http://cdn.23so.cn/web/upload/201804/12/8484370a6d472dd322f4ecf12ff54958565fe3fd.pdf");
//                map.put("", "aaa");
//                ActivityAnim.intentActivity(this, PdfViewReaderActivity.class, map);
                break;
        }
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(url);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        params.put("uid", user_uid);
        params.put("user_id", user_uid);
        params.put("keytype", keytype);
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
        MDialog.getInstance(this).showToast(mCallBackVo.getMessage());
    }

    @Override
    public void excuteSuccessCallBack(UserVo userVo) {
        if (userVo != null && userVo.getData() != null) {
            if (userVo.getData().getPhoto() != null && userVo.getData().getPhoto().size() > 0) {
                mAdapter.onReference(userVo.getData().getPhoto());
            }
            GlideUtils.getInstance().LoadContextCircleBitmap(this, userVo.getData().getAvatar(), image_view_header, R.mipmap.icon_user_default_image);
            if (TextUtils.equals("1", userVo.getData().getStatus())) {
                image_renzheng.setVisibility(View.VISIBLE);
            } else {
                image_renzheng.setVisibility(View.GONE);
            }
            tv_user_name.setText(userVo.getData().getNickname());
            tv_user_content.setText(userVo.getData().getAuth_text());
            tv_user_phone.setText(TextUtils.isEmpty(userVo.getData().getNickname()) ? userVo.getData().getUsername() : userVo.getData().getNickname());
            tv_user_height.setText(userVo.getData().getShengao());
            xingbie = userVo.getData().getGender();
            if (TextUtils.equals("1", xingbie)) {
                tv_user_sex.setText("男");
            } else if (TextUtils.equals("2", xingbie)) {
                tv_user_sex.setText("女");
            } else {
                tv_user_sex.setText("");
            }
            tv_user_age.setText(userVo.getData().getShengri_age());
            tv_user_weight.setText(userVo.getData().getTizhong());
            tv_user_minzu.setText(userVo.getData().getMinzu());
            tv_info.setText(userVo.getData().getIntro());
            if (!TextUtils.isEmpty(userVo.getData().getZhiye())) {
                String[] str = userVo.getData().getZhiye().split(",");
                line_biaoqian.removeAllViews();
                for (int i = 0; i < str.length; i++) {
                    View view = LayoutInflater.from(mContext).inflate(R.layout.xml_text_view_biaoqian, null);
                    TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
                    tv_name.setText(str[i]);
                    line_biaoqian.addView(view);
                }
            }
        }
    }

    @Override
    public void excuteSuccessHHCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(this).showToast("互换成功");
    }

    @Override
    public void excuteSuccessPDFCallBack(String mCallBackVo) {
        LogUtil.i("putOK", mCallBackVo + "");
    }
}

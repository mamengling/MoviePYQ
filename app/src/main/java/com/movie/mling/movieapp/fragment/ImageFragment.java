package com.movie.mling.movieapp.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.RadioGroup;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.ImageListAdapter;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.iactivityview.ImageFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.ImageBean;
import com.movie.mling.movieapp.mould.EditTextActivity;
import com.movie.mling.movieapp.mould.ImageInfoActivity;
import com.movie.mling.movieapp.presenter.ImageFragmentPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/26 17:27
 * $DESE$
 */
public class ImageFragment extends BaseFragment implements View.OnClickListener, ImageFragmentView {
    private ImageFragmentPresenter mImageFragmentPresenter;
    private Button radiobutton1;
    private CheckBox radiobutton2;
    private CheckBox radiobutton3;
    private GridView grid_view;
    private ImageListAdapter mAdapter;
    private List<ImageBean.DataBean> mList = new ArrayList<>();
    private List<ImageBean.DataBean> mListItem;
    private String pic_id;
    private String keytype;
    private String title;
    private int typeY;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mImageFragmentPresenter = new ImageFragmentPresenter(this);
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.fragment_image;
    }

    @Override
    protected void onCreateViewContent(View view) {
        radiobutton1 = (Button) view.findViewById(R.id.radiobutton1);
        radiobutton2 = (CheckBox) view.findViewById(R.id.radiobutton2);
        radiobutton3 = (CheckBox) view.findViewById(R.id.radiobutton3);
        grid_view = (GridView) view.findViewById(R.id.grid_view);
    }

    @Override
    protected void fromNetGetData() {
        mImageFragmentPresenter.getUserImageList();
    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    protected void setListener() {
        radiobutton1.setOnClickListener(this);
//        radiogroup.setOnCheckedChangeListener(new OnTabListener());
        TypeCheckBoxListener boxListener = new TypeCheckBoxListener();
        radiobutton2.setOnCheckedChangeListener(boxListener);
        radiobutton3.setOnCheckedChangeListener(boxListener);
        mAdapter = new ImageListAdapter(mList, getContext());
        grid_view.setAdapter(mAdapter);
        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String> map = new HashMap<>();
                map.put("pic_id", mList.get(position).getId());
                map.put("name", mList.get(position).getTitle());
                map.put("number", mList.get(position).getCount() + "张");
                ActivityAnim.intentActivity(getActivity(), ImageInfoActivity.class, map);
            }
        });
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                switch (ClickType) {
                    case 1:
                        keytype = "rename";
                        pic_id = content;
                        Intent intent = new Intent(getActivity(), EditTextActivity.class);
                        intent.putExtra("titleName", "修改相册名称");
                        intent.putExtra("titleEditText", mList.get(position).getTitle());
                        intent.putExtra("titleContent", "请填写相册名称");
                        startActivityForResult(intent, 102);
                        break;
                    case 2:
                        keytype = "remove";
                        pic_id = content;
                        mImageFragmentPresenter.getSetImage();
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResultNestedCompat(int requestCode, int resultCode, Intent data) {
        LogUtil.i("titletitle", "102--------------");
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case 101://创建相册
                title = data.getStringExtra("editTextContent");
                mImageFragmentPresenter.getUserAlbumAdd();
                LogUtil.i("titletitle", "101" + title);
                break;
            case 102://修改名字
                title = data.getStringExtra("editTextContent");
                mImageFragmentPresenter.getSetImage();
                LogUtil.i("titletitle", "102" + title);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radiobutton1:
                Intent intent = new Intent(getActivity(), EditTextActivity.class);
                intent.putExtra("titleName", "修改相册名称");
                intent.putExtra("titleEditText", "");
                intent.putExtra("titleContent", "请填写相册名称");
                startActivityForResult(intent, 101);
                break;
        }
    }


    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_IMAGE_ALBUM);
        params.put("uid", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_ID, ""));
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
        MDialog.getInstance(getContext()).showToast(mCallBackVo.getMessage());
    }

    @Override
    public RequestParams getParamentersSetImage() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_IMAGE_SET);
        params.put("token", SharePreferenceUtil.getString(getContext(), UserConfig.USER_TOKEN, ""));
        params.put("keyid", pic_id);
        params.put("keytype", keytype);
        params.put("title", title);
        return params;
    }

    @Override
    public RequestParams getParamentersAddImage() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_IMAGE_AL_BMADD);
        params.put("token", SharePreferenceUtil.getString(getContext(), UserConfig.USER_TOKEN, ""));
        params.put("title", title);
        return params;
    }

    @Override
    public void excuteSuccessCallBack(ImageBean mCallBackVo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().size() > 0) {
            mListItem = mCallBackVo.getData();
            mList.clear();
            mList.addAll(mListItem);
            mAdapter.notifyDataSetChanged();
        } else {
            mList.clear();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void excuteSuccessSetCallBack(CallBackVo mCallBackVo) {
        mImageFragmentPresenter.getUserImageList();
    }

    protected class OnTabListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radiobutton2:// 修改
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setIs_set("1");
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
                case R.id.radiobutton3:// 删除
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setIs_set("2");
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }

    }

    private class TypeCheckBoxListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!radiobutton2.isChecked() && !radiobutton3.isChecked()) {
                typeY = 0;
                for (int i = 0; i < mList.size(); i++) {
                    mList.get(i).setIs_set("0");
                }
                mAdapter.notifyDataSetChanged();
                return;
            }
            switch (buttonView.getId()) {
                case R.id.radiobutton2:
                    if (isChecked) {
                        typeY = R.id.radiobutton2;
                        radiobutton3.setChecked(false);
                        for (int i = 0; i < mList.size(); i++) {
                            mList.get(i).setIs_set("1");
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                    break;
                case R.id.radiobutton3:
                    if (isChecked) {
                        typeY = R.id.radiobutton3;
                        radiobutton2.setChecked(false);
                        for (int i = 0; i < mList.size(); i++) {
                            mList.get(i).setIs_set("2");
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }
}

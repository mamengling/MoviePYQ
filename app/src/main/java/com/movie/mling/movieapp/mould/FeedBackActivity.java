package com.movie.mling.movieapp.mould;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.FeedBackAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.FeedBackActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.FeedBackBean;
import com.movie.mling.movieapp.presenter.FeedBackActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/16 14:24
 * $DESE$
 */
public class FeedBackActivity extends BaseCompatActivity implements FeedBackActivityView, View.OnClickListener {
    private FeedBackActivityPresenter mFeedBackActivityPresenter;
    private GridView grid_view;
    private EditText editTextt;
    private FeedBackAdapter mAdapter;
    private List<FeedBackBean> mList;
    //	1:更换地点 2:筹备完毕 3:定位有误 4:信息有误 5:暂停筹备 6:其他
    private String[] listName = {"更换地点", "筹备完毕", "定位有误", "信息有误", "暂停筹备", "其他"};
    private String[] listNameID = {"1", "2", "3", "4", "5", "6"};
    private String changeNameIndex = "";
    private String keyid;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mFeedBackActivityPresenter = new FeedBackActivityPresenter(this);
        titleBar.setTitleName("意见反馈");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(FeedBackActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void onCreateViewContent(View view) {
        grid_view = view.findViewById(R.id.grid_view);
        editTextt = view.findViewById(R.id.editTextt);
        view.findViewById(R.id.btn_save).setOnClickListener(this);
    }

    @Override
    protected void getExras() {
        keyid = getIntent().getStringExtra("keyid");
    }

    @Override
    protected void setListener() {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.clear();
        for (int i = 0; i < listName.length; i++) {
            FeedBackBean item = new FeedBackBean();
            item.setId(listNameID[i]);
            item.setName(listName[i]);
            mList.add(item);
        }
        mAdapter = new FeedBackAdapter(mList, this);
        grid_view.setAdapter(mAdapter);
        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                changeNameIndex = mList.get(i).getId();
                mAdapter.changeState(i);
            }
        });
    }

    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_FEEDBOOK);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        params.put("keyid", keyid);
        params.put("keytype", changeNameIndex);
        params.put("remark", editTextt.getText().toString());
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
    public void excuteSuccessCallBack(CallBackVo mCallBackVo) {
        MDialog.getInstance(this).showToast("提交成功");
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                mFeedBackActivityPresenter.getPutFeedBook();
                break;
        }
    }
}

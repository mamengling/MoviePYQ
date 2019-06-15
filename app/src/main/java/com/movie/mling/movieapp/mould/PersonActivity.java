package com.movie.mling.movieapp.mould;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.NoticeListAdapter;
import com.movie.mling.movieapp.adapter.PersonAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.PersonActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.NearBean;
import com.movie.mling.movieapp.mode.bean.PersonBean;
import com.movie.mling.movieapp.presenter.PersonActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.DensityUtil;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.RecycleViewDivider;
import com.movie.mling.movieapp.utils.widget.TitleBar;
import com.movie.mling.movieapp.utils.widget.YRecycleview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MLing on 2018/4/17 0017.
 */

public class PersonActivity extends BaseCompatActivity implements PersonActivityView {
    private PersonActivityPresenter mPersonActivityPresenter;
    private YRecycleview yrecycle_view;
    private PersonAdapter mAdapter;
    private int intNumberPage = 0;
    private int intHandler = 101;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mPersonActivityPresenter = new PersonActivityPresenter(this);
        titleBar.setTitleName("附近用户");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(PersonActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_person;
    }

    @Override
    protected void onCreateViewContent(View view) {
        yrecycle_view = view.findViewById(R.id.yrecycle_view);
    }

    @Override
    protected void getExras() {

    }

    @Override
    protected void setListener() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yrecycle_view.setLayoutManager(layoutManager);
        //水平分割线
        yrecycle_view.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, DensityUtil.dip2px(this, 5), getResources().getColor(R.color.content_view_bg)));
        mAdapter = new PersonAdapter(this);
        yrecycle_view.setAdapter(mAdapter);
        yrecycle_view.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                intHandler = 101;
                intNumberPage = 0;
                mPersonActivityPresenter.getNearList();
            }

            @Override
            public void onLoadMore() {
                intHandler = 102;
                intNumberPage += 1;
                mPersonActivityPresenter.getNearList();
            }
        });
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                Map<String, String> map = new HashMap<>();
                map.put("user_uid", content);
                ActivityAnim.intentActivity(PersonActivity.this, UserInfoActivity.class, map);
            }
        });
    }

    @Override
    protected void fromNetGetData() {
        mPersonActivityPresenter.getNearList();
    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_NEAR_USER);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
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
        switch (intHandler) {
            case 101:
                yrecycle_view.setReFreshComplete();
                break;
            case 102:
                yrecycle_view.setloadMoreComplete();
                break;
        }
        MDialog.getInstance(this).showToast(mCallBackVo.getMessage());
    }

    @Override
    public void excuteSuccessBack(PersonBean userVo) {
        switch (intHandler) {
            case 101:
                if (userVo != null && userVo.getData() != null && userVo.getData() != null && userVo.getData().size() > 0) {
                    mAdapter.onReference(userVo.getData());
                }
                yrecycle_view.setReFreshComplete();
                break;
            case 102:
                if (userVo.getData()!= null && userVo.getData().size() > 0) {
                    mAdapter.addOnReference(userVo.getData());
                }
                yrecycle_view.setloadMoreComplete();
                break;
        }
    }
}

package com.movie.mling.movieapp.mould;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.UserMarkAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.UserMarkActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.UserMarkBean;
import com.movie.mling.movieapp.presenter.UserMarkActivityPresenter;
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
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2019/2/16 11:19
 */

public class UserMarkActivity extends BaseCompatActivity implements UserMarkActivityView {
    private UserMarkActivityPresenter mPresenter;
    private YRecycleview yrecycle_view;
    private UserMarkAdapter mAdapter;
    private int intHandler = 101;
    private int intNumberPage = 0;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mPresenter = new UserMarkActivityPresenter(this);
        titleBar.setTitleName("工作备注");
        titleBar.setRightEdit(0, "添加");
        titleBar.setShowIcon(true, true, false);
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(UserMarkActivity.this);
                        break;
                    case TitleBar.clickEdt:
                        ActivityAnim.intentActivity(UserMarkActivity.this, MarkEdtActivity.class, null);
                        break;
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_user_mark;
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
                this, LinearLayoutManager.HORIZONTAL, DensityUtil.dip2px(this, 1), getResources().getColor(R.color.white)));
        mAdapter = new UserMarkAdapter(this);
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                Map<String, String> map = new HashMap<>();
                map.put("markid", content);
                map.put("newstext", mAdapter.getList().get(position).getNewstext());
                ActivityAnim.intentActivity(UserMarkActivity.this, MarkEdtActivity.class, map);
            }
        });
        yrecycle_view.setAdapter(mAdapter);
        yrecycle_view.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                intHandler = 101;
                intNumberPage = 0;
                mPresenter.getMarkList();
            }

            @Override
            public void onLoadMore() {
                intHandler = 102;
                intNumberPage += 1;
                mPresenter.getMarkList();
            }
        });
    }

    @Override
    protected void fromNetGetData() {
        mPresenter.getMarkList();
    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_SERVICE_NOT);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        params.put("page", intNumberPage + "");
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
    public void excuteSuccessCallBack(UserMarkBean mCallBackVo) {
        switch (intHandler) {
            case 101:
                if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getList() != null && mCallBackVo.getData().getList().size() > 0) {
                    mAdapter.onReference(mCallBackVo.getData().getList());
                }
                yrecycle_view.setReFreshComplete();
                break;
            case 102:
                if (mCallBackVo.getData().getList() != null && mCallBackVo.getData().getList().size() > 0) {
                    mAdapter.addOnReference(mCallBackVo.getData().getList());
                }
                yrecycle_view.setloadMoreComplete();
                break;
        }
    }

}

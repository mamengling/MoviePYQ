package com.movie.mling.movieapp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.NoticeListAdapter;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.iactivityview.NoticeFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MsgNoticeBean;
import com.movie.mling.movieapp.mould.MovieInfoActivity;
import com.movie.mling.movieapp.presenter.NoticeFragmentPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
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
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/16 11:06
 * $DESE$
 */
public class NoticeFragment extends BaseFragment implements NoticeFragmentView {
    private NoticeFragmentPresenter mNoticeFragmentPresenter;
    private YRecycleview yrecycle_view;
    private String url;
    private String keyType;
    private NoticeListAdapter mAdapter;
    private int intHandler = 101;
    private int intNumberPage = 0;

    public static NoticeFragment newInstance(String url, String keyType) {
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("keyType", keyType);
        NoticeFragment fragment = new NoticeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            mNoticeFragmentPresenter.getMsgList();
        }
    }
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        url = getArguments().getString("url");
        keyType = getArguments().getString("keyType");
        mNoticeFragmentPresenter = new NoticeFragmentPresenter(this);
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.feagment_notice;
    }

    @Override
    protected void onCreateViewContent(View view) {
        yrecycle_view = view.findViewById(R.id.yrecycle_view);
    }

    @Override
    protected void fromNetGetData() {
        mNoticeFragmentPresenter.getMsgList();
    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    protected void setListener() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yrecycle_view.setLayoutManager(layoutManager);
        //水平分割线
        yrecycle_view.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, DensityUtil.dip2px(getActivity(), 5), getResources().getColor(R.color.content_view_bg)));
        mAdapter = new NoticeListAdapter(getActivity());
        yrecycle_view.setAdapter(mAdapter);
        yrecycle_view.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                intHandler = 101;
                intNumberPage = 0;
                mNoticeFragmentPresenter.getMsgList();
            }

            @Override
            public void onLoadMore() {
                intHandler = 102;
                intNumberPage += 1;
                mNoticeFragmentPresenter.getMsgList();
            }
        });
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                Map<String, String> map = new HashMap<>();
                map.put("movieID", content);
                ActivityAnim.intentActivity(getActivity(), MovieInfoActivity.class, map);
            }
        });
    }

    @Override
    public RequestParams getParamentersDo() {
        return null;
    }

    @Override
    public void excuteSuccessCallBack(MsgNoticeBean mCallBackVo) {
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

    @Override
    public void excuteSuccessDoCallBack(CallBackVo mCallBackVo) {

    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(url);
        params.put("token", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_TOKEN, ""));
        params.put("keytype", keyType);
        params.put("page", intNumberPage + "");
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
        MDialog.getInstance(getActivity()).showToast(mCallBackVo.getMessage());
    }
}

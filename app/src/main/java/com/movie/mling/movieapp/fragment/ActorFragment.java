package com.movie.mling.movieapp.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.ActorListAdapater;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.iactivityview.ActorFragmentView;
import com.movie.mling.movieapp.mode.bean.ActorBean;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mould.ActorInfoActivity;
import com.movie.mling.movieapp.presenter.ActorFragmentPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;
import com.movie.mling.movieapp.utils.widget.YRecycleview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MLing on 2018/4/27 0027.
 */

public class ActorFragment extends BaseFragment implements ActorFragmentView, View.OnClickListener {
    private ActorFragmentPresenter mActorFragmentPresenter;
    private YRecycleview yrecycle_view;
    private String url;
    private String token;
    private String uuid;
    private String islast = "";
    private int intHandler = 101;
    private int intNumberPage = 0;
    private ActorListAdapater mAdapter;
    private String type;
    private ArrayList<ActorBean.DataBean.ListBean> mList = new ArrayList<>();

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mActorFragmentPresenter.getList(url);
        }
    }

    public static ActorFragment newInstance(String url, String token, String uuid, String type) {
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("token", token);
        args.putString("uuid", uuid);
        args.putString("type", type);
        ActorFragment fragment = new ActorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * @param titleBar 设置标题栏
     */
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mActorFragmentPresenter = new ActorFragmentPresenter(this);
        titleBar.setVisibility(View.GONE);
        url = getArguments().getString("url");
        token = getArguments().getString("token");
        uuid = getArguments().getString("uuid");
        type = getArguments().getString("type");
    }

    /**
     * @return 设置页面布局
     */
    @Override
    protected int onCreateViewId() {
        return R.layout.fragment_actor;
    }

    /**
     * 设置页面布局实例化
     *
     * @param view
     */
    @Override
    protected void onCreateViewContent(View view) {
        yrecycle_view = view.findViewById(R.id.yrecycle_view);
    }

    /**
     * 获取数据方法，之后，View赋值
     */
    @Override
    protected void fromNetGetData() {
        islast = "";
        mActorFragmentPresenter.getList(url);
    }

    /**
     * 获取数据方法，之后，View赋值
     */
    @Override
    protected void fromNotMsgReference() {
        mActorFragmentPresenter.getList(url);
    }

    /**
     * 监听事件
     */
    @Override
    protected void setListener() {
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mList.get(position).getId() == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        yrecycle_view.setLayoutManager(layoutManager);
        yrecycle_view.setLoadMoreEnabled(false);
        yrecycle_view.setReFreshEnabled(true);
        mAdapter = new ActorListAdapater(getActivity());
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                Map<String, String> map = new HashMap<>();
                map.put("actorID", content);
                map.put("actortitle", mAdapter.getList().get(position).getXingming());
                ActivityAnim.intentActivity(getActivity(), ActorInfoActivity.class, map);
            }
        });
        yrecycle_view.setAdapter(mAdapter);
        yrecycle_view.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                intHandler = 101;
                intNumberPage = 0;
                mActorFragmentPresenter.getList(url);
            }

            @Override
            public void onLoadMore() {
                intHandler = 102;
                intNumberPage += 1;
                mActorFragmentPresenter.getList(url);
            }
        });
    }

    @Override
    public void excuteSuccessCallBack(ActorBean mCallBackVo) {
        switch (intHandler) {
            case 101:
                if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getList() != null && mCallBackVo.getData().getList().size() > 0) {
                    mList.clear();
                    mList.addAll(mCallBackVo.getData().getList());
                    mAdapter.onReference(mCallBackVo.getData().getList());
                    LogUtil.i("put", "======================" + mCallBackVo.getData().getList().size());
//                    if (TextUtils.equals("2", type)) {
//                        if (mCallBackVo.getData().getList().size() <= 20) {
//                            btn_save.setVisibility(View.VISIBLE);
//                        } else {
//                            btn_save.setVisibility(View.GONE);
//                        }
//                    }

                }
                yrecycle_view.setReFreshComplete();
                break;
            case 102:
                if (mCallBackVo.getData().getList() != null && mCallBackVo.getData().getList().size() > 0) {
                    mList.addAll(mCallBackVo.getData().getList());
                    mAdapter.addOnReference(mCallBackVo.getData().getList());
                } else {
                }
                yrecycle_view.setloadMoreComplete();
                break;
        }
    }

    /**
     * 获取参数
     *
     * @return
     */
    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(url);
        params.put("token", token);
        params.put("page", intNumberPage);
        params.put("uuid", uuid);
        params.put("islast", islast);
        return params;
    }

    /**
     * 显示操作进度
     */
    @Override
    public void showProgress() {

    }

    /**
     * 关闭进度
     */
    @Override
    public void closeProgress() {

    }

    /**
     * 失败回调
     *
     * @param mCallBackVo
     */
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                islast = "1";
                intHandler = 101;
                intNumberPage = 0;
                mActorFragmentPresenter.getList(url);
                break;
        }
    }
}

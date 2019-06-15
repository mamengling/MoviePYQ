package com.movie.mling.movieapp.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MLing on 2018/4/27 0027.
 */

public class ActorRoundFragment extends BaseFragment implements ActorFragmentView, View.OnClickListener {
    private ActorFragmentPresenter mActorFragmentPresenter;
    private RecyclerView yrecycle_view;
    private String url;
    private String token;
    private String uuid;
    private String islast = "";
    private ActorListAdapater mAdapter;
    private String type;
    private GridLayoutManager layoutManager;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mActorFragmentPresenter.getList(url);
        }
    }

    public static ActorRoundFragment newInstance(String url, String token, String uuid, String type) {
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("token", token);
        args.putString("uuid", uuid);
        args.putString("type", type);
        ActorRoundFragment fragment = new ActorRoundFragment();
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
        return R.layout.fragment_round_actor;
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
        layoutManager = new GridLayoutManager(getActivity(), 2);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = yrecycle_view.getAdapter().getItemViewType(position);
                if (type == 1) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        yrecycle_view.setLayoutManager(layoutManager);
        mAdapter = new ActorListAdapater(getActivity());
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                if (ClickType == 1) {
                    islast = "1";
                    mActorFragmentPresenter.getList(url);
                } else {
                    Map<String, String> map = new HashMap<>();
                    map.put("actorID", content);
                    map.put("actortitle", mAdapter.getList().get(position).getXingming());
                    ActivityAnim.intentActivity(getActivity(), ActorInfoActivity.class, map);
                }
            }
        });
        yrecycle_view.setAdapter(mAdapter);
    }

    @Override
    public void excuteSuccessCallBack(ActorBean mCallBackVo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getList() != null && mCallBackVo.getData().getList().size() > 0) {
            ActorBean.DataBean.ListBean item = new ActorBean.DataBean.ListBean();
            item.setId(0);
            mCallBackVo.getData().getList().add(item);
            mAdapter.onReference(mCallBackVo.getData().getList());
            layoutManager.scrollToPositionWithOffset(0, 0);
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
        MDialog.getInstance(getActivity()).showToast(mCallBackVo.getMessage());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:

                break;
        }
    }
}

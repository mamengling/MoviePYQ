package com.movie.mling.movieapp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.MovieListAdapter;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.iactivityview.MovieListFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MovieBean;
import com.movie.mling.movieapp.mould.MovieInfoActivity;
import com.movie.mling.movieapp.presenter.MovieListFragmentPresenter;
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
 * 创建时间 ：2018/3/2 09:41
 * $DESE$
 */
public class MovieListFragment extends BaseFragment implements MovieListFragmentView {
    private MovieListFragmentPresenter mMovieListFragmentPresenter;
    private YRecycleview yrecycle;
    private MovieListAdapter mAdapter;
    private String url;
    private String keytype;
    private String uid;
    private String isgood;
    private int intHandler = 101;
    private int intNumberPage = 0;


    public static MovieListFragment newInstance(String url, String keytype, String uid, String isgood) {
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("keytype", keytype);
        args.putString("uid", uid);
        args.putString("isgood", isgood);
        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            mMovieListFragmentPresenter.getMovieList(url);
        }
    }
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setVisibility(View.GONE);
        mMovieListFragmentPresenter = new MovieListFragmentPresenter(this);
        url = getArguments().getString("url");
        keytype = getArguments().getString("keytype");
        uid = getArguments().getString("uid");
        isgood = getArguments().getString("isgood");
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.fragment_movie_list;
    }

    @Override
    protected void onCreateViewContent(View view) {
        yrecycle = view.findViewById(R.id.yrecycle_view);
    }

    @Override
    protected void fromNetGetData() {
        mMovieListFragmentPresenter.getMovieList(url);
    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    protected void setListener() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yrecycle.setLayoutManager(layoutManager);
        //水平分割线
        yrecycle.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, DensityUtil.dip2px(getActivity(), 1), getResources().getColor(R.color.content_view_bg)));
        mAdapter = new MovieListAdapter(getActivity());
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                Map<String, String> map = new HashMap<>();
                map.put("movieID", content);
                ActivityAnim.intentActivity(getActivity(), MovieInfoActivity.class, map);
            }
        });
        yrecycle.setAdapter(mAdapter);
        yrecycle.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                intHandler = 101;
                intNumberPage = 0;
                mMovieListFragmentPresenter.getMovieList(url);
            }

            @Override
            public void onLoadMore() {
                intHandler = 102;
                intNumberPage += 1;
                mMovieListFragmentPresenter.getMovieList(url);
            }
        });
    }

    @Override
    public void excuteSuccessCallBack(MovieBean mCallBackVo) {
        switch (intHandler) {
            case 101:
                if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getList() != null && mCallBackVo.getData().getList().size() > 0) {
                    mAdapter.onReference(mCallBackVo.getData().getList());
                }
                yrecycle.setReFreshComplete();
                break;
            case 102:
                if (mCallBackVo.getData().getList() != null && mCallBackVo.getData().getList().size() > 0) {
                    mAdapter.addOnReference(mCallBackVo.getData().getList());
                }
                yrecycle.setloadMoreComplete();
                break;
        }
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(url);
        params.put("token", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_TOKEN, ""));
        params.put("isgood", isgood + "");
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
                yrecycle.setReFreshComplete();
                break;
            case 102:
                yrecycle.setloadMoreComplete();
                break;
        }
        MDialog.getInstance(getActivity()).showToast(mCallBackVo.getMessage());
    }
}

package com.movie.mling.movieapp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.MovieBannerListAdapter;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.base.MovieBannerBean;
import com.movie.mling.movieapp.iactivityview.MovieNewListFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MovieBean;
import com.movie.mling.movieapp.mode.bean.MovieNewBean;
import com.movie.mling.movieapp.mould.MovieInfoActivity;
import com.movie.mling.movieapp.mould.WebviewActivity;
import com.movie.mling.movieapp.presenter.MovieNewListFragmentPresenter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/2 10:08
 * $DESE$
 */
public class MovieNewFragment extends BaseFragment implements MovieNewListFragmentView {
    public YRecycleview yrecycle_view;
    private MovieNewListFragmentPresenter mMovieListFragmentPresenter;
    private MovieBannerListAdapter mAdapter;
    private String url;
    private String keytype;
    private String bannerKeytype;
    private String uid;
    private String isgood;
    private int intHandler = 101;
    private int intNumberPage = 0;
    private List<MovieBean.DataBean.ListBean> mList = new ArrayList<>();
    private List<MovieBean.DataBean.ListBean> mListItem;

    public static MovieNewFragment newInstance(String url, String keytype, String uid, String isgood, String bannerKeytype) {

        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("keytype", keytype);
        args.putString("bannerKeytype", bannerKeytype);
        args.putString("uid", uid);
        args.putString("isgood", isgood);
        MovieNewFragment fragment = new MovieNewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mMovieListFragmentPresenter.getMovieBannerList(Constants.APP_USER_SERVICE_BANNER);
        }
    }

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mMovieListFragmentPresenter = new MovieNewListFragmentPresenter(this);
        url = getArguments().getString("url");
        keytype = getArguments().getString("keytype");
        bannerKeytype = getArguments().getString("bannerKeytype");
        uid = getArguments().getString("uid");
        isgood = getArguments().getString("isgood");
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_movie_new;
    }

    @Override
    protected void onCreateViewContent(View view) {
        yrecycle_view = (YRecycleview) view.findViewById(R.id.yrecycle_view);
    }

    @Override
    protected void fromNetGetData() {
        mMovieListFragmentPresenter.getMovieBannerList(Constants.APP_USER_SERVICE_BANNER);
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
                getActivity(), LinearLayoutManager.HORIZONTAL, DensityUtil.dip2px(getActivity(), 1), getResources().getColor(R.color.content_view_bg)));
        mAdapter = new MovieBannerListAdapter(getActivity());
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                if (ClickType == 1) {
                    Map<String, String> map = new HashMap<>();
                    map.put("title", "娱乐");
                    map.put("flag", "1");
                    map.put("loadUrl", content);
                    ActivityAnim.intentActivity(getActivity(), WebviewActivity.class, map);
                } else {
                    Map<String, String> map = new HashMap<>();
                    map.put("movieID", content);
                    ActivityAnim.intentActivity(getActivity(), MovieInfoActivity.class, map);
                }
            }
        });
        yrecycle_view.setAdapter(mAdapter);
        yrecycle_view.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                intHandler = 101;
                intNumberPage = 0;
                mMovieListFragmentPresenter.getMovieBannerList(Constants.APP_USER_SERVICE_BANNER);
            }

            @Override
            public void onLoadMore() {
                intHandler = 102;
                intNumberPage += 1;
                mMovieListFragmentPresenter.getMovieBannerList(Constants.APP_USER_SERVICE_BANNER);
            }
        });
    }


    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(url);
        params.put("token", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_TOKEN, ""));
        params.put("keytype", keytype + "");
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
                yrecycle_view.setReFreshComplete();
                break;
            case 102:
                yrecycle_view.setloadMoreComplete();
                break;
        }
        MDialog.getInstance(getActivity()).showToast(mCallBackVo.getMessage());
    }

    @Override
    public RequestParams getParamentersBanner() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_SERVICE_BANNER);
        params.put("token", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_TOKEN, ""));
        params.put("keytype", bannerKeytype + "");
        return params;
    }

    @Override
    public void excuteSuccessCallBack(MovieBean.DataBean.ListBean bean, MovieBean mCallBackVo) {
        switch (intHandler) {
            case 101:
                mList.clear();
                if (bean.getListBannaer() != null && bean.getListBannaer().size() > 0) {
                    mList.add(bean);
                }
                if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getList() != null && mCallBackVo.getData().getList().size() > 0) {
                    mList.addAll(mCallBackVo.getData().getList());
                }
                mAdapter.onReference(mList);
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
    public void excuteSuccessDataCallBack(MovieNewBean.DataBean.ListBean bean, MovieNewBean userVo) {

    }


    @Override
    public void excuteSuccessCallBackBanner(MovieBannerBean userVo) {
        mMovieListFragmentPresenter.getMovieList(userVo, url);
    }
}

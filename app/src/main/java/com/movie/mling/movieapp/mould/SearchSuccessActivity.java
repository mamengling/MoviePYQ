package com.movie.mling.movieapp.mould;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.MovieListAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.SearchSuccessActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MovieBean;
import com.movie.mling.movieapp.presenter.MovieSearchFragmentPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.DensityUtil;
import com.movie.mling.movieapp.utils.common.MD5Utils;
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
 * 创建时间 ：2018/3/6 13:40
 * $DESE$
 */
public class SearchSuccessActivity extends BaseCompatActivity implements SearchSuccessActivityView {
    private MovieSearchFragmentPresenter mMovieSearchFragmentPresenter;
    private String keywords;
    private String keytype;
    private String isgood;
    private YRecycleview yrecycle_view;
    private int intNumberPage = 0;
    private String url = Constants.APP_USER_FILM_LIST;
    private int intHandler = 101;
    private MovieListAdapter mAdapter;
    private List<MovieBean.DataBean.ListBean> mList = new ArrayList<>();
    private List<MovieBean.DataBean.ListBean> mListItem;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mMovieSearchFragmentPresenter = new MovieSearchFragmentPresenter(this);
        titleBar.setTitleName("搜索结果");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(SearchSuccessActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_success_search;
    }

    @Override
    protected void onCreateViewContent(View view) {
        yrecycle_view = view.findViewById(R.id.yrecycle_view);
    }

    @Override
    protected void getExras() {
        keywords = getIntent().getStringExtra("keywords");
    }

    @Override
    protected void setListener() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yrecycle_view.setLayoutManager(layoutManager);
        //水平分割线
        yrecycle_view.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, DensityUtil.dip2px(this, 5), getResources().getColor(R.color.white)));
        mAdapter = new MovieListAdapter(this);
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                Map<String, String> map = new HashMap<>();
                map.put("movieID", content);
                ActivityAnim.intentActivity(SearchSuccessActivity.this, MovieInfoActivity.class, map);
            }
        });
        yrecycle_view.setAdapter(mAdapter);
        yrecycle_view.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                intHandler = 101;
                intNumberPage = 1;
                mMovieSearchFragmentPresenter.getMovieList(url);
            }

            @Override
            public void onLoadMore() {
                intHandler = 102;
                intNumberPage += 1;
                mMovieSearchFragmentPresenter.getMovieList(url);
            }
        });
        mMovieSearchFragmentPresenter.getMovieList(url);

    }

    @Override
    protected void fromNetGetData() {
    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public void excuteSuccessCallBack(MovieBean mCallBackVo) {
        switch (intHandler) {
            case 101:
                if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getList() != null && mCallBackVo.getData().getList().size() > 0) {
                    mList.clear();
                    mListItem = mCallBackVo.getData().getList();
                    mList.addAll(mListItem);
                    mAdapter.addOnReference(mList);
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
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(url);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        params.put("keytype", keytype + "");
        params.put("isgood", isgood + "");
        params.put("keyword", keywords + "");
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
        MDialog.getInstance(this).showToast(mCallBackVo.getMessage());
    }
}

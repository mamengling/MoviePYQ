package com.movie.mling.movieapp.mould;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.ActorListAdapater;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.SearchActorActivityView;
import com.movie.mling.movieapp.mode.bean.ActorBean;
import com.movie.mling.movieapp.mode.bean.ActorInfoBean;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.presenter.SearchActorActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;
import com.movie.mling.movieapp.utils.widget.YRecycleview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MLing on 2018/5/16 0016.
 */

public class SearchActorActivity extends BaseCompatActivity implements SearchActorActivityView {
    private SearchActorActivityPresenter mSearchActorActivityPresenter;
    private YRecycleview yrecycle_view;
    private ActorListAdapater mAdapter;
    private String xingbie = "";
    private String guoji = "";
    private String gongsi = "";
    private String nianling_min;
    private String nianling_max;
    private String shengao_min;
    private String shengao_max;
    private String tizhong_max;
    private String tizhong_min;
    private String techang;
    private String tags;
    private int intHandler = 101;
    private int intNumberPage = 0;

    /**
     * @param titleBar 设置标题栏
     */
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mSearchActorActivityPresenter = new SearchActorActivityPresenter(this);
        titleBar.setTitleName("筛选结果");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(SearchActorActivity.this);
                        break;
                }
            }
        });
    }

    /**
     * @return 设置页面布局
     */
    @Override
    protected int onCreateViewId() {
        return R.layout.activity_search_actor;
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
     * 本地传参
     */
    @Override
    protected void getExras() {
        xingbie= getIntent().getStringExtra("xingbie");
        guoji= getIntent().getStringExtra("guoji");
        gongsi= getIntent().getStringExtra("gongsi");
        nianling_min= getIntent().getStringExtra("nianling_min");
        nianling_max= getIntent().getStringExtra("nianling_max");
        shengao_min= getIntent().getStringExtra("shengao_min");
        shengao_max= getIntent().getStringExtra("shengao_max");
        tizhong_max= getIntent().getStringExtra("tizhong_max");
        tizhong_min= getIntent().getStringExtra("tizhong_min");
        techang= getIntent().getStringExtra("techang");
        tags= getIntent().getStringExtra("tags");
    }

    /**
     * 监听事件
     */
    @Override
    protected void setListener() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yrecycle_view.setLayoutManager(layoutManager);
        //水平分割线
//        yrecycle_view.addItemDecoration(new RecycleViewDivider(
//                getActivity(), LinearLayoutManager.HORIZONTAL, DensityUtil.dip2px(getActivity(), 5), getResources().getColor(R.color.white)));
        mAdapter = new ActorListAdapater(this);
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                Map<String, String> map = new HashMap<>();
                map.put("actorID", content);
                map.put("actortitle", mAdapter.getList().get(position).getXingming());
                ActivityAnim.intentActivity(SearchActorActivity.this, ActorInfoActivity.class, map);
            }
        });
        yrecycle_view.setAdapter(mAdapter);
        yrecycle_view.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                intHandler = 101;
                intNumberPage = 0;
                mSearchActorActivityPresenter.getSearchList(Constants.APP_USER_SERVICE_STAR_SEARCH);
            }

            @Override
            public void onLoadMore() {
                intHandler = 102;
                intNumberPage += 1;
                mSearchActorActivityPresenter.getSearchList(Constants.APP_USER_SERVICE_STAR_SEARCH);
            }
        });
    }

    /**
     * 获取数据方法，之后，View赋值
     */
    @Override
    protected void fromNetGetData() {
        mSearchActorActivityPresenter.getSearchList(Constants.APP_USER_SERVICE_STAR_SEARCH);
    }

    /**
     * 获取数据方法，之后，View赋值
     */
    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public void excuteSuccessCallBack(ActorBean mCallBackVo) {
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

    /**
     * 获取参数
     *
     * @return
     */
    @Override
    public RequestParams getParamenters() {
        RequestParams params= AppMethod.getMapParams(Constants.APP_USER_SERVICE_STAR_SEARCH);
        params.put("page",intNumberPage);
        params.put("xingming","");
        params.put("xingbie",xingbie);
        params.put("guoji",guoji);
        params.put("gongsi",gongsi);
        params.put("techang",techang);
        params.put("tags",tags);
        params.put("nianling_min",nianling_min);
        params.put("nianling_max",nianling_max);
        params.put("shengao_min",shengao_min);
        params.put("shengao_max",shengao_max);
        params.put("tizhong_min",tizhong_min);
        params.put("tizhong_max",tizhong_max);
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
        MDialog.getInstance(this).showToast(mCallBackVo.getMessage());
    }
}

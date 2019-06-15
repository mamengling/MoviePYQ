package com.movie.mling.movieapp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.MarkBannerListAdapter;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.base.MovieBannerBean;
import com.movie.mling.movieapp.iactivityview.MovieNewListFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MovieBean;
import com.movie.mling.movieapp.mode.bean.MovieNewBean;
import com.movie.mling.movieapp.presenter.MovieNewListFragmentPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.DensityUtil;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.RecycleViewDivider;
import com.movie.mling.movieapp.utils.widget.TitleBar;
import com.movie.mling.movieapp.utils.widget.YRecycleview;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2019/2/16 20:35
 */

public class MarkListFragment extends BaseFragment implements MovieNewListFragmentView {
    public YRecycleview yrecycle_view;
    private MovieNewListFragmentPresenter mPresenter;
    private String url;
    private String keytype;
    private String bannerKeytype;
    private String uid;
    private String isgood;
    private TextView btn_daochu;
    private TextView tv_count;
    private CheckBox checkbox;
    private int intNumberPage = 0;
    private MarkBannerListAdapter mAdapter;
    private int intHandler = 101;
    private List<MovieNewBean.DataBean.ListBean> mList = new ArrayList<>();
    private List<MovieNewBean.DataBean.ListBean> mListItem = new ArrayList<>();
    private UMShareListener mShareListener;
    private ShareAction mShareAction;
    private String loadUrl = "http://yingq.cc/index/export?id=";
    private String ids = "";
    private String mTitle;

    public static MarkListFragment newInstance(String url, String keytype, String uid, String isgood, String bannerKeytype) {

        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("keytype", keytype);
        args.putString("bannerKeytype", bannerKeytype);
        args.putString("uid", uid);
        args.putString("isgood", isgood);
        MarkListFragment fragment = new MarkListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mPresenter = new MovieNewListFragmentPresenter(this);
        url = getArguments().getString("url");
        keytype = getArguments().getString("keytype");
        bannerKeytype = getArguments().getString("bannerKeytype");
        uid = getArguments().getString("uid");
        isgood = getArguments().getString("isgood");
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.fragment_mark_list;
    }

    @Override
    protected void onCreateViewContent(View view) {
        yrecycle_view = view.findViewById(R.id.yrecycle_view);
        btn_daochu = view.findViewById(R.id.btn_daochu);
        tv_count = view.findViewById(R.id.tv_count);
        checkbox = view.findViewById(R.id.checkbox);
    }

    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    protected void setListener() {
        mPresenter.getMovieList(url);
        btn_daochu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mShareAction != null)
                    mShareAction.open();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yrecycle_view.setLayoutManager(layoutManager);
        //水平分割线
        yrecycle_view.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, DensityUtil.dip2px(getActivity(), 1), getResources().getColor(R.color.content_view_bg)));
        mAdapter = new MarkBannerListAdapter(getActivity());
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {

                if (ClickType == 2) {//2是时间 1是title
                    mAdapter.getList().get(position).setCheck(!mAdapter.getList().get(position).isCheck());
                    int length = 0;
                    int allLength = 0;
                    int p = 0;
                    boolean check = false;
                    for (int i = 0; i < mAdapter.getList().size(); i++) {
                        if (mAdapter.getList().get(i).getViewType() != 2) {
                            allLength += 1;
                        }

                        if (TextUtils.equals(content, mAdapter.getList().get(i).getTime()) && mAdapter.getList().get(i).getViewType() != 2) {
                            length += 1;
                        }

                        if (TextUtils.equals(content, mAdapter.getList().get(i).getTime()) && mAdapter.getList().get(i).getViewType() == 2) {
                            check = mAdapter.getList().get(i).isCheck();
                            p = i;
                        }
                    }
                    int a = 0;
                    int b = 0;
                    for (int i = 0; i < mAdapter.getList().size(); i++) {
                        if (TextUtils.equals(content, mAdapter.getList().get(i).getTime()) && mAdapter.getList().get(i).getViewType() != 2) {
                            if (mAdapter.getList().get(i).isCheck()) {
                                a += 1;
                            }

                            if (!mAdapter.getList().get(i).isCheck()) {
                                b += 1;
                            }
                        }
                    }

                    LogUtil.e("int", "p=" + p + "a=" + a + "b=" + b);
                    if (length == a) {
                        mAdapter.getList().get(p).setCheck(true);
                    } else {
                        mAdapter.getList().get(p).setCheck(false);
                    }
                    int c = 0;
                    ids = "";
                    for (int i = 0; i < mAdapter.getList().size(); i++) {
                        if (mAdapter.getList().get(i).getViewType() != 2 && mAdapter.getList().get(i).isCheck()) {
                            c += 1;
                            ids += mAdapter.getList().get(i).getId() + "_";
                        }
                    }
                    tv_count.setText("共计：" + c + "个通告");
                    if (c > 0) {
                        btn_daochu.setEnabled(true);
                    } else {
                        btn_daochu.setEnabled(false);
                    }
                    if (allLength == c) {
                        checkbox.setChecked(true);
                    } else {
                        checkbox.setChecked(false);
                    }
                    mAdapter.notifyDataSetChanged();

                } else if (ClickType == 1) {
                    mAdapter.getList().get(position).setCheck(!mAdapter.getList().get(position).isCheck());

                    for (int i = 0; i < mAdapter.getList().size(); i++) {
                        if (TextUtils.equals(content, mAdapter.getList().get(i).getTime()) && i != position) {
                            mAdapter.getList().get(i).setCheck(mAdapter.getList().get(position).isCheck());
                        }
                    }

                    int allLength = 0;
                    for (int i = 0; i < mAdapter.getList().size(); i++) {
                        if (mAdapter.getList().get(i).getViewType() != 2) {
                            allLength += 1;
                        }
                    }

                    int c = 0;
                    ids = "";
                    for (int i = 0; i < mAdapter.getList().size(); i++) {
                        if (mAdapter.getList().get(i).getViewType() != 2 && mAdapter.getList().get(i).isCheck()) {
                            c += 1;
                            ids += mAdapter.getList().get(i).getId() + "_";
                        }
                    }
                    tv_count.setText("共计：" + c + "个通告");
                    if (c > 0) {
                        btn_daochu.setEnabled(true);
                    } else {
                        btn_daochu.setEnabled(false);
                    }
                    if (allLength == c) {
                        checkbox.setChecked(true);
                    } else {
                        checkbox.setChecked(false);
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
        yrecycle_view.setAdapter(mAdapter);
        yrecycle_view.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                intHandler = 101;
                intNumberPage = 0;
                mPresenter.getMovieList(url);
            }

            @Override
            public void onLoadMore() {
                intHandler = 102;
                intNumberPage += 1;
                mPresenter.getMovieList(url);
            }
        });

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c = 0;

                for (int i = 0; i < mAdapter.getList().size(); i++) {
                    mAdapter.getList().get(i).setCheck(checkbox.isChecked());
                    if (mAdapter.getList().get(i).getViewType() != 2 && mAdapter.getList().get(i).isCheck()) {
                        c += 1;
                        ids += mAdapter.getList().get(i).getId() + "_";
                    }
                }

                tv_count.setText("共计：" + c + "个通告");
                if (c > 0) {
                    btn_daochu.setEnabled(true);
                } else {
                    btn_daochu.setEnabled(false);
                }
                mAdapter.notifyDataSetChanged();

            }
        });


        /*增加自定义按钮的分享面板*/
        mShareAction = new ShareAction(getActivity()).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.SINA)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (snsPlatform.mShowWord.equals("复制文本")) {
                            Toast.makeText(getContext(), "复制文本按钮", Toast.LENGTH_LONG).show();
                        } else if (snsPlatform.mShowWord.equals("复制链接")) {
                            Toast.makeText(getActivity(), "复制链接按钮", Toast.LENGTH_LONG).show();

                        } else {
                            UMWeb web = new UMWeb(loadUrl + ids + "&token=" + SharePreferenceUtil.getString(getActivity(), UserConfig.USER_TOKEN, ""));
                            web.setTitle("朋影圈App：跑组记录统计表");
                            web.setDescription("朋影圈App");
//                            web.setThumb(new UMImage(getContext(), group));
                            new ShareAction(getActivity()).withMedia(web)
                                    .setPlatform(share_media)
                                    .setCallback(mShareListener)
                                    .share();
                        }
                    }
                });
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(url);
        params.put("token", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_TOKEN, ""));
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
        MDialog.getInstance(getContext()).showToast(mCallBackVo.getMessage());
    }

    @Override
    public RequestParams getParamentersBanner() {
        return null;
    }

    @Override
    public void excuteSuccessCallBack(MovieBean.DataBean.ListBean bean, MovieBean userVo) {

    }

    @Override
    public void excuteSuccessDataCallBack(MovieNewBean.DataBean.ListBean bean, MovieNewBean mCallBackVo) {
        switch (intHandler) {
            case 101:
                mList.clear();
                if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData() != null && mCallBackVo.getData().size() > 0) {
                    for (int i = 0; i < mCallBackVo.getData().size(); i++) {
                        if (!TextUtils.isEmpty(mCallBackVo.getData().get(i).getTime())) {
                            MovieNewBean.DataBean.ListBean bean1 = new MovieNewBean.DataBean.ListBean();
                            bean1.setViewType(2);
                            bean1.setTime(mCallBackVo.getData().get(i).getTime());
                            mList.add(bean1);
                        }
                        for (int j = 0; j < mCallBackVo.getData().get(i).getList().size(); j++) {
                            mCallBackVo.getData().get(i).getList().get(j).setTime(mCallBackVo.getData().get(i).getTime());
                        }
                        mList.addAll(mCallBackVo.getData().get(i).getList());
                    }
                    mAdapter.onReference(mList);
                }
                yrecycle_view.setReFreshComplete();
                break;
            case 102:
                mListItem.clear();
                if (mCallBackVo.getData() != null && mCallBackVo.getData().size() > 0) {
                    for (int i = 0; i < mCallBackVo.getData().size(); i++) {
                        if (!TextUtils.isEmpty(mCallBackVo.getData().get(i).getTime())) {
                            MovieNewBean.DataBean.ListBean bean1 = new MovieNewBean.DataBean.ListBean();
                            bean1.setViewType(2);
                            bean1.setTime(mCallBackVo.getData().get(i).getTime());
                            mListItem.add(bean1);
                        }
                        for (int j = 0; j < mCallBackVo.getData().get(i).getList().size(); j++) {
                            mCallBackVo.getData().get(i).getList().get(j).setTime(mCallBackVo.getData().get(i).getTime());
                        }
                        mListItem.addAll(mCallBackVo.getData().get(i).getList());
                    }
                    mAdapter.addOnReference(mListItem);
                }
                yrecycle_view.setloadMoreComplete();
                break;
        }
    }

    @Override
    public void excuteSuccessCallBackBanner(MovieBannerBean userVo) {

    }
}

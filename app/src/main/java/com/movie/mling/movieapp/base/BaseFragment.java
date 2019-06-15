package com.movie.mling.movieapp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.MainPermissionsUtils;
import com.movie.mling.movieapp.utils.common.NetWorkUtils;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.widget.CustomButtonDialog;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import static android.content.ContentValues.TAG;
import static android.os.Build.ID;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/6/15 11:53
 * $DESE$
 */
public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    private View mViewFragment;
    /**
     * 1网络错误，2没有数据，3存在问题,0正常
     */
    private int mState;
    protected TitleBar viewTitleBar;
    private FrameLayout viewContent;
    private LinearLayout mEmptyLayout;
    private TextView mEmptyTvHint;
    private View viewNetError, viewLoading;
    private int viewContentId;//viewPageLayoutId
    private TextView viewNetErrorRefresh;
    private BaseFragment forResultChildFragment;

    /**
     * Fragment 关联到 Activity 时回调
     * 此时 Activity 已经与 Fragment 关联，通过 Context 向下转型，就可以与 Activity 通信
     * 当然也可以使用 getActivity(),前提是这个 fragment 已经和宿主的 activity 关联，并且没有脱离
     * onAttach 只调用一次。
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        this.mContext = context;
        super.onAttach(context);
    }

    /**
     * 当 Fragment 调用 hide() 、 show() 时回调
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.i(TAG, "Fragment id = " + ID + "," + "mTitle" + " is onHiddenChanged.hidden = " + hidden);
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SharePreferenceUtil.getString(getActivity(), UserConfig.USER_DEVICE_IMEI, "") == null) {
            try {
                SharePreferenceUtil.setValue(mContext, UserConfig.USER_DEVICE_IMEI, AppMethod.getDeviceIMEI(mContext));
            } catch (Exception e) {
                final CustomButtonDialog dialog = new CustomButtonDialog(getActivity());
                dialog.setText("提示信息?\n当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。");
                dialog.setLeftButtonText("取消");
                dialog.setLeftButtonTextColor(R.color.colorAccent);
                dialog.setRightButtonText("确定");
                dialog.setRightButtonTextColor(R.color.colorAccent);
                dialog.setButtonListener(new CustomButtonDialog.OnButtonListener() {
                    @Override
                    public void onLeftButtonClick(CustomButtonDialog var1) {
                        dialog.cancel();
                    }

                    @Override
                    public void onRightButtonClick(CustomButtonDialog var1) {
                        dialog.cancel();
                        MainPermissionsUtils.startAppSettings(getActivity());
                    }
                });
            }
        }
        this.viewContentId = onCreateViewId();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewFragment = inflater.inflate(R.layout.base_page_title, container, false);
        initViewPage(mViewFragment, savedInstanceState);
        titleBarSet(viewTitleBar);
        setListener();
        if (NetWorkUtils.isConnectedOrConnecting(mContext)) {
            viewContent.setVisibility(View.VISIBLE);
        } else {
            showViewNetError(true);
        }
        return mViewFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        fromNetGetData();
    }

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;


    /**
     * 实现Fragment数据的懒加载(此方法在onCreateView()方法之前调用)
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
// TODO Auto-generated method stub
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {

    }

    /**
     * 延迟加载
     */
    protected void lazyLoad() {

    }


    /**
     * @param titleBar 设置标题栏
     */
    protected abstract void titleBarSet(TitleBar titleBar);

    /**
     * @return 设置页面布局
     */
    protected abstract int onCreateViewId();

    /**
     * 设置页面布局实例化
     */
    protected abstract void onCreateViewContent(View view);

    /**
     * 设置页面布局实例化
     */
    protected void onCreateViewContent(View view, @Nullable Bundle savedInstanceState) {
    }

    /**
     * 获取数据方法，之后，View赋值
     */
    protected abstract void fromNetGetData();

    /**
     * 获取数据方法，之后，View赋值
     */
    protected abstract void fromNotMsgReference();

    /**
     * 监听事件
     */
    protected abstract void setListener();

    /**
     * @param id 子空间R.id, viewParent:id所在View
     * @return View 封装 View.findViewById()
     */
    protected View newViewById(int id, View viewParent) {
        return viewParent.findViewById(id);
    }

    /**
     * @param show 加载框,是否显示
     */
    protected void showViewLoading(boolean show) {
        if (viewLoading == null) return;
        if (show) {
            viewLoading.setVisibility(View.VISIBLE);
        } else {
            viewLoading.setVisibility(View.GONE);
        }
    }

    /**
     * @param show 空数据是否显示
     */
    protected void showEmptyView(boolean show) {
        if (mEmptyLayout == null) {
            mEmptyLayout = (LinearLayout) mViewFragment.findViewById(R.id.base_fragment_empty);
        }
        if (show) {
            mEmptyLayout.setVisibility(View.VISIBLE);
        } else {
            mEmptyLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 空数据,是否显示
     *
     * @param show 空数据页面提示信息
     */
    protected void showEmptyView(boolean show, String infoHint) {
        this.showEmptyView(show);
        if (TextUtils.isEmpty(infoHint)) {
            mEmptyTvHint.setText("暂无信息");
        } else {
            mEmptyTvHint.setText(infoHint);
        }
    }

    /**
     * 空数据,自定义布局样式
     *
     * @param view 自定义显示视图
     * @param show 是否显示
     */
    protected void showEmptyView(View view, boolean show) {
        try {
            this.showEmptyView(show);
            if (view != null) {
                mEmptyLayout.removeAllViews();
                mEmptyLayout.addView(view);
            }
        } catch (Exception e) {
        }
    }

    /**
     * 网络错误，是否显示
     *
     * @param show
     */
    protected void showViewNetError(boolean show) {
        if (show) {
            viewNetError.setVisibility(View.VISIBLE);
        } else {
            viewNetError.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化页面内容
     *
     * @param view
     */
    private void initViewPage(View view, @Nullable Bundle savedInstanceState) {
        try {
            viewTitleBar = (TitleBar) view.findViewById(R.id.base_fragment_title_bar);
            mEmptyLayout = (LinearLayout) newViewById(R.id.base_fragment_empty, view);
            mEmptyTvHint = (TextView) newViewById(R.id.base_fragment_empty_hintTv, view);
            viewContent = (FrameLayout) view.findViewById(R.id.base_fragment_content);
            viewNetError = newViewById(R.id.base_fragment_net_error, view);
            viewLoading = newViewById(R.id.base_fragment_loading, view);
            viewLoading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //设置监听事件
                }
            });
            mEmptyLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //设置监听事件
                    fromNotMsgReference();
                }
            });
            viewNetError.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fromNetGetData();
                }
            });
            View viewPage = LayoutInflater.from(mContext).inflate(viewContentId, viewContent, false);
            viewContent.addView(viewPage);
            onCreateViewContent(viewPage);
            onCreateViewContent(viewPage, savedInstanceState);
            mEmptyLayout.setVisibility(View.GONE);
            viewNetError.setVisibility(View.GONE);
        } catch (Exception e) {
            showViewNetError(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && parentFragment instanceof BaseFragment) {
            ((BaseFragment) parentFragment).startActivityForResultFromChildFragment(intent, requestCode, this);
        } else {
            forResultChildFragment = null;
            super.startActivityForResult(intent, requestCode);
        }
    }

    private void startActivityForResultFromChildFragment(Intent intent, int requestCode, BaseFragment childFragment) {
        forResultChildFragment = childFragment;

        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && parentFragment instanceof BaseFragment) {
            ((BaseFragment) parentFragment).startActivityForResultFromChildFragment(intent, requestCode, this);
        } else {
            super.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public final void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (forResultChildFragment != null) {
            forResultChildFragment.onActivityResult(requestCode, resultCode, data);
            forResultChildFragment = null;
        } else {
            onActivityResultNestedCompat(requestCode, resultCode, data);
        }
    }

    public void onActivityResultNestedCompat(int requestCode, int resultCode, Intent data) {

    }
}

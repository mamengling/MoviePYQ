package com.movie.mling.movieapp.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.utils.common.AppManager;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.MainPermissionsUtils;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.StatusBarUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.CustomButtonDialog;
import com.movie.mling.movieapp.utils.widget.TitleBar;
import com.umeng.analytics.MobclickAgent;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/6/14 15:09
 * $DESE$
 */
public abstract class BaseCompatActivity extends AppCompatActivity {
    protected Context mContext;
    private InputMethodManager manager = null;
    protected BaseApplication application;
    /**
     * 1网络错误，2没有数据，3存在问题,0正常
     */
    private int state;
    protected TitleBar viewTitleBar;
    private FrameLayout viewContent;
    private LinearLayout mEmptyLayout;
    private TextView mEmptyTvHint;
    private View viewNetError;
    protected View rootView;
    private int viewContentId;
    protected Bundle savedInstanceState;
    /**
     * 设置广播接收，区别操作
     */
    protected int whereId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        this.mContext = this;
        this.viewContentId = onCreateViewId();
        try {
            getExras();
        } catch (Exception e) {

        }
        setContentView(R.layout.base_page_title);
        initMainView();
        titleBarSet(viewTitleBar);
        setListener();
        //广播结束Activity.App
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(mContext);
        localBroadcastManager.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String strAction = intent.getAction();
                if (TextUtils.equals(strAction, "exitApp")) {
                    int goWhere = intent.getIntExtra("goWhere", 0);
                    if (goWhere == whereId) {
                        //2.设置搜索页面关闭
                        finish();
                    } else if (goWhere == 1) {
                        finish();
                    } else if (goWhere == 5) {
                        MDialog.getInstance(BaseApplication.getContext()).showToast(viewContent, "设备在其他终端登陆，请重新登陆");
//                        Intent intent1 = new Intent(mContext, ChangeUserTypeActivity.class);
//                        startActivity(intent1);
//                        finish();
                    }
                }
            }
        }, new IntentFilter("exitApp"));
        if (SharePreferenceUtil.getString(mContext, UserConfig.USER_DEVICE_IMEI, "") == null) {
            try {
                SharePreferenceUtil.setValue(mContext, UserConfig.USER_DEVICE_IMEI, AppMethod.getDeviceIMEI(mContext));
            } catch (Exception e) {
                final CustomButtonDialog dialog = new CustomButtonDialog(mContext);
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
                        MainPermissionsUtils.startAppSettings(mContext);
                    }
                });
            }
        }
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar(true);
    }

    /**
     * 设置状态栏
     */
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
    }

    @Override
    public void onResume() {
        fromNetGetData();
        super.onResume();
        MobclickAgent.onResume(this);
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
     * 本地传参
     */
    protected abstract void getExras();

    /**
     * 监听事件
     */
    protected abstract void setListener();

    /**
     * 获取数据方法，之后，View赋值
     */
    protected abstract void fromNetGetData();

    /**
     * 获取数据方法，之后，View赋值
     */
    protected abstract void fromNotMsgReference();

    /**
     * @param id 封装 View.findViewById()
     * @return View
     */
    protected View newViewById(int id) {
        return findViewById(id);
    }

    /**
     * @param id 封装 View.findViewById()
     * @return View
     */
    protected View newViewById(int id, View viewParent) {
        return viewParent.findViewById(id);
    }

    /**
     * 空数据,是否显示
     *
     * @param show
     */
    protected void showEmptyView(boolean show) {
        if (mEmptyLayout == null) {
            mEmptyLayout = (LinearLayout) findViewById(R.id.base_fragment_empty);
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
     * @param view 空数据,自定义布局样式
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
     * @param show 网络错误，是否显示
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
     */
    private void initMainView() {
        try {
            viewTitleBar = (TitleBar) findViewById(R.id.base_fragment_title_bar);
            mEmptyLayout = (LinearLayout) newViewById(R.id.base_fragment_empty);
            mEmptyTvHint = (TextView) newViewById(R.id.base_fragment_empty_hintTv);
            viewContent = (FrameLayout) findViewById(R.id.base_fragment_content);
            viewNetError = newViewById(R.id.base_fragment_net_error);
            View mainView = View.inflate(mContext, viewContentId, null);
            rootView = findViewById(R.id.root_view);
            viewContent.addView(mainView);
            onCreateViewContent(mainView);
            mEmptyLayout.setVisibility(View.GONE);
            viewNetError.setVisibility(View.GONE);
        } catch (Exception e) {
            showViewNetError(true);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        /**
         * 点击空白处隐藏软键盘
         */
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                if (manager == null) {
                    manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                }
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public BaseApplication getApplicationContext() {
        return (BaseApplication) super.getApplicationContext();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }


    protected void setStatusBar(boolean needSet) {
        if (needSet) {
            setColor(this, getResources().getColor(R.color.colorAccent));
        }else {
            setColor(this, getResources().getColor(R.color.white));
        }
    }

    protected void setStatusBar(boolean needSet, int color) {
        if (needSet) {
            setColor(this, getResources().getColor(color));
        }
    }

    /**
     * 设置状态栏颜色 * * @param activity 需要设置的activity * @param color 状态栏颜色值
     */
    public static void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 生成一个状态栏大小的矩形
            View statusView = createStatusView(activity, color);
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

    /**
     * * 生成一个和状态栏大小相同的矩形条 * * @param activity 需要设置的activity * @param color
     * 状态栏颜色值 * @return 状态栏矩形条
     */
    private static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(
                resourceId);
        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }
}

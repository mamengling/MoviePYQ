package com.movie.mling.movieapp.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.mould.WebviewActivity;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MLing on 2018/8/13 0013.
 */

public class ZiXunChildFragment extends BaseFragment {
    private WebView webView;
    private ProgressBar progressBar;
    private String loadUrl;

    public static ZiXunChildFragment newInstance(String loadUrl, String title) {

        Bundle args = new Bundle();
        args.putString("loadUrl", loadUrl);
        args.putString("title", title);
        ZiXunChildFragment fragment = new ZiXunChildFragment();
        fragment.setArguments(args);
        return fragment;

    }

    /**
     * @param titleBar 设置标题栏
     */
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setVisibility(View.GONE);
        loadUrl = getArguments().getString("loadUrl");
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            webView.loadUrl(loadUrl);
        }
    }
    /**
     * @return 设置页面布局
     */
    @Override
    protected int onCreateViewId() {
        return R.layout.fragment_zixun_student_child;
    }

    /**
     * 设置页面布局实例化
     *
     * @param view
     */
    @Override
    protected void onCreateViewContent(View view) {
        webView = view.findViewById(R.id.web_view);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    }

    /**
     * 获取数据方法，之后，View赋值
     */
    @Override
    protected void fromNetGetData() {
        webView.loadUrl(loadUrl);
    }

    /**
     * 获取数据方法，之后，View赋值
     */
    @Override
    protected void fromNotMsgReference() {

    }

    /**
     * 监听事件
     */
    @Override
    protected void setListener() {
        webView.setWebViewClient(new WebViewClient() {
            //覆写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO 自动生成的方法存根
                LogUtil.i("WEBVIEW", url);
//                view.loadUrl(url);
                Map<String, String> map = new HashMap<>();
                map.put("title", "娱乐");
                map.put("flag", "1");
                map.put("loadUrl", url);
                ActivityAnim.intentActivity(getActivity(), WebviewActivity.class, map);
                return true;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                LogUtil.i("WEBVIEW", url);
            }
        });
        WebSettings seting = webView.getSettings();
        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
        seting.setUseWideViewPort(true);
        seting.setLoadWithOverviewMode(true);
        String userAgent = seting.getUserAgentString();
        seting.setUserAgentString(userAgent + "saxapp");
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
            seting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
            }
        });
    }
}

package com.movie.mling.movieapp.mould;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/7/7 14:39
 * $DESE$
 */
public class WebviewDefulitActivity extends BaseCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;
    private String title;
    private String loadUrl;
    private TitleBar mTitleBar;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mTitleBar = titleBar;
//        titleBar.setTitleName("webView");
        titleBar.setShowIcon(true, true, false);
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        mTitleBar.setTitleName(title);
                        if (webView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                            webView.goBack();
                        } else {
                            ActivityAnim.endActivity(WebviewDefulitActivity.this);
                        }
                        break;
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void onCreateViewContent(View view) {
        webView = (WebView) view.findViewById(R.id.web_view);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    }

    @Override
    protected void getExras() {
        title = getIntent().getStringExtra("title");
        loadUrl = getIntent().getStringExtra("loadUrl");
        LogUtil.i("loadUrl", loadUrl);
    }

    @Override
    protected void setListener() {
        webView.setWebViewClient(new WebViewClient() {
            //覆写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO 自动生成的方法存根
                view.loadUrl(url);
                mTitleBar.setTitleName(webView.getTitle());
                return true;
            }
        });
        WebSettings seting = webView.getSettings();
        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
        seting.setUseWideViewPort(true);
        seting.setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mTitleBar.setTitleName(webView.getTitle());
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
            }
        });
    }

    @Override
    protected void fromNetGetData() {
        webView.loadUrl(loadUrl);
    }

    @Override
    protected void fromNotMsgReference() {

    }

    //设置返回键动作（防止按返回键直接退出程序)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自动生成的方法存根
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                webView.goBack();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

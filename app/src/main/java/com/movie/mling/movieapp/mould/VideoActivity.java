package com.movie.mling.movieapp.mould;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

/**
 * Created by MLing on 2018/5/14 0014.
 */

public class VideoActivity extends BaseCompatActivity {
    private WebView webView;
    private String loadUrl;
    private TextView title_bar_back;

    /**
     * @param titleBar 设置标题栏
     */
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setVisibility(View.GONE);
    }

    /**
     * @return 设置页面布局
     */
    @Override
    protected int onCreateViewId() {
        return R.layout.activity_video;
    }

    /**
     * 设置页面布局实例化
     *
     * @param view
     */
    @Override
    protected void onCreateViewContent(View view) {
        webView = view.findViewById(R.id.web_view);
        title_bar_back = view.findViewById(R.id.title_bar_back);
    }

    /**
     * 本地传参
     */
    @Override
    protected void getExras() {
        loadUrl = getIntent().getStringExtra("loadUrl");
    }

    /**
     * 监听事件
     */
    @Override
    protected void setListener() {
        title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityAnim.endActivity(VideoActivity.this);
            }
        });
//        webView.setWebViewClient(new WebViewClient() {
//            //覆写shouldOverrideUrlLoading实现内部显示网页
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return true;
//            }
//        });
        WebSettings seting = webView.getSettings();
        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
        seting.setUseWideViewPort(true);
        seting.setLoadWithOverviewMode(true);
//        webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//
//            }
//        });
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

package com.movie.mling.movieapp.mould;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.ActorImageListAdapter;
import com.movie.mling.movieapp.adapter.VideoListAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.mode.bean.ActorInfoBean;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MLing on 2018/5/14 0014.
 */

public class VideoListActivity extends BaseCompatActivity {
    private RecyclerView recycler_view;
    private VideoListAdapter mAdapter;
    private ArrayList<ActorInfoBean.DataBean.VideoBean> videolist;
    private String videoname;
    private TitleBar mTitleBar;

    /**
     * @param titleBar 设置标题栏
     */
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setTitleName("视频");
        mTitleBar = titleBar;
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(VideoListActivity.this);
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
        return R.layout.activity_video_list;
    }

    /**
     * 设置页面布局实例化
     *
     * @param view
     */
    @Override
    protected void onCreateViewContent(View view) {
        recycler_view = view.findViewById(R.id.recycler_view);
    }

    /**
     * 本地传参
     */
    @Override
    protected void getExras() {
        videolist = getIntent().getParcelableArrayListExtra("videoList");
        videoname = getIntent().getStringExtra("videoname");
    }

    /**
     * 监听事件
     */
    @Override
    protected void setListener() {
        mTitleBar.setTitleName(videoname);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recycler_view.setLayoutManager(layoutManager);
        mAdapter = new VideoListAdapter(this);
        recycler_view.setAdapter(mAdapter);
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                Map<String, String> mapV = new HashMap<>();
                mapV.put("loadUrl", content);
                ActivityAnim.intentActivity(VideoListActivity.this, VideoActivity.class, mapV);
            }
        });
    }

    @Override
    protected void fromNetGetData() {
        mAdapter.onReference(videolist);
    }

    @Override
    protected void fromNotMsgReference() {

    }
}

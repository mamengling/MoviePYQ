package com.movie.mling.movieapp.mould;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.ActorImageListAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.mode.bean.ActorInfoBean;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;

/**
 * Created by MLing on 2018/8/20 0020.
 */

public class ActorImageActivity extends BaseCompatActivity {
    private RecyclerView recycler_view;
    private ActorImageListAdapter mAdapter;
    private ArrayList<ActorInfoBean.DataBean.AlbumBean> mList;

    /**
     * @param titleBar 设置标题栏
     */
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setTitleName("相册");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType){
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(ActorImageActivity.this);
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
        return R.layout.activity_actor_image;
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
        mList = getIntent().getParcelableArrayListExtra("imageList");
    }

    /**
     * 监听事件
     */
    @Override
    protected void setListener() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recycler_view.setLayoutManager(layoutManager);
        mAdapter = new ActorImageListAdapter(this);
        recycler_view.setAdapter(mAdapter);
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                ArrayList<String> items = new ArrayList<>();
                items.add(content);
                PictureConfig config = new PictureConfig.Builder()
                        .setListData(items)//图片数据List<String> list
                        .setPosition(position)//图片下标（从第position张图片开始浏览）
                        .setDownloadPath("pictureviewer")//图片下载文件夹地址
                        .setIsShowNumber(true)//是否显示数字下标
                        .needDownload(true)//是否支持图片下载
                        .setPlacrHolder(R.mipmap.ic_launcher)//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                        .build();
                ImagePagerActivity.startActivity(ActorImageActivity.this, config);
            }
        });
    }

    /**
     * 获取数据方法，之后，View赋值
     */
    @Override
    protected void fromNetGetData() {
        mAdapter.onReference(mList);
    }

    /**
     * 获取数据方法，之后，View赋值
     */
    @Override
    protected void fromNotMsgReference() {

    }
}

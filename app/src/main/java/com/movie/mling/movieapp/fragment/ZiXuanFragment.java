package com.movie.mling.movieapp.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.TabAdapter;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.utils.widget.TitleBar;

/**
 * Created by MLing on 2018/8/7 0007.
 */

public class ZiXuanFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

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
        return R.layout.fragment_zixun_student;
    }

    /**
     * 设置页面布局实例化
     *
     * @param view
     */
    @Override
    protected void onCreateViewContent(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabs_zixun);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_zixun);
    }

    /**
     * 获取数据方法，之后，View赋值
     */
    @Override
    protected void fromNetGetData() {

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
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }


    /**
     * 设置可滑动界面
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        TabAdapter adapter = new TabAdapter(getChildFragmentManager());
        adapter.addFragment(ZiXunChildFragment.newInstance("https://yingq.cc/news/index", "首页"), "首页");
        adapter.addFragment(ZiXunChildFragment.newInstance("https://yingq.cc/news/ent?cate=6", ""), "娱乐");
        adapter.addFragment(ZiXunChildFragment.newInstance("https://yingq.cc/news/ent?cate=7", ""), "影视");
        adapter.addFragment(ZiXunChildFragment.newInstance("https://yingq.cc/news/ent?cate=8", ""), "自媒体");
        adapter.addFragment(ZiXunChildFragment.newInstance("https://yingq.cc/news/ent?cate=9", ""), "视频");
        viewPager.setAdapter(adapter);
    }
}

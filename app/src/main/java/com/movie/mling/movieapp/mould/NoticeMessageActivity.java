package com.movie.mling.movieapp.mould;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.MyFragmentAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.fragment.NoticeFragment;
import com.movie.mling.movieapp.fragment.RequestFeagment;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;

/**
 * Created by MLing on 2018/5/11 0011.
 */

public class NoticeMessageActivity extends BaseCompatActivity {
    private ViewPager viewPager;
    private MyFragmentAdapter myFragmentAdapter;
    private ArrayList<Fragment> fragmentList;

    /**
     * @param titleBar 设置标题栏
     */
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setTitleName("通知");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(NoticeMessageActivity.this);
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
        return R.layout.activity_notice_message;
    }

    /**
     * 设置页面布局实例化
     *
     * @param view
     */
    @Override
    protected void onCreateViewContent(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
    }

    /**
     * 本地传参
     */
    @Override
    protected void getExras() {

    }

    /**
     * 监听事件
     */
    @Override
    protected void setListener() {
        myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fragmentList = new ArrayList<>();
        fragmentList.add(NoticeFragment.newInstance(Constants.APP_USER_MSG_LIST, "a"));//最新
        myFragmentAdapter.setList(fragmentList);
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

}

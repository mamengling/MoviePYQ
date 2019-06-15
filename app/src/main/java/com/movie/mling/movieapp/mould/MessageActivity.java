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

public class MessageActivity extends BaseCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private RadioButton radiobutton0;
    private RadioButton radiobutton1;
    private RadioGroup radioGroup;
    private MyFragmentAdapter myFragmentAdapter;
    private ArrayList<Fragment> fragmentList;

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
        return R.layout.activity_message;
    }

    /**
     * 设置页面布局实例化
     *
     * @param view
     */
    @Override
    protected void onCreateViewContent(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_full);
        radiobutton0 = (RadioButton) view.findViewById(R.id.radiobutton0);
        radiobutton1 = (RadioButton) view.findViewById(R.id.radiobutton1);
        view.findViewById(R.id.title_bar_back).setOnClickListener(this);
        radiobutton0.setChecked(true);
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
        radioGroup.setOnCheckedChangeListener(new OnTabListener());
        myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radiobutton0.setChecked(true);
                        break;
                    case 1:
                        radiobutton1.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fragmentList = new ArrayList<>();
        fragmentList.add(NoticeFragment.newInstance(Constants.APP_USER_MSG_LIST, "a"));//最新
        fragmentList.add(RequestFeagment.newInstance(Constants.APP_USER_MSG_LIST, "b"));//推荐
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                ActivityAnim.endActivity(this);
                break;
        }
    }

    protected class OnTabListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radiobutton0:// 通知
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.radiobutton1:// 请求
                    viewPager.setCurrentItem(1);
                    break;
            }
        }
    }
}

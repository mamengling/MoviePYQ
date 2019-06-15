package com.movie.mling.movieapp.mould;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.MyFragmentAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.fragment.MarkListFragment;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.NoScrollViewPager;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2019/2/15 18:31
 */

public class MarkListActivity extends BaseCompatActivity {
    private NoScrollViewPager viewPager;
    private RadioButton radiobutton0;
    private RadioButton radiobutton1;
    private RadioButton radiobutton2;
    private RadioButton radiobutton3;
    private RadioGroup radioGroup;
    private MyFragmentAdapter myFragmentAdapter;
    private ArrayList<Fragment> fragmentList;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setTitleName("工作总结");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(MarkListActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_mark_list;
    }

    @Override
    protected void onCreateViewContent(View view) {
        viewPager = (NoScrollViewPager) view.findViewById(R.id.viewPager);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_full);
        radiobutton0 = (RadioButton) view.findViewById(R.id.radiobutton0);
        radiobutton1 = (RadioButton) view.findViewById(R.id.radiobutton1);
        radiobutton2 = (RadioButton) view.findViewById(R.id.radiobutton2);
        radiobutton3 = (RadioButton) view.findViewById(R.id.radiobutton3);
        radiobutton0.setChecked(true);
    }

    @Override
    protected void getExras() {

    }

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
                    case 2:
                        radiobutton2.setChecked(true);
                        break;
                    case 3:
                        radiobutton3.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fragmentList = new ArrayList<>();
        fragmentList.add(MarkListFragment.newInstance(Constants.APP_USER_SERVICE_STAR_ZJALL, "0", "", "0","1"));//全部
        fragmentList.add(MarkListFragment.newInstance(Constants.APP_USER_SERVICE_STAR_ZJPAO, "0", "", "0","1"));//已跑
        fragmentList.add(MarkListFragment.newInstance(Constants.APP_USER_SERVICE_STAR_ZJFAV, "0", "", "0","1"));//跟进
        fragmentList.add(MarkListFragment.newInstance(Constants.APP_USER_SERVICE_STAR_ZJMARK, "0", "", "0","1"));//备注
        myFragmentAdapter.setList(fragmentList);
    }

    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }


    protected class OnTabListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radiobutton0:// 全部
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.radiobutton1:// 已跑
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.radiobutton2:// 跟进
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.radiobutton3:// 备注
                    viewPager.setCurrentItem(3);
                    break;
            }
        }
    }
}

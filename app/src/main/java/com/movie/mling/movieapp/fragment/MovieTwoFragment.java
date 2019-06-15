package com.movie.mling.movieapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.MyFragmentAdapter;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.mould.SearchActivity;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.NoScrollViewPager;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/5 15:00
 * $DESE$
 */
public class MovieTwoFragment extends BaseFragment implements View.OnClickListener {
    private NoScrollViewPager viewPager;
    private RadioButton radiobutton0;
    private RadioButton radiobutton1;
    private RadioButton radiobutton2;
    private RadioGroup radioGroup;
    private MyFragmentAdapter myFragmentAdapter;
    private ArrayList<Fragment> fragmentList;

    public static MovieTwoFragment newInstance(String url, String keytype, String uid, String isgood) {
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("keytype", keytype);
        args.putString("uid", uid);
        args.putString("isgood", isgood);
        MovieTwoFragment fragment = new MovieTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.fargment_movie_two;
    }

    @Override
    protected void onCreateViewContent(View view) {
        viewPager = (NoScrollViewPager) view.findViewById(R.id.viewPager);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_full);
        radiobutton0 = (RadioButton) view.findViewById(R.id.radiobutton0);
        radiobutton1 = (RadioButton) view.findViewById(R.id.radiobutton1);
        radiobutton2 = (RadioButton) view.findViewById(R.id.radiobutton2);
        radiobutton0.setChecked(true);
    }

    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    protected void setListener() {
        radioGroup.setOnCheckedChangeListener(new OnTabListener());
        myFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager());
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
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fragmentList = new ArrayList<>();
        fragmentList.add(MovieTwoChrildFragment.newInstance(Constants.APP_USER_FILM_LIST, "0", "", "0", "isgood", "1"));//平台推荐
        fragmentList.add(MovieTwoChrildFragment.newInstance(Constants.APP_USER_FILM_LIST, "1", "", "0", "digs", "1"));//用户推荐
        fragmentList.add(MovieTwoChrildFragment.newInstance(Constants.APP_USER_FILM_LIST, "2", "", "0", "onclick", "1"));//用户浏览
        myFragmentAdapter.setList(fragmentList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                ActivityAnim.intentActivity(getActivity(), SearchActivity.class, null);
                break;
//            case R.id.radiobutton6:
//                ActivityAnim.intentActivity(getActivity(), AddMovieActivity.class, null);
//                break;
        }
    }

    protected class OnTabListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radiobutton0:// 首页
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.radiobutton1:// 校招
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.radiobutton2:// 校招
                    viewPager.setCurrentItem(2);
                    break;
            }
        }
    }

}

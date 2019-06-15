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
import com.movie.mling.movieapp.mould.AddMovieActivity;
import com.movie.mling.movieapp.mould.LoginActivity;
import com.movie.mling.movieapp.mould.NearPeopleActivity;
import com.movie.mling.movieapp.mould.SearchActivity;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
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
public class MovieIndexFragment extends BaseFragment implements View.OnClickListener {
    private NoScrollViewPager viewPager;
    private RadioButton radiobutton0;
    private RadioButton radiobutton1;
    private RadioButton radiobutton2;
    private RadioButton radiobutton3;
    private RadioButton radiobutton4;
    private RadioButton radiobutton5;
    private RadioGroup radioGroup;
    private MyFragmentAdapter myFragmentAdapter;
    private ArrayList<Fragment> fragmentList;

    public static MovieIndexFragment newInstance(String url, String keytype, String uid, String isgood) {
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("keytype", keytype);
        args.putString("uid", uid);
        args.putString("isgood", isgood);
        MovieIndexFragment fragment = new MovieIndexFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.fargment_movie_index;
    }

    @Override
    protected void onCreateViewContent(View view) {
        viewPager = (NoScrollViewPager) view.findViewById(R.id.viewPager);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_full);
        radiobutton0 = (RadioButton) view.findViewById(R.id.radiobutton0);
        radiobutton1 = (RadioButton) view.findViewById(R.id.radiobutton1);
        radiobutton2 = (RadioButton) view.findViewById(R.id.radiobutton2);
        radiobutton3 = (RadioButton) view.findViewById(R.id.radiobutton3);
        radiobutton4 = (RadioButton) view.findViewById(R.id.radiobutton4);
        radiobutton5 = (RadioButton) view.findViewById(R.id.radiobutton5);
        view.findViewById(R.id.tv_search).setOnClickListener(this);
        view.findViewById(R.id.add_movie).setOnClickListener(this);
        view.findViewById(R.id.add_location).setOnClickListener(this);
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
                    case 3:
                        radiobutton3.setChecked(true);
                        break;
                    case 4:
                        radiobutton5.setChecked(true);
                        break;
                    case 5:
                        radiobutton4.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });//1电视剧,2电影 3网剧 4其他 空:全部
        fragmentList = new ArrayList<>();
        fragmentList.add(MovieNewFragment.newInstance(Constants.APP_USER_FILM_LIST, "0", "", "0", "1"));//全部
        fragmentList.add(MovieNewFragment.newInstance(Constants.APP_USER_FILM_LIST, "1", "", "0", "1"));//电视剧
        fragmentList.add(MovieNewFragment.newInstance(Constants.APP_USER_FILM_LIST, "2", "", "0", "1"));//院线
        fragmentList.add(MovieNewFragment.newInstance(Constants.APP_USER_FILM_LIST, "3", "", "0", "1"));//网络
        fragmentList.add(MovieNewFragment.newInstance(Constants.APP_USER_FILM_LIST, "5", "", "0", "1"));//网络
        fragmentList.add(MovieNewFragment.newInstance(Constants.APP_USER_FILM_LIST, "4", "", "0", "1"));//其他
        myFragmentAdapter.setList(fragmentList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                ActivityAnim.intentActivity(getActivity(), SearchActivity.class, null);
                break;
            case R.id.add_location:
                ActivityAnim.intentActivity(getActivity(), NearPeopleActivity.class, null);
                break;
            case R.id.add_movie:
                if (!SharePreferenceUtil.getBoolean(getContext(), UserConfig.SYS_IS_LOGIN, false)) {
                    //去登陆页
                    ActivityAnim.intentActivity(getActivity(), LoginActivity.class, null);
                    return;
                } else {
                    ActivityAnim.intentActivity(getActivity(), AddMovieActivity.class, null);
                }
                break;
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
                case R.id.radiobutton3:// 校招
                    viewPager.setCurrentItem(3);
                    break;
                case R.id.radiobutton5:// 校招
                    viewPager.setCurrentItem(4);
                    break;
                case R.id.radiobutton4:// 校招
                    viewPager.setCurrentItem(5);
                    break;
            }
        }
    }

}

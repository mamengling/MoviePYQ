package com.movie.mling.movieapp.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.MyFragmentAdapter;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.iactivityview.IndexFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.UserVo;
import com.movie.mling.movieapp.mould.LoginActivity;
import com.movie.mling.movieapp.presenter.IndexFragmentPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.MD5Utils;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/26 10:25
 * $DESE$
 */
public class IndexFragment extends BaseFragment implements IndexFragmentView {
    private IndexFragmentPresenter mIndexFragmentPresenter;
    private ViewPager viewPager;
    private RadioButton radiobutton0;
    private RadioButton radiobutton1;
    private RadioButton radiobutton2;
    private RadioGroup radioGroup;
    private MyFragmentAdapter myFragmentAdapter;
    private ArrayList<Fragment> fragmentList;
    private int intFlag = 0;
    private int isFlag = 0;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mIndexFragmentPresenter = new IndexFragmentPresenter(this);
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void onCreateViewContent(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
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
//        mIndexFragmentPresenter.getUserLogin();
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
                if (position == 2 || position == 1) {
                    if (!SharePreferenceUtil.getBoolean(getContext(), UserConfig.SYS_IS_LOGIN, false)) {
                        //去登陆页
                        isFlag = 1;
                        ActivityAnim.intentActivity(getActivity(), LoginActivity.class, null);
                        return;
                    } else {
                        isFlag = 0;
                    }
                }
                switch (position) {
                    case 0:
                        intFlag = 0;
                        radiobutton0.setChecked(true);
                        break;
                    case 1:
                        intFlag = 1;
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
        fragmentList.add(MovieIndexFragment.newInstance(Constants.APP_USER_FILM_LIST, "", "", "0"));//全部
        fragmentList.add(MovieTwoFragment.newInstance(Constants.APP_USER_FILM_LIST, "", "", "1"));//人气
        fragmentList.add(MovieThreeFragment.newInstance(Constants.APP_USER_FAV_LIST, "", "", ""));//跑组
        myFragmentAdapter.setList(fragmentList);
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams mapParams = AppMethod.getMapParams(Constants.APP_LOGIN);
        String timestamp = AppMethod.getSecondTimestampTwo();
        mapParams.put("sign", MD5Utils.Md5("vfbw4UdPkHfSwMVh|service/login|" + timestamp));
        mapParams.put("timestamp", timestamp);
        mapParams.put("username", "15554509193");
        mapParams.put("password", "123456");
        mapParams.put("channelid", "");
        mapParams.put("devicetype", "2");
        mapParams.put("lastloginversion", "1.0.0");
        return mapParams;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {

    }

    @Override
    public void excuteSuccessCallBack(UserVo mCallBackVo) {

    }

    protected class OnTabListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radiobutton2 || checkedId == R.id.radiobutton1) {
                if (!SharePreferenceUtil.getBoolean(getContext(), UserConfig.SYS_IS_LOGIN, false)) {
                    //去登陆页
                    isFlag = 1;
                    ActivityAnim.intentActivity(getActivity(), LoginActivity.class, null);
                    return;
                } else {
                    isFlag = 0;
                }
            }
            switch (checkedId) {
                case R.id.radiobutton0:// 首页
                    intFlag = 0;
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.radiobutton1:// 校招
                    intFlag = 1;
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.radiobutton2:// 校招
                    viewPager.setCurrentItem(2);
                    break;
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isFlag == 1) {
            if (intFlag == 0) {
                radiobutton0.setChecked(true);
                viewPager.setCurrentItem(0);
            } else if (intFlag == 1) {
                radiobutton1.setChecked(true);
                viewPager.setCurrentItem(1);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentList = null;
    }
}

package com.movie.mling.movieapp.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.MyFragmentAdapter;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.mould.ChangeLabelActivity;
import com.movie.mling.movieapp.mould.LoginActivity;
import com.movie.mling.movieapp.mould.NoticeMessageActivity;
import com.movie.mling.movieapp.mould.RequestMessageActivity;
import com.movie.mling.movieapp.mould.ScreenActivity;
import com.movie.mling.movieapp.mould.WebviewActivity;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/26 10:30
 * $DESE$
 */
public class YanyuanFragment extends BaseFragment implements View.OnClickListener {
    private ViewPager viewPager;
    private RadioButton radiobutton0;
    private RadioButton radiobutton1;
    private RadioButton radiobutton2;
    private RadioGroup radioGroup;
    private TextView tv_actor_ruku;
    private TextView tv_actor_huhuan;
    private TextView tv_add_user;
    private TextView tv_search_user;
    private TextView tv_actor_shaixuan;
    private MyFragmentAdapter myFragmentAdapter;
    private ArrayList<Fragment> fragmentList;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.message_fragment;
    }

    @Override
    protected void onCreateViewContent(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_full);
        radiobutton0 = (RadioButton) view.findViewById(R.id.radiobutton0);
        radiobutton1 = (RadioButton) view.findViewById(R.id.radiobutton1);
        radiobutton2 = (RadioButton) view.findViewById(R.id.radiobutton2);
        tv_add_user = (TextView) view.findViewById(R.id.tv_add_user);
        tv_search_user = (TextView) view.findViewById(R.id.tv_search_user);
        tv_actor_ruku = (TextView) view.findViewById(R.id.tv_actor_ruku);
        tv_actor_huhuan = (TextView) view.findViewById(R.id.tv_actor_huhuan);
        tv_actor_shaixuan = (TextView) view.findViewById(R.id.tv_actor_shaixuan);
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
        tv_actor_ruku.setOnClickListener(this);
        tv_actor_huhuan.setOnClickListener(this);
        tv_actor_shaixuan.setOnClickListener(this);
        tv_search_user.setOnClickListener(this);
        tv_add_user.setOnClickListener(this);
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
        fragmentList.add(ActorFragment.newInstance(Constants.APP_USER_SERVICE_STAR_LIST, SharePreferenceUtil.getString(getContext(), UserConfig.USER_TOKEN, ""), "", "1"));//最新
        fragmentList.add(ActorRoundFragment.newInstance(Constants.APP_USER_SERVICE_STARAND_LIST, SharePreferenceUtil.getString(getContext(), UserConfig.USER_TOKEN, ""), AppMethod.getDeviceIMEIOnley(getContext()), "2"));//推荐
        fragmentList.add(ActorCollectFragment.newInstance(Constants.APP_USER_SERVICE_STAR_FAV_LIST, SharePreferenceUtil.getString(getContext(), UserConfig.USER_TOKEN, ""), "", "3"));//推荐
        myFragmentAdapter.setList(fragmentList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_actor_ruku:
                Map<String, String> map = new HashMap<>();
                map.put("title", "演员入库");
                map.put("flag", "102");
                map.put("loadUrl", "https://yingq.cc/index/feedback1");
                ActivityAnim.intentActivity(getActivity(), WebviewActivity.class, map);
                break;
            case R.id.tv_actor_huhuan:
                if (!SharePreferenceUtil.getBoolean(getContext(), UserConfig.SYS_IS_LOGIN, false)) {
                    //去登陆页
                    ActivityAnim.intentActivity(getActivity(), LoginActivity.class, null);
                } else {
                    ActivityAnim.intentActivity(getActivity(), RequestMessageActivity.class, null);
                }
                break;
            case R.id.tv_actor_shaixuan:
                ActivityAnim.intentActivity(getActivity(), ChangeLabelActivity.class, null);
                break;
            case R.id.tv_add_user:
                Map<String, String> mapAdd = new HashMap<>();
                mapAdd.put("title", "演员入库");
                mapAdd.put("flag", "102");
                mapAdd.put("loadUrl", "https://yingq.cc/index/feedback1");
                ActivityAnim.intentActivity(getActivity(), WebviewActivity.class, mapAdd);
                break;
            case R.id.tv_search_user:
                ActivityAnim.intentActivity(getActivity(), ScreenActivity.class, null);
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
                case R.id.radiobutton2:// 请求
                    viewPager.setCurrentItem(2);
                    break;
            }
        }
    }
}

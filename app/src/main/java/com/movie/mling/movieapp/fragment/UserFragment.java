package com.movie.mling.movieapp.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.mould.SettingActivity;
import com.movie.mling.movieapp.mould.UserInfoActivity;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.common.log.ConstmActivityResultListener;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/26 10:30
 * $DESE$
 */
public class UserFragment extends BaseFragment {

    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    private String title;
    private ConstmActivityResultListener callback;
    private int intentFlag;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setTitleName("个人中心");
        titleBar.setRightEdit(0, "设置");
        titleBar.setLeftView(0, "个人主页");
        titleBar.setShowIcon(true, true, false);
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        Map<String, String> map = new HashMap<>();
                        map.put("user_uid", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_ID, ""));
                        ActivityAnim.intentActivity(getActivity(), UserInfoActivity.class, map);
                        break;
                    case TitleBar.clickEdt:
                        ActivityAnim.intentActivity(getActivity(), SettingActivity.class, null);
                        break;
                }
            }
        });
        intentFlag = getActivity().getIntent().getIntExtra("intentFlag", 0);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void onCreateViewContent(View view) {
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        radioButton1 = (RadioButton) view.findViewById(R.id.radiobutton0);
        radioButton2 = (RadioButton) view.findViewById(R.id.radiobutton1);
        radioButton3 = (RadioButton) view.findViewById(R.id.radiobutton2);
        radioButton4 = (RadioButton) view.findViewById(R.id.radiobutton3);
        radioButton1.setChecked(true);
    }

    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    protected void setListener() {
        if (intentFlag == 104) {
            toogleFragment(MineFragment.class);
            radioButton4.setChecked(true);
        }else {
            toogleFragment(UserInfoFragment.class);
            radioButton1.setChecked(true);
        }
        radioGroup.setOnCheckedChangeListener(new OnTabListener());
    }


    public void toogleFragment(Class<? extends Fragment> c) {
        FragmentManager manager = getChildFragmentManager();
        String tag = c.getName();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(tag);

        if (fragment == null) {
            try {
                fragment = c.newInstance();
                // 替换时保留Fragment,以便复用
                transaction.add(R.id.content_frame_child, fragment, tag);
            } catch (Exception e) {
                // ignore
            }
        } else {
            // nothing
        }
        // 遍历存在的Fragment,隐藏其他Fragment
        List<Fragment> fragments = manager.getFragments();
        if (fragments != null)
            for (Fragment fm : fragments)
                if (!fm.equals(fragment))
                    transaction.hide(fm);

        transaction.show(fragment);
        transaction.commit();
    }

    protected class OnTabListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radiobutton0:// 首页
                    toogleFragment(UserInfoFragment.class);
                    break;
                case R.id.radiobutton1:// 地图
                    toogleFragment(ImageFragment.class);
                    break;
                case R.id.radiobutton2:// 消息
                    toogleFragment(AboutFragment.class);
                    break;
                case R.id.radiobutton3:// 我的
                    toogleFragment(MineFragment.class);
                    break;
            }
        }

    }
}

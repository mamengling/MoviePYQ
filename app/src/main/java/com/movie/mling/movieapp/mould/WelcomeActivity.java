package com.movie.mling.movieapp.mould;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.welcomeAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;

/**
 * Created by MLing on 2016/7/20.
 */
public class WelcomeActivity extends BaseCompatActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private Button startButton;
    private Button closeButton;
    private welcomeAdapter mAdapter;
    private ArrayList<String> images;
    private String[] imgs;
    private TextView tv_to_main;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setVisibility(View.GONE);
        if (images != null && images.size() > 0) {
            imgs = new String[images.size()];
            for (int i = 0; i < images.size(); i++) {
                imgs[i] = images.get(i);
            }
        } else {
            imgs = new String[]{"g_01.png",
                    "g_03.png", "g_05.png"};
        }
        mAdapter = new welcomeAdapter(mContext, imgs);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    protected int onCreateViewId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        return R.layout.activity_welcome;
    }

    @Override
    protected void onCreateViewContent(View view) {
        mViewPager = view.findViewById(R.id.viewpager);
        tv_to_main = view.findViewById(R.id.tv_to_main);
    }

    @Override
    protected void getExras() {
        images = getIntent().getStringArrayListExtra("images");
    }

    @Override
    protected void setListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (imgs.length - 1 == position) {
                    tv_to_main.setVisibility(View.VISIBLE);
                } else {
                    tv_to_main.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }

    public void toMain() {
        Intent it = new Intent(this, IndexActivity.class);
        startActivity(it);
        SharePreferenceUtil.setValue(mContext, UserConfig.KEY_IS_FIRS_TIN, true);
        finish();
    }

    @Override
    public void onClick(View v) {
// TODO Auto-generated method stub
        int i = (Integer) v.getTag();
        //设置当前页为i所对应的图片
        mViewPager.setCurrentItem(i);
    }
}

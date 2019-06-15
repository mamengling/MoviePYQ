package com.movie.mling.movieapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/7/26 15:52
 * $DESE$
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list = new ArrayList<>();

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setList(ArrayList<Fragment> listItem) {
        if (list != null) {
            list.clear();
            list.addAll(listItem);
            notifyDataSetChanged();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}

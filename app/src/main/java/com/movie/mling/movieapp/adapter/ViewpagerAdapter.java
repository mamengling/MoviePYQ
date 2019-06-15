package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.utils.common.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

import lib.util.open.rollviewpage.adapter.StaticPagerAdapter;

/**
 * 作者：MLing on 2016/12/7 13:25
 * 邮箱：mamenglingkl1314@163.com
 */
public class ViewpagerAdapter extends StaticPagerAdapter {
    private List<String> mList = new ArrayList<>();
    private Context mContext;

    public ViewpagerAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
    }

    public void setImgs(List<String> imgs) {
        this.mList = imgs;
        notifyDataSetChanged();
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ImageLoaderUtils.loadImage(view, mList.get(position), R.mipmap.icon_hone_default_image);
        return view;
    }


    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }
}

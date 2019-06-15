package com.movie.mling.movieapp.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by MLing on 2016/2/25.
 */
public  abstract  class MyBaseAdapter<T> extends BaseAdapter {
    private List<T> mDatas;
    private Context mContext;
    public MyBaseAdapter(List<T> data, Context context){
        mContext = context;
        mDatas =data;
    }
    @Override
    public int getCount() {
        return mDatas==null?0:mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

}

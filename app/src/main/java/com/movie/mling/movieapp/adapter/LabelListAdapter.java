package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.MyBaseAdapter;
import com.movie.mling.movieapp.mode.bean.LabelBean;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.widget.MGridView;

import java.util.List;

/**
 * Created by MLing on 2018/5/15 0015.
 */

public class LabelListAdapter extends MyBaseAdapter {
    private List<LabelBean.MsgdateBean> mList;
    private ViewHolder holder;
    private Context mContext;
    private ConstmOnItemOnclickListener onItemOnclickListener;

    public LabelListAdapter(List data, Context context) {
        super(data, context);
        this.mList = data;
        this.mContext = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.xml_item_label_change, null);
            holder = new ViewHolder();
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.mMGridView = (MGridView) convertView.findViewById(R.id.grid_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(mList.get(position).getTitle());
        final LabelGridAdapter mAdapter = new LabelGridAdapter(mList.get(position).getList(), mContext);
        holder.mMGridView.setAdapter(mAdapter);
        holder.mMGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mAdapter.changeState(i);
                if (mAdapter.mImage_bs.get(i)) {
                    onItemOnclickListener.clickItem(holder.mMGridView, position, i, 0, mList.get(position).getList().get(i).getLtitle());
                } else {
                    onItemOnclickListener.clickItem(holder.mMGridView, position, i, 1, mList.get(position).getList().get(i).getLtitle());
                }
            }
        });
        return convertView;
    }


    static class ViewHolder {
        MGridView mMGridView;
        TextView tv_title;
    }

    public void setOnItemOnclickListener(ConstmOnItemOnclickListener onItemOnclickListener) {
        this.onItemOnclickListener = onItemOnclickListener;
    }
}

package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.MyBaseAdapter;
import com.movie.mling.movieapp.mode.bean.ImageBean;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/28 14:36
 * $DESE$
 */
public class ImageListAdapter extends MyBaseAdapter {
    private ViewHolder holder;
    private Context mContext;
    private List<ImageBean.DataBean> mList = new ArrayList<>();
    private ConstmOnItemOnclickListener mConstmOnItemOnclickListener;

    public ImageListAdapter(List data, Context context) {
        super(data, context);
        this.mContext = context;
        this.mList = data;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.xml_item_image_list, null);
            holder = new ViewHolder();
            holder.image_view = (ImageView) convertView.findViewById(R.id.image_view);
            holder.image_view_edt = (ImageView) convertView.findViewById(R.id.image_view_edt);
            holder.image_view_del = (ImageView) convertView.findViewById(R.id.image_view_del);
            holder.image_name = (TextView) convertView.findViewById(R.id.image_name);
            holder.image_count = (TextView) convertView.findViewById(R.id.image_count);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (TextUtils.equals("1", mList.get(position).getIs_set())) {
            holder.image_view_edt.setVisibility(View.VISIBLE);
            holder.image_view_del.setVisibility(View.GONE);
        }
        if (TextUtils.equals("2", mList.get(position).getIs_set())) {
            holder.image_view_del.setVisibility(View.VISIBLE);
            holder.image_view_edt.setVisibility(View.GONE);
        }
        if (TextUtils.equals("0", mList.get(position).getIs_set())) {
            holder.image_view_del.setVisibility(View.GONE);
            holder.image_view_edt.setVisibility(View.GONE);
        }

        holder.image_view_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstmOnItemOnclickListener.clickItem(holder.image_view_edt, position, 0, 1, mList.get(position).getId());
            }
        });
        holder.image_view_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstmOnItemOnclickListener.clickItem(holder.image_view_edt, position, 0, 2, mList.get(position).getId());
            }
        });
        ImageLoaderUtils.loadImage(holder.image_view, mList.get(position).getTitlepic(), R.mipmap.icon_alum_default_image);
        holder.image_name.setText(mList.get(position).getTitle());
        holder.image_count.setText(mList.get(position).getCount() + "张");
        return convertView;
    }

    static class ViewHolder {
        ImageView image_view;
        ImageView image_view_edt;
        ImageView image_view_del;
        TextView image_name;
        TextView image_count;
    }

    public void setOnItemOnclickListener(ConstmOnItemOnclickListener mConstmOnItemOnclickListener) {
        this.mConstmOnItemOnclickListener = mConstmOnItemOnclickListener;
    }
}

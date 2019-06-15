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
import com.movie.mling.movieapp.mode.bean.ImageInfoBean;
import com.movie.mling.movieapp.utils.common.ImageLoaderUtils;

import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/28 15:47
 * $DESE$
 */
public class ImageInfoAdapter extends MyBaseAdapter {
    private List<ImageInfoBean.DataBean> mList;
    private Context mContext;
    private ViewHolder holder;

    public ImageInfoAdapter(List data, Context context) {
        super(data, context);
        this.mContext = context;
        this.mList = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.xml_item_image_info_list, null);
            holder = new ViewHolder();
            holder.image_view = (ImageView) convertView.findViewById(R.id.image_view);
            holder.is_fm = (TextView) convertView.findViewById(R.id.is_fm);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (TextUtils.equals("1", mList.get(position).getFm())) {
            holder.is_fm.setVisibility(View.VISIBLE);
        }else {
            holder.is_fm.setVisibility(View.GONE);
        }
        ImageLoaderUtils.loadImage(holder.image_view, mList.get(position).getTitlepic(), R.mipmap.icon_alum_default_image);
        return convertView;
    }

    static class ViewHolder {
        ImageView image_view;
        TextView is_fm;
    }
}

package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.MyBaseAdapter;
import com.movie.mling.movieapp.mode.bean.MapMovieBean;

import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/13 13:33
 * $DESE$
 */
public class MapMovieListAdapter extends MyBaseAdapter {

    private final Context mContext;
    private List<MapMovieBean.DataBean> mList;
    private ViewHolder holder;

    public MapMovieListAdapter(List data, Context context) {
        super(data, context);
        this.mList = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.xml_item_map_movie_list, null);
            holder = new ViewHolder();
            holder.tv_view_type = convertView.findViewById(R.id.tv_view_type);
            holder.tv_movie_name = convertView.findViewById(R.id.tv_movie_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_view_type.setText(mList.get(position).getLeixing_text());
        holder.tv_movie_name.setText(mList.get(position).getTitle());
        if (TextUtils.equals("1", mList.get(position).getFav())) {
            holder.tv_movie_name.setTextColor(Color.parseColor("#E13319"));
        } else if (TextUtils.equals("1", mList.get(position).getPao())) {
            holder.tv_movie_name.setTextColor(Color.parseColor("#427fed"));
        } else {
            holder.tv_movie_name.setTextColor(Color.parseColor("#292422"));
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tv_view_type;
        TextView tv_movie_name;
    }
}

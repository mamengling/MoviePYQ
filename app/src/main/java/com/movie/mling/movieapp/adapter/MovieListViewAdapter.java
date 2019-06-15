package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.MyBaseAdapter;
import com.movie.mling.movieapp.mode.bean.MovieBean;
import com.movie.mling.movieapp.utils.common.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/2 14:24
 * $DESE$
 */
public class MovieListViewAdapter extends MyBaseAdapter {
    private ViewHolder holder;
    private Context mContext;
    private List<MovieBean.DataBean.ListBean> mList = new ArrayList<>();
    private View view;

    public MovieListViewAdapter(List data, Context context) {
        super(data, context);
        this.mContext = context;
        this.mList = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.xml_item_movie_list, null);
            holder = new ViewHolder();
            holder.image_movie_view = (ImageView) convertView.findViewById(R.id.image_movie_view);
            holder.tv_view_type = (TextView) convertView.findViewById(R.id.tv_view_type);
            holder.tv_movie_name = (TextView) convertView.findViewById(R.id.tv_movie_name);
            holder.line_con = (LinearLayout) convertView.findViewById(R.id.line_con);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GlideUtils.getInstance().LoadContextBitmap(mContext, mList.get(position).getTitlepic(), holder.image_movie_view, R.mipmap.icon_alum_default_image, GlideUtils.LOAD_BITMAP);
        holder.tv_view_type.setText(mList.get(position).getLeixing_text());
        holder.tv_movie_name.setText(mList.get(position).getTitle());
        holder.line_con.removeAllViews();
        for (int i = 0; i < mList.get(position).getExt().size(); i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_text_view_, null);
            TextView tv_ticai = (TextView) view.findViewById(R.id.tv_ticai);
            TextView tv_ticai_name = (TextView) view.findViewById(R.id.tv_ticai_name);
            tv_ticai.setText(mList.get(position).getExt().get(i).get(0) + "：");
            tv_ticai_name.setText(mList.get(position).getExt().get(i).get(1));
            holder.line_con.addView(view);
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView image_movie_view;
        TextView tv_view_type;
        TextView tv_movie_name;
        LinearLayout line_con;
    }
}

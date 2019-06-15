package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.mode.bean.MovieBean;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.GlideUtils;
import com.movie.mling.movieapp.utils.widget.AlignTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/2 10:51
 * $DESE$
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private Context mContext;
    private List<MovieBean.DataBean.ListBean> mList = new ArrayList<>();
    private ConstmOnItemOnclickListener constmOnItemOnclickListener;

    public MovieListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<MovieBean.DataBean.ListBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    public void addOnReference(List<MovieBean.DataBean.ListBean> list1) {
        if (list1 != null) {
            mList.addAll(list1);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_movie_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        GlideUtils.getInstance().LoadContextBitmap(mContext, mList.get(position).getTitlepic(), holder.image_movie_view, R.mipmap.icon_alum_default_image, GlideUtils.LOAD_BITMAP);
        holder.tv_view_type.setText(mList.get(position).getLeixing_text());
        holder.tv_movie_name.setText(mList.get(position).getTitle());
        holder.line_con.removeAllViews();
//        if (TextUtils.equals("1", mList.get(position).getIsfav())) {
//            holder.tv_view_collect.setVisibility(View.VISIBLE);
//        } else {
        holder.tv_view_collect.setVisibility(View.GONE);
//        }
        for (int i = 0; i < mList.get(position).getExt().size(); i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_text_view_, null);
            AlignTextView tv_ticai = view.findViewById(R.id.tv_ticai);
            TextView tv_ticai_name = view.findViewById(R.id.tv_ticai_name);
            tv_ticai.setAlingText(mList.get(position).getExt().get(i).get(0) + "：");
            tv_ticai_name.setText(mList.get(position).getExt().get(i).get(1));
            holder.line_con.addView(view);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constmOnItemOnclickListener.clickItem(holder.itemView, position, 0, 0, mList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_movie_view;
        TextView tv_view_type;
        TextView tv_view_collect;
        TextView tv_movie_name;
        TextView tv_ticai;
        LinearLayout line_con;

        public ViewHolder(View itemView) {
            super(itemView);
            image_movie_view = (ImageView) itemView.findViewById(R.id.image_movie_view);
            tv_view_type = (TextView) itemView.findViewById(R.id.tv_view_type);
            tv_view_collect = (TextView) itemView.findViewById(R.id.tv_view_collect);
            tv_movie_name = (TextView) itemView.findViewById(R.id.tv_movie_name);
            tv_ticai = (TextView) itemView.findViewById(R.id.tv_ticai);
            line_con = (LinearLayout) itemView.findViewById(R.id.line_con);
        }
    }

    public void setOnItemOnclickListener(ConstmOnItemOnclickListener onItemOnclickListener) {
        this.constmOnItemOnclickListener = onItemOnclickListener;
    }
}

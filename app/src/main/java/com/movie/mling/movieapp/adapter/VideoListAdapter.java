package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.mode.bean.ActorInfoBean;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.GlideUtils;

import java.util.ArrayList;

/**
 * Created by MLing on 2018/8/21 0021.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ActorInfoBean.DataBean.VideoBean> mList = new ArrayList<>();
    private ConstmOnItemOnclickListener constmOnItemOnclickListener;

    public VideoListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(ArrayList<ActorInfoBean.DataBean.VideoBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_actor_list_video, parent, false);
        return new VideoListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        GlideUtils.getInstance().LoadContextBitmap(mContext, mList.get(position).getVideopic(), holder.image_view, R.mipmap.icon_alum_default_image, GlideUtils.LOAD_BITMAP);
        holder.tv_name.setText(mList.get(position).getVideoname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constmOnItemOnclickListener.clickItem(holder.itemView,position,0,0,mList.get(position).getVideourl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView image_view;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            image_view = itemView.findViewById(R.id.image_view);
        }
    }

    public void setOnItemOnclickListener(ConstmOnItemOnclickListener onItemOnclickListener) {
        this.constmOnItemOnclickListener = onItemOnclickListener;
    }
}

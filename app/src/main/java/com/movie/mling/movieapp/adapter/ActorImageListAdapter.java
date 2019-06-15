package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.mode.bean.ActorBean;
import com.movie.mling.movieapp.mode.bean.ActorInfoBean;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MLing on 2018/8/21 0021.
 */

public class ActorImageListAdapter extends RecyclerView.Adapter<ActorImageListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ActorInfoBean.DataBean.AlbumBean> mList = new ArrayList<>();
    private ConstmOnItemOnclickListener constmOnItemOnclickListener;

    public ActorImageListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(ArrayList<ActorInfoBean.DataBean.AlbumBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    public ArrayList<ActorInfoBean.DataBean.AlbumBean> getList() {
        return mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_actor_list_image, parent, false);
        return new ActorImageListAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        GlideUtils.getInstance().LoadContextBitmap(mContext, mList.get(position).getPicurl(), holder.image_view, R.mipmap.icon_alum_default_image, GlideUtils.LOAD_BITMAP);
        holder.image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constmOnItemOnclickListener.clickItem(holder.image_view, position, 0, 0, mList.get(position).getPicurl());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_view;

        public ViewHolder(View itemView) {
            super(itemView);
            image_view = itemView.findViewById(R.id.image_view);
        }
    }


    public void setOnItemOnclickListener(ConstmOnItemOnclickListener onItemOnclickListener) {
        this.constmOnItemOnclickListener = onItemOnclickListener;
    }
}

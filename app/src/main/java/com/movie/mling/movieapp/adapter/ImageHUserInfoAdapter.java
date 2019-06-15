package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.mode.bean.UserVo;
import com.movie.mling.movieapp.utils.common.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2018/3/1 20:32
 */

public class ImageHUserInfoAdapter extends RecyclerView.Adapter<ImageHUserInfoAdapter.ViewHolder> {
    private Context mContext;
    private List<UserVo.PhotoBean> mList = new ArrayList<>();

    public ImageHUserInfoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<UserVo.PhotoBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_image_list_userinfo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GlideUtils.getInstance().LoadContextBitmap(mContext, mList.get(position).getTitlepic(), holder.image_view_photo, R.mipmap.icon_alum_default_image,GlideUtils.LOAD_BITMAP);
        holder.tv_photo_name.setText(mList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_view_photo;
        TextView tv_photo_name;

        public ViewHolder(View itemView) {
            super(itemView);
            image_view_photo = (ImageView) itemView.findViewById(R.id.image_view_photo);
            tv_photo_name = (TextView) itemView.findViewById(R.id.tv_photo_name);
        }
    }
}

package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.mode.bean.UserMarkBean;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2019/2/16 14:34
 */

public class UserMarkAdapter extends RecyclerView.Adapter<UserMarkAdapter.ViewHolder> {
    private Context mContext;
    private ConstmOnItemOnclickListener constmOnItemOnclickListener;
    private List<UserMarkBean.DataBean.ListBean> mList = new ArrayList<>();

    public UserMarkAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<UserMarkBean.DataBean.ListBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    public void addOnReference(List<UserMarkBean.DataBean.ListBean> list1) {
        if (list1 != null) {
            mList.addAll(list1);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_mark_user_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_time.setText(mList.get(position).getLasttime());
        holder.tv_content.setText(mList.get(position).getNewstext());
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

    public List<UserMarkBean.DataBean.ListBean> getList() {
        return mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time;
        TextView tv_content;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_content = itemView.findViewById(R.id.tv_content);
        }
    }


    public void setOnItemOnclickListener(ConstmOnItemOnclickListener onItemOnclickListener) {
        this.constmOnItemOnclickListener = onItemOnclickListener;
    }
}

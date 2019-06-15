package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/9/14 10:37
 * $DESE$
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<String> mList = new ArrayList<>();
    private Context mContext;
    private ConstmOnItemOnclickListener onItemOnclickListener;

    public HistoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<String> list1) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_list_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_key.setText(mList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemOnclickListener.clickItem(holder.itemView, position, 1, 1, mList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_key;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_key = (TextView) itemView.findViewById(R.id.tv_key);
        }
    }

    public void setOnItemOnclickListener(ConstmOnItemOnclickListener onItemOnclickListener) {
        this.onItemOnclickListener = onItemOnclickListener;
    }
}

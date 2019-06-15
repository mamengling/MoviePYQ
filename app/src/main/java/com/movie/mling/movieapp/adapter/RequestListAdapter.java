package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.mode.bean.MsgNoticeBean;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2018/3/20 23:00
 */

public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.ViewHolder> {
    private Context mContext;
    private List<MsgNoticeBean.DataBean.ListBean> mList = new ArrayList<>();
    private ConstmOnItemOnclickListener constmOnItemOnclickListener;

    public RequestListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<MsgNoticeBean.DataBean.ListBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    public void addOnReference(List<MsgNoticeBean.DataBean.ListBean> list1) {
        if (list1 != null) {
            mList.addAll(list1);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_msg_request_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        GlideUtils.getInstance().LoadContextCircleBitmap(mContext, mList.get(position).getFrom_avatar(), holder.image_user_logo, R.mipmap.icon_alum_default_image);

        holder.tv_user_name.setText(mList.get(position).getFrom_nickname());
        if (TextUtils.equals("2", mList.get(position).getKeytype())) {
            if (TextUtils.equals("1", mList.get(position).getTags())) {
                holder.tv_msg_title.setText("对方向您请求交互微信联系方式，您是否同意？");
            } else if (TextUtils.equals("2", mList.get(position).getTags())) {
                holder.tv_msg_title.setText("对方向您请求交互手机联系方式，您是否同意？");
            }
            holder.tv_ag.setVisibility(View.GONE);
        } else if (TextUtils.equals("3", mList.get(position).getKeytype())) {
            holder.tv_msg_title.setText(mList.get(position).getKeytext());
        } else if (TextUtils.equals("4", mList.get(position).getKeytype())) {
            if (TextUtils.equals("1", mList.get(position).getTags())) {
                holder.tv_look_jujue.setVisibility(View.VISIBLE);
                holder.tv_look_jujue.setBackgroundResource(R.color.colorAccent);
                holder.tv_look_jujue.setText("点击复制");
                holder.tv_look_tongyi.setVisibility(View.INVISIBLE);
            } else {
                holder.tv_look_jujue.setVisibility(View.VISIBLE);
                holder.tv_look_jujue.setBackgroundResource(R.color.colorAccent);
                holder.tv_look_jujue.setText("点击操作");
                holder.tv_look_tongyi.setVisibility(View.INVISIBLE);
            }
            holder.tv_ag.setVisibility(View.GONE);
            holder.tv_msg_title.setText("您好，这是我的" + (TextUtils.equals("1", mList.get(position).getTags()) ? "微信" : "电话") + "联系方式，" + (TextUtils.equals("1", mList.get(position).getTags()) ? "微信为：" : "电话为：") + mList.get(position).getKeytext());
        } else if (TextUtils.equals("1", mList.get(position).getKeytype())) {
            if (TextUtils.equals("1", mList.get(position).getTags())) {
                holder.tv_msg_title.setText("对方向您请求交互微信联系方式，您是否同意？");
            } else if (TextUtils.equals("2", mList.get(position).getTags())) {
                holder.tv_msg_title.setText("对方向您请求交互手机联系方式，您是否同意？");
            }
            if (TextUtils.equals("1", mList.get(position).getStatus())) {
                holder.tv_look_tongyi.setVisibility(View.INVISIBLE);
                holder.tv_look_jujue.setVisibility(View.INVISIBLE);
                holder.tv_ag.setVisibility(View.VISIBLE);
                holder.tv_ag.setText("您已经同意！");
            } else if (TextUtils.equals("2", mList.get(position).getStatus())) {
                holder.tv_look_tongyi.setVisibility(View.INVISIBLE);
                holder.tv_look_jujue.setVisibility(View.INVISIBLE);
                holder.tv_ag.setVisibility(View.VISIBLE);
                holder.tv_ag.setText("您已经拒绝！");
            } else if (TextUtils.equals("0", mList.get(position).getStatus())) {
                holder.tv_look_tongyi.setVisibility(View.VISIBLE);
                holder.tv_look_jujue.setVisibility(View.VISIBLE);
                holder.tv_ag.setVisibility(View.GONE);
            }
        }
        holder.tv_look_tongyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constmOnItemOnclickListener.clickItem(holder.itemView, position, 0, 1, mList.get(position).getId());
            }
        });

        holder.tv_look_jujue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (holder.tv_look_jujue.getText().toString()) {
                    case "拒绝":
                        constmOnItemOnclickListener.clickItem(holder.itemView, position, 0, 1, mList.get(position).getId());
                        break;
                    case "点击复制":
                        constmOnItemOnclickListener.clickItem(holder.itemView, position, 0, 2, mList.get(position).getId());
                        break;
                    case "点击操作":
                        constmOnItemOnclickListener.clickItem(holder.itemView, position, 0, 3, mList.get(position).getId());
                        break;
                }
            }
        });
        holder.tv_look_tongyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constmOnItemOnclickListener.clickItem(holder.itemView, position, 0, 4, mList.get(position).getId());
            }
        });
        holder.image_user_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constmOnItemOnclickListener.clickItem(holder.itemView, position, 0, 5, mList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<MsgNoticeBean.DataBean.ListBean> getList() {
        return mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image_user_logo;
        public TextView tv_msg_title;
        public TextView tv_look_tongyi;
        public TextView tv_look_jujue;
        public TextView tv_ag;
        public TextView tv_user_name;

        public ViewHolder(View itemView) {
            super(itemView);
            image_user_logo = itemView.findViewById(R.id.image_user_logo);
            tv_msg_title = itemView.findViewById(R.id.tv_msg_title);
            tv_look_tongyi = itemView.findViewById(R.id.tv_look_tongyi);
            tv_look_jujue = itemView.findViewById(R.id.tv_look_jujue);
            tv_user_name = itemView.findViewById(R.id.tv_user_name);
            tv_ag = itemView.findViewById(R.id.tv_ag);
        }
    }

    public void setOnItemOnclickListener(ConstmOnItemOnclickListener onItemOnclickListener) {
        this.constmOnItemOnclickListener = onItemOnclickListener;
    }
}

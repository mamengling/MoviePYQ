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
import com.movie.mling.movieapp.mode.bean.PersonBean;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.GlideUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MLing on 2018/4/17 0017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private Context mContext;
    private List<PersonBean.DataBean> mList = new ArrayList<>();
    private ConstmOnItemOnclickListener constmOnItemOnclickListener;

    public PersonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<PersonBean.DataBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    public void addOnReference(List<PersonBean.DataBean> list1) {
        if (list1 != null) {
            mList.addAll(list1);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_person_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        GlideUtils.getInstance().LoadContextCircleBitmap(mContext, mList.get(position).getAvatar(), holder.user_image_header, R.mipmap.icon_user_default_image);
        holder.tv_user_nick.setText(TextUtils.isEmpty(mList.get(position).getNickname()) ? mList.get(position).getUsername() : mList.get(position).getNickname());
        if (mList.get(position).getDistance() > 1000) {
            DecimalFormat df = new DecimalFormat("#.00");
            holder.tv_user_msg.setText((df.format(mList.get(position).getDistance() / 1000)) + "km\t\t" + mList.get(position).getLasttime());
        } else {
            holder.tv_user_msg.setText(mList.get(position).getDistance() + "m\t\t" + mList.get(position).getLasttime());
        }
        if (TextUtils.equals("1",mList.get(position).getStatus())){
            holder.user_image_renz.setVisibility(View.VISIBLE);
        }else {
            holder.user_image_renz.setVisibility(View.GONE);
        }

        if (TextUtils.equals("1", mList.get(position).getGender())) {
            holder.user_image_gender.setVisibility(View.VISIBLE);
            holder.user_image_gender.setImageResource(R.mipmap.icon_person_sex_nan);
        } else if (TextUtils.equals("2", mList.get(position).getGender())) {
            holder.user_image_gender.setVisibility(View.VISIBLE);
            holder.user_image_gender.setImageResource(R.mipmap.icon_person_sex_nv);
        } else {
            holder.user_image_gender.setVisibility(View.GONE);
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
        ImageView user_image_gender;
        ImageView user_image_renz;
        ImageView user_image_header;
        TextView tv_user_nick;
        TextView tv_user_msg;

        public ViewHolder(View itemView) {
            super(itemView);
            user_image_gender = itemView.findViewById(R.id.user_image_gender);
            user_image_renz = itemView.findViewById(R.id.user_image_renz);
            user_image_header = itemView.findViewById(R.id.user_image_header);
            tv_user_nick = itemView.findViewById(R.id.tv_user_nick);
            tv_user_msg = itemView.findViewById(R.id.tv_user_msg);
        }
    }

    public void setOnItemOnclickListener(ConstmOnItemOnclickListener onItemOnclickListener) {
        constmOnItemOnclickListener = onItemOnclickListener;
    }
}

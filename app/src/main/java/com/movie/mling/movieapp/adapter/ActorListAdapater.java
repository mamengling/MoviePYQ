package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.mode.bean.ActorBean;
import com.movie.mling.movieapp.mode.bean.MsgNoticeBean;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.DensityUtil;
import com.movie.mling.movieapp.utils.common.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MLing on 2018/4/27 0027.
 */

public class ActorListAdapater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ActorBean.DataBean.ListBean> mList = new ArrayList<>();
    private ConstmOnItemOnclickListener constmOnItemOnclickListener;

    public ActorListAdapater(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<ActorBean.DataBean.ListBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    public void addOnReference(List<ActorBean.DataBean.ListBean> list1) {
        if (list1 != null) {
            mList.addAll(list1);
            notifyDataSetChanged();
        }
    }

    public List<ActorBean.DataBean.ListBean> getList() {
        return mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 2) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_actor_list, parent, false);
            return new ViewHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_actor_list_btn, parent, false);
            return new ViewHolderBtn(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (mList.get(position).getId() == 0) {
            ((ViewHolderBtn) holder).btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    constmOnItemOnclickListener.clickItem(((ViewHolderBtn) holder).btn_save, position, 0, 1, mList.get(position).getId() + "");
                }
            });
        } else {
            if (!TextUtils.isEmpty(mList.get(position).getPicurl())) {
                GlideUtils.getInstance().LoadContextBitmap(mContext, mList.get(position).getPicurl(), ((ViewHolder) holder).image_view, R.mipmap.icon_alum_default_image, GlideUtils.LOAD_BITMAP);
            } else {
                GlideUtils.getInstance().LoadContextBitmap(mContext, mList.get(position).getAvatar(), ((ViewHolder) holder).image_view, R.mipmap.icon_alum_default_image, GlideUtils.LOAD_BITMAP);
            }
            ((ViewHolder) holder).tv_name.setText(mList.get(position).getXingming());
            ((ViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    constmOnItemOnclickListener.clickItem(((ViewHolder) holder).itemView, position, 0, 0, mList.get(position).getId() + "");
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if (mList.get(position).getId() == 0) {
            type = 1;
        } else {
            type = 2;
        }
        return type;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_view;
        TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            image_view = itemView.findViewById(R.id.image_view);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }

    public class ViewHolderBtn extends RecyclerView.ViewHolder {
        Button btn_save;

        public ViewHolderBtn(View itemView) {
            super(itemView);
            btn_save = itemView.findViewById(R.id.btn_save);
        }
    }

    public void setOnItemOnclickListener(ConstmOnItemOnclickListener onItemOnclickListener) {
        this.constmOnItemOnclickListener = onItemOnclickListener;
    }
}

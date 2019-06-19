package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.mode.bean.ScreenBean;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;

import java.util.ArrayList;
import java.util.List;

public class ScreenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ScreenBean.labelBean> mList = new ArrayList<>();
    private ConstmOnItemOnclickListener mOnclickListener;


    public ScreenAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public void onReference(List<ScreenBean.labelBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    public void addOnReference(List<ScreenBean.labelBean> list1) {
        if (list1 != null) {
            mList.addAll(list1);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 101) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_lable_title, parent, false);
            return new ViewHolder(view);
        } else if (viewType == 102) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_top_view, parent, false);
            return new ViewTopHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_lable, parent, false);
            return new ViewTitleHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == 101) {
            ((ViewHolder) holder).tv_title.setText(mList.get(position).getName());
        } else if (getItemViewType(position) == 102) {
            ((ViewTopHolder) holder).rb_nan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnclickListener.clickItem(((ViewTopHolder) holder).rb_nan, position, 0, 1, ((ViewTopHolder) holder).rb_nan.isChecked() + "");
                }
            });
            ((ViewTopHolder) holder).rb_nv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnclickListener.clickItem(((ViewTopHolder) holder).rb_nan, position, 0, 2, ((ViewTopHolder) holder).rb_nv.isChecked() + "");
                }
            });
            ((ViewTopHolder) holder).rb_you.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            ((ViewTopHolder) holder).rb_wu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        } else {
            ((ViewTitleHolder) holder).tv_title.setText(mList.get(position).getName());
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<ScreenBean.labelBean> getList() {
        return mList;
    }


    public class ViewTopHolder extends RecyclerView.ViewHolder {
        RadioGroup radiogroup_xingbie;
        RadioButton rb_nan;
        RadioButton rb_nv;
        RadioGroup radio_group_gongsi;
        RadioButton rb_you;
        RadioButton rb_wu;
        TextView tv_xiao_nianling;
        TextView tv_di_shengao;
        TextView tv_gao_shengao;
        TextView tv_di_tizhong;
        TextView tv_gao_tizhong;
        TextView rb_guonei;
        TextView rb_gat;
        TextView rb_guowai;


        public ViewTopHolder(View itemView) {
            super(itemView);
            radiogroup_xingbie = itemView.findViewById(R.id.radiogroup_xingbie);
            rb_nan = itemView.findViewById(R.id.rb_nan);
            rb_nv = itemView.findViewById(R.id.rb_nv);
            rb_you = itemView.findViewById(R.id.rb_you);
            rb_wu = itemView.findViewById(R.id.rb_wu);
            radio_group_gongsi = itemView.findViewById(R.id.radio_group_gongsi);
            tv_xiao_nianling = itemView.findViewById(R.id.tv_xiao_nianling);
            tv_di_shengao = itemView.findViewById(R.id.tv_di_shengao);
            tv_gao_shengao = itemView.findViewById(R.id.tv_gao_shengao);
            tv_di_tizhong = itemView.findViewById(R.id.tv_di_tizhong);
            tv_gao_tizhong = itemView.findViewById(R.id.tv_gao_tizhong);
            rb_guonei = itemView.findViewById(R.id.rb_guonei);
            rb_gat = itemView.findViewById(R.id.rb_gat);
            rb_guowai = itemView.findViewById(R.id.rb_guowai);

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);

        }
    }

    public class ViewTitleHolder extends RecyclerView.ViewHolder {

        TextView tv_title;

        public ViewTitleHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }


    public void setOnItemOnclickListener(ConstmOnItemOnclickListener onItemOnclickListener) {
        this.mOnclickListener = onItemOnclickListener;
    }
}

package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.mode.bean.MovieNewBean;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;

import java.util.ArrayList;
import java.util.List;

import lib.util.open.rollviewpage.OnItemClickListener;
import lib.util.open.rollviewpage.RollPagerView;
import lib.util.open.rollviewpage.hintview.ColorPointHintView;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/2 10:51
 * $DESE$
 */
public class MarkBannerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<MovieNewBean.DataBean.ListBean> mList = new ArrayList<>();
    private ConstmOnItemOnclickListener constmOnItemOnclickListener;
    private ArrayList<String> items = new ArrayList<>();

    public MarkBannerListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<MovieNewBean.DataBean.ListBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    public void addOnReference(List<MovieNewBean.DataBean.ListBean> list1) {
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
        if (viewType == 1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_movie_banner, parent, false);
            return new ViewHolderBanner(view);
        } else if (viewType == 2) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_movie_list_time_check, parent, false);
            return new ViewHolderTime(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_movie_list_title, parent, false);
            return new ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (mList.get(position).getViewType() == 1) {
            ViewpagerAdapter mPagerAdapter = new ViewpagerAdapter(mContext, items);
            ((ViewHolderBanner) holder).normal_view_pager.setPlayDelay(3000);
            ((ViewHolderBanner) holder).normal_view_pager.setAdapter(mPagerAdapter);
            ((ViewHolderBanner) holder).normal_view_pager.setHintView(new ColorPointHintView(mContext, Color.parseColor("#5ac5b3"), Color.WHITE));
            ((ViewHolderBanner) holder).normal_view_pager.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });
            if (mList.get(position).getListBannaer().size() > 0) {
                items.clear();
                for (int i = 0; i < mList.get(position).getListBannaer().size(); i++) {
                    items.add(mList.get(position).getListBannaer().get(i).getPic());
                }
                mPagerAdapter.setImgs(items);
            }

        } else if (mList.get(position).getViewType() == 2) {
            if (mList.get(position).isCheck()) {
                ((ViewHolderTime) holder).image_movie_view.setImageResource(R.mipmap.icon_radio_check_sel);
            } else {
                ((ViewHolderTime) holder).image_movie_view.setImageResource(R.mipmap.icon_radio_check_unsel);
            }
            ((ViewHolderTime) holder).tv_time.setText(mList.get(position).getTime());
            ((ViewHolderTime) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    constmOnItemOnclickListener.clickItem(((ViewHolderTime) holder).itemView, position, 0, 1, mList.get(position).getTime());
                }
            });
        } else {
            ((ViewHolder) holder).tv_view_type.setText(mList.get(position).getLeixing_text());
            ((ViewHolder) holder).tv_movie_name.setText(mList.get(position).getTitle());
            if (TextUtils.equals("1", mList.get(position).getIsfav())) {
                ((ViewHolder) holder).tv_movie_name.setTextColor(Color.parseColor("#E13319"));
            } else if (TextUtils.equals("1", mList.get(position).getIspao())) {
                ((ViewHolder) holder).tv_movie_name.setTextColor(Color.parseColor("#427fed"));
            } else {
                ((ViewHolder) holder).tv_movie_name.setTextColor(Color.parseColor("#292422"));
            }
            if (mList.get(position).isCheck()) {
                ((ViewHolder) holder).image_movie_view.setImageResource(R.mipmap.icon_radio_check_sel);
            } else {
                ((ViewHolder) holder).image_movie_view.setImageResource(R.mipmap.icon_radio_check_unsel);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    constmOnItemOnclickListener.clickItem(((ViewHolder) holder).itemView, position, 0, 2, mList.get(position).getTime());
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<MovieNewBean.DataBean.ListBean> getList() {
        return mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_movie_view;
        TextView tv_view_type;
        TextView tv_movie_name;

        public ViewHolder(View itemView) {
            super(itemView);
            image_movie_view = (ImageView) itemView.findViewById(R.id.image_movie_view);
            tv_view_type = (TextView) itemView.findViewById(R.id.tv_view_type);
            tv_movie_name = (TextView) itemView.findViewById(R.id.tv_movie_name);
        }
    }

    public class ViewHolderBanner extends RecyclerView.ViewHolder {
        RollPagerView normal_view_pager;

        public ViewHolderBanner(View itemView) {
            super(itemView);
            normal_view_pager = itemView.findViewById(R.id.normal_view_pager);
        }
    }

    public class ViewHolderTime extends RecyclerView.ViewHolder {
        TextView tv_time;
        ImageView image_movie_view;

        public ViewHolderTime(View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
            image_movie_view = (ImageView) itemView.findViewById(R.id.image_movie_view);
        }
    }

    public void setOnItemOnclickListener(ConstmOnItemOnclickListener onItemOnclickListener) {
        this.constmOnItemOnclickListener = onItemOnclickListener;
    }
}

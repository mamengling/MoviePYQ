package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.MyBaseAdapter;
import com.movie.mling.movieapp.mode.bean.ImageInfoBean;
import com.movie.mling.movieapp.utils.common.ImageLoaderUtils;

import java.util.List;
import java.util.Vector;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/28 17:02
 * $DESE$
 */
public class ChangeFmAdapter extends MyBaseAdapter {
    private List<ImageInfoBean.DataBean> mList;
    private Context mContext;
    private ViewHolder holder;
    private Vector<Boolean> mImage_bs = new Vector<Boolean>();// 定义一个向量作为选中与否容器
    private int lastPosition = -1;// 记录上一次选中的图片位置，-1表示未选中任何图片
    private boolean multiChoose = false;// 表示当前适配器是否允许多选

    public ChangeFmAdapter(List data, Context context) {
        super(data, context);
        this.mContext = context;
        this.mList = data;
        for (int i = 0; i < mList.size(); i++) {
            mImage_bs.add(false);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.xml_item_image_change_list, null);
            holder = new ViewHolder();
            holder.image_view = (ImageView) convertView.findViewById(R.id.image_view);
            holder.radio_button = (ImageView) convertView.findViewById(R.id.radio_button);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoaderUtils.loadImage(holder.image_view, mList.get(position).getTitlepic(), R.mipmap.icon_alum_default_image);
        if (mImage_bs.get(position)) {
            holder.radio_button.setBackgroundColor(Color.parseColor("#E13319"));
        } else {
            holder.radio_button.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView image_view;
        ImageView radio_button;
    }

    // 修改选中的状态
    public void changeState(int position) {
        // 多选时
        if (multiChoose == true) {
            mImage_bs.setElementAt(!mImage_bs.elementAt(position), position); // 直接取反即可
        }
        // 单选时
        else {
            if (lastPosition != -1)
                mImage_bs.setElementAt(false, lastPosition);// 取消上一次的选中状态
            mImage_bs.setElementAt(!mImage_bs.elementAt(position), position); // 直接取反即可
            lastPosition = position; // 记录本次选中的位置
        }
        notifyDataSetChanged(); // 通知适配器进行更新
    }
}

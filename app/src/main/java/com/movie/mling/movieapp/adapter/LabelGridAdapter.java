package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.MyBaseAdapter;
import com.movie.mling.movieapp.mode.bean.LabelBean;

import java.util.List;
import java.util.Vector;

/**
 * Created by MLing on 2018/5/15 0015.
 */

public class LabelGridAdapter extends MyBaseAdapter {
    private List<LabelBean.MsgdateBean.ListBean> mList;
    private Context mContext;
    private ViewHolder holder;
    public Vector<Boolean> mImage_bs = new Vector<Boolean>();// 定义一个向量作为选中与否容器
    private int lastPosition = -1;// 记录上一次选中的图片位置，-1表示未选中任何图片
    private boolean multiChoose = true;// 表示当前适配器是否允许多选

    public LabelGridAdapter(List data, Context context) {
        super(data, context);
        this.mList = data;
        this.mContext = context;
        for (int i = 0; i < mList.size(); i++) {
            mImage_bs.add(false);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.xml_item_label_child_change, null);
            holder = new ViewHolder();
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(mList.get(position).getLtitle());
        if (mImage_bs.get(position)) {
            holder.tv_title.setBackgroundResource(R.drawable.rect_red_stroke_sel);
            holder.tv_title.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.tv_title.setBackgroundResource(R.drawable.rect_red_stroke);
            holder.tv_title.setTextColor(Color.parseColor("#E13319"));
        }
        return convertView;
    }


    static class ViewHolder {
        TextView tv_title;
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

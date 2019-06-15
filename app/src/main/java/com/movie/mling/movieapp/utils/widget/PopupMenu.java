package com.movie.mling.movieapp.utils.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.movie.mling.movieapp.R;

public class PopupMenu extends PopupWindow implements OnClickListener {

    private Activity activity;
    private View popView;
    private TextView v_item1;
    private View v_item2;
    private View v_item3;

    private OnItemClickListener onItemClickListener;

    /**
     * @author ywl5320 枚举，用于区分选择了哪个选项
     */
    public enum MENUITEM {
        ITEM1, ITEM2, ITEM3
    }

    public PopupMenu(Activity activity) {
        super(activity);
        this.activity = activity;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popView = inflater.inflate(R.layout.set_index_menu, null);// 加载菜单布局文件
        this.setContentView(popView);// 把布局文件添加到popupwindow中
        this.setWidth(dip2px(activity, 130));// 设置菜单的宽度（需要和菜单于右边距的距离搭配，可以自己调到合适的位置）
//        this.setHeight(dip2px(activity, 130));
        this.setFocusable(true);// 获取焦点
        this.setTouchable(true); // 设置PopupWindow可触摸
        this.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸
        ColorDrawable dw = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(dw);

        // 获取选项卡
        v_item1 = (TextView) popView.findViewById(R.id.device_like);
        v_item2 = popView.findViewById(R.id.add_share);
        v_item3 = popView.findViewById(R.id.add_jubao);
        // 添加监听
        v_item1.setOnClickListener(this);
        v_item2.setOnClickListener(this);
        v_item3.setOnClickListener(this);

    }

    /**
     * 设置显示的位置
     *
     * @param resourId 这里的x,y值自己调整可以
     */
    public void showLocation(int resourId) {
        showAsDropDown(activity.findViewById(resourId), dip2px(activity, 0), dip2px(activity, 0));
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onClick(v);
        }
        dismiss();
    }

    // dip转换为px
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    // 点击监听接口
    public interface OnItemClickListener {
        public void onClick(View item);
    }

    // 设置监听
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setRightSet(int drwId, String name) {
        setTextDrwNameRight(v_item1, drwId);
        v_item1.setText(textDealWithStr(name));
    }

    //设置右侧
    private void setTextDrwNameRight(TextView textView, int drawId) {
        Drawable drawable = null;
        if (drawId == 101) {
            textView.setTextColor(Color.parseColor("#CE9278"));
        } else if (drawId != 101 && drawId != 0) {
            drawable = activity.getResources().getDrawable(drawId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    //处理字符串 不是null
    private String textDealWithStr(String name) {
        return TextUtils.isEmpty(name) ? "" : name;
    }
}

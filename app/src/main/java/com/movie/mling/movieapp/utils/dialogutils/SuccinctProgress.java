package com.movie.mling.movieapp.utils.dialogutils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.movie.mling.movieapp.R;

public class SuccinctProgress {

    private static int[] iconStyles = {R.mipmap.x1,R.mipmap.x2, R.mipmap.x3,
            R.mipmap.x4,
            R.mipmap.x5,
            R.mipmap.x6,
            R.mipmap.x7,
            R.mipmap.x8,
            R.mipmap.x9,
            R.mipmap.x10,
            R.mipmap.x11,
            R.mipmap.x12,
            R.mipmap.x13,
            R.mipmap.x14,
            R.mipmap.x15,
            R.mipmap.x16,
            R.mipmap.x17,
            R.mipmap.x18,
            R.mipmap.x19,
            R.mipmap.x20,
            R.mipmap.x21,
            R.mipmap.x22,
            R.mipmap.x23,
            R.mipmap.x24,
            R.mipmap.x25,
            R.mipmap.x26,
            R.mipmap.x27,
            R.mipmap.x28,
            R.mipmap.x29,
            R.mipmap.x30};
    private static ProgressDialog pd;
    /**
     * ICON 为太极
     */
    public static final int THEME_ULTIMATE = 0;
    /**
     * ICON 为点
     */
    public static final int THEME_DOT = 1;
    /**
     * ICON 为弧线
     */
    public static final int THEME_LINE = 2;
    /**
     * ICON 为线条
     */
    public static final int THEME_ARC = 3;

    public static void showSuccinctProgress(Context context, String message, int theme, boolean isCanceledOnTouchOutside, boolean isCancelable) {
        // 创建ProgressDialog对象
        pd = new ProgressDialog(context, R.style.succinctProgressDialog);
        // false 设置点击外边距不可取消,true 可取消
        pd.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
        // 设置点击back键不可取消
        pd.setCancelable(isCancelable);
        // 加载自定义的ProgressDialog
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(context).inflate(
                R.layout.succinct_progress_content, null);
        ImageView mProgressIcon = (ImageView) view
                .findViewById(R.id.progress_icon);
        // 设置样式
        mProgressIcon.setImageResource(iconStyles[theme]);
        TextView mProgressMessage = (TextView) view
                .findViewById(R.id.progress_message);
        // 设置内容
        mProgressMessage.setText(message);
        new AnimationUtils();
        // 设置动画
        Animation jumpAnimation = AnimationUtils.loadAnimation(context,
                R.anim.succinct_animation);
        mProgressIcon.startAnimation(jumpAnimation);
        // 显示
        pd.show();
        // 必须先显示，在设置自定义的View
        pd.setContentView(view, params);
    }

    /**
     * @return true即现实中，false为不在显示
     */
    public static boolean isShowing() {
        if (pd != null && pd.isShowing()) {
            return true;
        }
        return false;
    }

    /**
     * 取消Dialog
     */
    public static void dismiss() {
        if (isShowing()) {
            pd.dismiss();
        }
    }
}

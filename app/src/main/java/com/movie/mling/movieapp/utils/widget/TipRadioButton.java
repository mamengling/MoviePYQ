package com.movie.mling.movieapp.utils.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.movie.mling.movieapp.R;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/9/20 11:08
 * $DESE$
 */
public class TipRadioButton extends RadioButton {
    private boolean isTipOn = false;

    private Dot mDot;

    private class Dot {

        int color;

        int radius;

        int marginTop;
        int marginRight;

        Dot() {
            float density = getContext().getResources().getDisplayMetrics().density;
            radius = (int) (3 * density);
            marginTop = (int) (3 * density);
            marginRight = (int) (3 * density);

            color = getContext().getResources().getColor(R.color.home_tab_title);
        }

    }

    public boolean isTipOn() {
        return isTipOn;
    }

    public TipRadioButton(Context context) {
        super(context);
        init();
    }

    public TipRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TipRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mDot = new Dot();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isTipOn) {
            float cx = getWidth() - mDot.marginRight - mDot.radius;
            float cy = mDot.marginTop + mDot.radius;

            Drawable drawableTop = getCompoundDrawables()[1];
            if (drawableTop != null) {
                int drawableTopWidth = drawableTop.getIntrinsicWidth();
                if (drawableTopWidth > 0) {
                    int dotLeft = getWidth() / 2 + drawableTopWidth / 2;
                    cx = dotLeft + mDot.radius;
                }
            }

            Paint paint = getPaint();
            //save
            int tempColor = paint.getColor();

            paint.setColor(mDot.color);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(cx, cy, mDot.radius, paint);

            //restore
            paint.setColor(tempColor);
        }
    }

    public void setTipOn(boolean tip) {
        this.isTipOn = tip;
        invalidate(); // 重新绘制
    }
}
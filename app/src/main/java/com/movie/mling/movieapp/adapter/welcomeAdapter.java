package com.movie.mling.movieapp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioGroup;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.mould.WelcomeActivity;
import com.movie.mling.movieapp.utils.common.GlideUtils;

/**
 *
 */
public class welcomeAdapter extends PagerAdapter {
    private Context mContext;
    private RadioGroup mIndicator;
    private String[] imgs;

    public welcomeAdapter(Context mContext, String[] imgs) {
        this.mContext = mContext;
        this.imgs = imgs;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @SuppressWarnings("deprecation")
    @Override
    public Object instantiateItem(ViewGroup container, final int position) { // �����������ʵ����ҳ��
        ImageView mView;
        if (container.getChildAt(position) == null) {
            mView = new ImageView(mContext);
            mView.setScaleType(ScaleType.CENTER_CROP);
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
            mView.setLayoutParams(params);
//            try {
//                BitmapDrawable d = new BitmapDrawable(mContext.getAssets()
//                        .open(imgs[position]));
//                mView.setImageDrawable(d);
                // mView.setBackgroundDrawable(d);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            GlideUtils.getInstance().LoadContextBitmap(mContext, imgs[position], mView, R.mipmap.icon_alum_default_image, GlideUtils.LOAD_BITMAP);

            container.addView(mView, position);
        } else {
            mView = (ImageView) container.getChildAt(position);
        }
        mView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (position == (imgs.length - 1)) {
                    ((WelcomeActivity) mContext).toMain();
                }
            }
        });
        return mView;
    }

    @Override
    public int getCount() {
        int size = imgs == null ? 0 : imgs.length;
        return size == 0 ? 0 : imgs.length;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    public ViewGroup getIndicator() {
        return mIndicator;
    }

}


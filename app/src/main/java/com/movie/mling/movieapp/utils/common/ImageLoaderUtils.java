package com.movie.mling.movieapp.utils.common;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.movie.mling.movieapp.R;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Created by prin on 2016/8/27.
 * 图片加载封装  底层加载可切换其他图片加载框架
 * 目前采用的是glide
 */
public class ImageLoaderUtils {

    private static final String HTTP_PREFIX = "http://";
    private static final String HTTPS_PREFIX = "http://";
    private static final String FILE_PREFIX = "file://";
    private static final String CONTENT_PREFEIX = "content://";
    private static final String ASSETS_PREFIX = "assets://";
    private static final String DRAWABLE_PREFIX = "drawable://";
    private static final String MIPMAP_PREFIX = "mipmap://";

    private static ImageLoaderUtils sImageLoaderUtils;
    private static WeakReference<Context> sContextWeak;

    public ImageLoaderUtils() {

    }

    public static void init(Context context) {
        if (sImageLoaderUtils == null) {
            synchronized (ImageLoaderUtils.class) {
                if (sImageLoaderUtils == null) {
                    sImageLoaderUtils = new ImageLoaderUtils();
                }
            }
        }
        sContextWeak = new WeakReference<Context>(context);
    }

    public static void loadImage(ImageView imageView, String imageUrl) {
        String url = Constants.BASE_URL + imageUrl;

        if (imageUrl.startsWith("http://")) {
            loadImage(imageView, imageUrl, R.mipmap.icon_user_default_image, 0, 0);
        } else {
            loadImage(imageView, url, R.mipmap.icon_user_default_image, 0, 0);
        }
    }

    public static void loadImage(ImageView imageView, String imageUrl, int defaultImageId) {
        String url = Constants.BASE_URL + imageUrl;
        if (!TextUtils.isEmpty(imageUrl) && imageUrl.startsWith("http://")) {
            loadImage(imageView, imageUrl, defaultImageId, 0, 0);
        } else {
            loadImage(imageView, url, defaultImageId, 0, 0);
        }
    }

    public static void loadImage(ImageView imageView, String imageUrl, int defaultImageId, int maxWidth, int maxHeight) {
        loadImage(imageView, imageUrl, defaultImageId, maxWidth, maxHeight, ImageView.ScaleType.CENTER_INSIDE);
    }

    public static void loadImage(ImageView imageView, String imageUrl, int defaultImageId, int maxWidth, int maxHeight, ImageView.ScaleType scaleType) {
        isInitedCheck();
        loadImage((Context) sContextWeak.get(), imageView, imageUrl, defaultImageId, maxWidth, maxHeight, scaleType);
    }

    public static void loadImage(Context context, ImageView imageView, String imageUrl, int defaultImageId, int maxWidth, int maxHeight, ImageView.ScaleType scaleType) {
        try {
            if (imageUrl != null && imageUrl.length() >= 7) {
                if (!imageUrl.startsWith(HTTP_PREFIX) && !imageUrl.startsWith(HTTP_PREFIX)) {
                    if (imageUrl.startsWith(FILE_PREFIX)) {
                        if (maxWidth != 0 && maxHeight != 0) {
                            Glide.with(context).load(new File(imageUrl.substring(FILE_PREFIX.length()))).dontAnimate().error(defaultImageId).override(maxWidth, maxHeight).into(imageView);
                        } else {
                            Glide.with(context).load(new File(imageUrl.substring(FILE_PREFIX.length()))).dontAnimate().error(defaultImageId).into(imageView);
                        }
                    } else if (imageUrl.startsWith(CONTENT_PREFEIX)) {
                        if (maxWidth != 0 && maxHeight != 0) {
                            Glide.with(context).load(Uri.parse(imageUrl)).error(defaultImageId).override(maxWidth, maxHeight).into(imageView);
                        } else {
                            Glide.with(context).load(Uri.parse(imageUrl)).error(defaultImageId).into(imageView);
                        }
                    } else if (imageUrl.startsWith(ASSETS_PREFIX)) {
                        if (maxWidth != 0 && maxHeight != 0) {
                            Glide.with(context).load(Uri.parse("file:///android_asset/" + imageUrl.substring(ASSETS_PREFIX.length()))).dontAnimate().placeholder(defaultImageId).override(maxWidth, maxHeight).into(imageView);
                        } else {
                            Glide.with(context).load(Uri.parse("file:///android_asset/" + imageUrl.substring(ASSETS_PREFIX.length()))).dontAnimate().placeholder(defaultImageId).into(imageView);
                        }
                    } else {
                        Glide.with(context).load(imageUrl).error(R.mipmap.icon_user_default_image).crossFade().into(imageView);
                    }
                } else if (maxWidth != 0 && maxHeight != 0) {
                    Glide.with(context).load(imageUrl).dontAnimate().placeholder(defaultImageId).override(maxWidth, maxHeight).crossFade().into(imageView);
                } else {
                    Glide.with(context).load(imageUrl).dontAnimate().placeholder(defaultImageId).crossFade().into(imageView);
                }
            } else if (defaultImageId > 0) {
                imageView.setImageResource(defaultImageId);
            } else {
                throw new IllegalArgumentException("imageUrl invalid");
            }
        } catch (Exception e) {

        }
    }


    public static void clear() {
        isInitedCheck();
        Glide.get((Context) sContextWeak.get()).clearMemory();
    }

    private static void isInitedCheck() {
        if (sContextWeak == null || sContextWeak.get() == null) {
            throw new IllegalStateException("ImageLoaderUtils not initialized");
        }
    }
}

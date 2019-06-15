//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.movie.mling.movieapp.utils.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.WindowManager;

import com.movie.mling.movieapp.utils.common.log.LogUtil;

public class XtomWindowSize {
    private static final String TAG = "WindowSize";
    private static int height = 0;
    private static int width = 0;
    private static int statusBarHeight = 0;

    public XtomWindowSize() {
    }

    public static void get(Context context) {
        if (height == 0 || width == 0) {
            Activity ac = (Activity) context;
            WindowManager wm = (WindowManager) ac.getSystemService("window");
            width = wm.getDefaultDisplay().getWidth();
            height = wm.getDefaultDisplay().getHeight();
            Rect rect = new Rect();
            ac.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            statusBarHeight = rect.top;
            LogUtil.d("WindowSize", "height=" + height + " width=" + width + " statusBarHeight=" + statusBarHeight);
            if (width != 0) {
                XtomSharedPreferencesUtil.save(ac, "windowWidth", Integer.valueOf(width).toString());
                XtomSharedPreferencesUtil.save(ac, "windowHeight", Integer.valueOf(height).toString());
                XtomSharedPreferencesUtil.save(ac, "windowStatusBarHeight", Integer.valueOf(statusBarHeight).toString());
            }
        }

    }

    public static int getHeight(Context context) {
        String h = null;
        if (height == 0) {
            h = XtomSharedPreferencesUtil.get(context, "windowHeight");
            if (h == null) {
                get(context);
            }
        }

        return height != 0 ? height : (h != null ? Integer.valueOf(h).intValue() : 0);
    }

    public static int getWidth(Context context) {
        String h = null;
        if (width == 0) {
            h = XtomSharedPreferencesUtil.get(context, "windowWidth");
            if (h == null) {
                get(context);
            }
        }

        return width != 0 ? width : (h != null ? Integer.valueOf(h).intValue() : 0);
    }

    public static int getStatusBarHeight(Context context) {
        String h = null;
        if (statusBarHeight == 0) {
            h = XtomSharedPreferencesUtil.get(context, "windowStatusBarHeight");
            if (h == null) {
                get(context);
            }
        }

        return statusBarHeight != 0 ? statusBarHeight : (h != null ? Integer.valueOf(h).intValue() : 0);
    }
}

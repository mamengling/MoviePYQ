package com.movie.mling.movieapp.utils.common;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * 作者：MLing on 2016/9/5 0005 12:02
 * 邮箱：mamenglingkl1314@163.com
 */
public class SharePreferenceUtil {
    private static SharedPreferences sp;
    private static final String SharePreferncesName = "SP_SETTING";

    public SharePreferenceUtil() {
    }

    public static boolean setValue(Context context, String key, Object value) {
        if(sp == null) {
            sp = context.getSharedPreferences("SP_SETTING", 0);
        }

        SharedPreferences.Editor edit = sp.edit();
        if(value instanceof String) {
            return edit.putString(key, (String)value).commit();
        } else if(value instanceof Boolean) {
            return edit.putBoolean(key, ((Boolean)value).booleanValue()).commit();
        } else if(value instanceof Float) {
            return edit.putFloat(key, ((Float)value).floatValue()).commit();
        } else if(value instanceof Integer) {
            return edit.putInt(key, ((Integer)value).intValue()).commit();
        } else if(value instanceof Long) {
            return edit.putLong(key, ((Long)value).longValue()).commit();
        } else if(value instanceof Set) {
            new IllegalArgumentException("Value can not be Set object!");
            return false;
        } else {
            return false;
        }
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        if(sp == null) {
            sp = context.getSharedPreferences("SP_SETTING", 0);
        }

        return sp.getBoolean(key, defaultValue);
    }

    public static String getString(Context context, String key, String defaultValue) {
        if(sp == null) {
            sp = context.getSharedPreferences("SP_SETTING", 0);
        }

        return sp.getString(key, defaultValue);
    }

    public static Float getFloat(Context context, String key, float defaultValue) {
        if(sp == null) {
            sp = context.getSharedPreferences("SP_SETTING", 0);
        }

        return Float.valueOf(sp.getFloat(key, defaultValue));
    }

    public static int getInt(Context context, String key, int defaultValue) {
        if(sp == null) {
            sp = context.getSharedPreferences("SP_SETTING", 0);
        }

        return sp.getInt(key, defaultValue);
    }

    public static long getLong(Context context, String key, long defaultValue) {
        if(sp == null) {
            sp = context.getSharedPreferences("SP_SETTING", 0);
        }

        return sp.getLong(key, defaultValue);
    }

    public static boolean remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("SP_SETTING", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        return editor.commit();
    }

    public static boolean clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences("SP_SETTING", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        return editor.commit();
    }

    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("SP_SETTING", 0);
        boolean result = sp.contains(key);
        return result;
    }

    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences("SP_SETTING", 0);
        return sp.getAll();
    }
}

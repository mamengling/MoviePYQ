
package com.movie.mling.movieapp.utils.common;

import android.content.Context;
import android.content.SharedPreferences;

public class XtomSharedPreferencesUtil {
    private static final String FILENAME = "sp";

    public XtomSharedPreferencesUtil() {
    }

    public static void save(Context con, String key, String value) {
        SharedPreferences sp = con.getSharedPreferences("sp", 0);
        sp.edit().putString(key, value).commit();
    }

    public static String get(Context con, String key) {
        SharedPreferences sp = con.getSharedPreferences("sp", 0);
        return sp.getString(key, (String)null);
    }
}

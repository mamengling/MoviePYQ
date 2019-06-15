package com.movie.mling.movieapp.utils.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Map;

/**
 * Created by MLing on 14/12/12.
 */
public class ActivityAnim {
    private static Intent intent = null;

    /**
     * @param startactivity
     * @param endactivity   Activity间跳转
     *                      实现动画
     */
    public static void intentActivity(Activity startactivity, Class<?> endactivity, Map<String, String> map) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.setClass(startactivity, endactivity);
        if (map != null) { //处理
            for (String o : map.keySet()) {
                intent.putExtra(o, map.get(o));
            }
        }
        startactivity.startActivity(intent);
//        startactivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    /**
     * @param context
     * @param cls     Activity间跳转
     *                实现动画
     */
    public static void startActivity(Context context, Class<?> cls) {
        if (intent == null) {
            intent = new Intent();
        }
        if (cls != null) {
            intent.setClass(context, cls);
        }
        context.startActivity(intent);
//        ((Activity) context).overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    /**
     * Activity间跳转，加左右动画
     *
     * @param context
     * @param cls     目标Activity
     * @param bundle  数据参数，没有参数null
     */
    public static void startActivity(Context context, Class<?> cls, Bundle bundle) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.setClass(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
//        ((Activity) context).overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }


    public static void intentActivity(Activity startactivity, Intent intent) {
        if (intent == null) {
            return;
        }
        startactivity.startActivity(intent);
//        startactivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    /**
     * 设置Activity返回接收数据 配套 setResult()使用
     *
     * @param context
     * @param intent
     */
    public static void startActivityForResult(Context context, Intent intent, int intentCode) {
        if (intent == null) {
            return;
        }
        ((Activity) context).startActivityForResult(intent, intentCode);
//        ((Activity) context).overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    /**
     * 设置Activity返回接收数据 配套 setResult()使用
     *
     * @param startactivity
     * @param intent
     */
    public static void startActivityForResult(Activity startactivity, Intent intent, int intentCode) {
        if (intent == null) {
            return;
        }
        startactivity.startActivityForResult(intent, intentCode);
//        startactivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    public static void setResult(Activity startactivity, Intent intent, int result) {
        if (intent == null) {
            return;
        }
        startactivity.setResult(result, intent);
        startactivity.finish();
//        startactivity.overridePendingTransition(R.anim.right_in, R.anim.right_out);

    }

    public static void intentResultActivity(Activity startactivity, Class<?> endactivity, Map<String, String> map, int requestCode) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.setClass(startactivity, endactivity);
        if (map != null) { //处理
            for (String o : map.keySet()) {
                intent.putExtra(o, map.get(o));
            }
        }
        startactivity.startActivityForResult(intent, requestCode);
//        startactivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    /**
     * @param endactivity 结束当前activity的动画
     */
    public static void endActivity(Activity endactivity) {
        endactivity.finish();
//        endactivity.overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    /**
     * @param endactivity 结束当前程序的动画
     */
    public static void finshActivity(Activity endactivity) {
        endactivity.finish();
    }

    /**
     * @param endactivity 结束当前activity的动画
     */
    public static void homeActivity(Activity endactivity) {
        endactivity.finish();
//        endactivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    /**
     * @param endactivity 结束当前activity的动画
     */
    public static void popActivity(Activity endactivity) {
        endactivity.finish();
//        endactivity.overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
    }

    /**
     * 参数序列化
     *
     * @param strings 参数
     * @return 返回序列化之后的String[]数组
     */
    public String[] initPropertys(String... strings) {
        if (strings[0].equals(""))
            return new String[0];
        return strings;
    }

    private static void intent(Intent intent, String[] key, String[] value) {
        for (int i = 0; i < key.length; i++) {
            intent.putExtra(key[i], value[i]);
        }
    }


    /**
     * @param startactivity
     * @param endactivity   Activity间跳转
     *                      实现动画
     */
    public static void intentActivityUp(Activity startactivity, Class<?> endactivity, Map<String, String> map) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.setClass(startactivity, endactivity);
        if (map != null) { //处理
            for (String o : map.keySet()) {
                intent.putExtra(o, map.get(o));
            }
        }
        startactivity.startActivity(intent);
//        startactivity.overridePendingTransition(R.anim.down_in, R.anim.down_out);
    }

    /**
     * @param endactivity 结束当前activity的动画
     */
    public static void endActivityDowm(Activity endactivity) {
        endactivity.finish();
//        endactivity.overridePendingTransition(R.anim.down_in, R.anim.down_out);
    }
}

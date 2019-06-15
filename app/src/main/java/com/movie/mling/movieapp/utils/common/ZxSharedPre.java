package com.movie.mling.movieapp.utils.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangLianJun on 2016/12/21.
 * 邮箱：695301501@qq.com
 *
 * @desc
 * @tools
 */

public class ZxSharedPre {

    private static  ZxSharedPre zxSharedPreference;
    public static ZxSharedPre getInstance(Context context){
        zxSharedPreference=new ZxSharedPre(context);
        return  zxSharedPreference;
    }
    SharedPreferences sharedPreferences;
    public ZxSharedPre(Context context){
        //打开存储空间 应用内数据共享
        sharedPreferences=context.getSharedPreferences("FirstJobShared", Context.MODE_PRIVATE);
    }

    public void saveToSP(String key, Object value){
        if(value instanceof String){
            sharedPreferences.edit().putString(key, (String)value).commit();
        }else if(value instanceof Boolean){
            sharedPreferences.edit().putBoolean(key, (Boolean)value).commit();
        }else if(value instanceof Float){
            sharedPreferences.edit().putFloat(key, (Float)value).commit();
        }else if(value instanceof Integer){
            sharedPreferences.edit().putInt(key, (Integer)value).commit();
        }else{
            sharedPreferences.edit().putString(key, (String)value).commit();
        }
    }

    public String getString(String key){

        return sharedPreferences.getString(key, "");
    }

    public int getInt(Context context, String key){
        return sharedPreferences.getInt(key, 0);
    }

    public float getFloat(Context context, String key){
        return sharedPreferences.getFloat(key, 0.0f);
    }

    public  float getDouble(Context context, String key){
        return sharedPreferences.getFloat(key, 0);
    }

    public boolean getBoolean(Context context, String key){
        return sharedPreferences.getBoolean(key, false);
    }

    //搜索保存信息
    public void setListInfo(List<String> list, String key){
        if (list!=null&&list.size()>0){
            StringBuilder strB=new StringBuilder();
            for (int i=0;i<list.size();i++){
                if (!TextUtils.isEmpty(list.get(i).toString())) {
                    if (i == list.size() - 1) {
                        strB.append(list.get(i).toString() + "");
                    } else {
                        strB.append(list.get(i).toString() + "").append("`、");
                    }
                }
            }
            sharedPreferences.edit().putString(key, strB.toString()).commit();
        }else{
            sharedPreferences.edit().putString(key, "").commit();
        }
    }
    //查询集合数据
    public List<String> getListInfo(String key){
        List<String> listStr=new ArrayList<>();
        String strList=sharedPreferences.getString(key, "");
        if (!TextUtils.isEmpty(strList)) {
            String[] arrayStr = strList.split("`、");
            if (arrayStr != null && arrayStr.length > 0) {
                for (int i = 0; i < arrayStr.length; i++) {
                    if (!TextUtils.isEmpty(arrayStr[i])) {
                        listStr.add(arrayStr[i]);
                    }
                }
            }
        }
            return listStr;

    }
}

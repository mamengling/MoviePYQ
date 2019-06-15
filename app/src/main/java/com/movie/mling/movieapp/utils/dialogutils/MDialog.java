package com.movie.mling.movieapp.utils.dialogutils;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.movie.mling.movieapp.utils.widget.CustomProgressDialog;

/**
 * Author:zcmain on 2016/5/13 14:10
 * E-Mail:zcmain@163.com
 * 说明：
 */
public class MDialog {
    private volatile static MDialog mDialog = null;
    private static Context mContext;
    private CustomProgressDialog mProgressDialog;

    private MDialog() {

    }

    //
//    /**
//     *
//     * @param context
//     * @return
//     */
//    public static MDialog getInstance(Context context) {
//        if (mDialog == null) {
//            synchronized (MDialog.class) {
//                if (mDialog == null) {
//                    mDialog = new MDialog();
//                }
//            }
//        }
//        mContext = context;
//        return mDialog;
//    }
    public static MDialog getInstance(Context context) {
        mContext = context;
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final MDialog sInstance = new MDialog();
    }

    /**
     * 打开弹窗
     * 作者：MLing
     * 创建时间 ：2017/6/9 16:56.
     */
    public void showProgressDialog() {
//        closeProgressDialog();
//        mProgressDialog = new CustomProgressDialog(mContext, Constants.DIALOG_MSG, R.drawable.refresh_animation);
//        mProgressDialog.show();
        SuccinctProgress.dismiss();
        SuccinctProgress.showSuccinctProgress(mContext, "加载中...", SuccinctProgress.THEME_ULTIMATE, true, true);
    }

    /**
     * 关闭弹窗
     * 作者：MLing
     * 创建时间 ：2017/6/9 16:55.
     */
    public void closeProgressDialog() {
//        if (mProgressDialog != null && mProgressDialog.isShowing())
//            mProgressDialog.cancel();
        SuccinctProgress.dismiss();
    }

    /**
     * @param msg
     */
    public void showToast(String msg) {
        if (!TextUtils.isEmpty(msg))
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param msg
     */
    public void showToast(View view, String msg) {
        if (!TextUtils.isEmpty(msg)) {
//            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(Color.parseColor("#FEB500"));
            snackbar.show();
        }
    }
}

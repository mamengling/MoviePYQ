package com.movie.mling.movieapp.utils.common;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.utils.common.log.LogUtil;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：MLing on 2016/8/17 0017 10:25
 * 邮箱：mamenglingkl1314@163.com
 */
public class AppMethod {
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeToString(buffer, Base64.DEFAULT);
    }

    public static RequestParams getMapParams(String method) {
        RequestParams mapParams = new RequestParams();
        String timestamp = AppMethod.getSecondTimestampTwo();
        mapParams.put("sign", MD5Utils.Md5("vfbw4UdPkHfSwMVh|" + method + "|" + timestamp));
        mapParams.put("timestamp", timestamp);
        return mapParams;
    }


    public String Bitmap2Base64(Bitmap bitmap) {
        String bitmap2String = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bytes = bos.toByteArray();
        bitmap2String = Base64.encodeToString(bytes, Base64.DEFAULT);
        return bitmap2String;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);

        return str;
    }

    public static String byte2hex(byte[] b) {// 二进制转字符串
        StringBuffer sb = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                sb.append("0" + stmp);
            } else {
                sb.append(stmp);
            }
        }
        return sb.toString();
    }

    /**
     * 获取本机手机号
     */
    public static String getPhoneNumber(Context context) {
        TelephonyManager mTel = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        String tel = mTel.getLine1Number();
        return tel;

    }

    /**
     * 获取手机的序列号imei
     *
     * @param context
     * @return
     */
    public static String getDeviceIMEI(final Context context) {
        if (TextUtils.isEmpty(SharePreferenceUtil.getString(context, UserConfig.USER_DEVICE_IMEI, ""))) {
            try {
                TelephonyManager mTm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return "";
                }
                LogUtil.i("USER_DEVICE_IMEI 2", mTm.getDeviceId());
                return mTm.getDeviceId();
            } catch (Exception e) {
                LogUtil.i("USER_DEVICE_IMEI 2", e.getMessage());
                return "";
            }
        } else {
            LogUtil.i("USER_DEVICE_IMEI 1", SharePreferenceUtil.getString(context, UserConfig.USER_DEVICE_IMEI, ""));
            return SharePreferenceUtil.getString(context, UserConfig.USER_DEVICE_IMEI, "");
        }
    }

    /**
     * 获取手机的序列号imei
     *
     * @param context
     * @return
     */
    public static String getDeviceIMEIOnley(Context context) {
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);

        //获取用户唯一标示
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        LogUtil.i("USER_DEVICE_IMEI 2", mTm.getDeviceId());
        return mTm.getDeviceId();
    }


    // 将时间戳转为字符串
    public static String getStrTime(long cc_time) {

        Date date = new Date(cc_time * 1000L);
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");//获取中国的时区
            sdf.setTimeZone(timeZoneChina);//设置系统时区
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }

    // 将时间戳转为字符串
    public static String getStrNewTime(long cc_time) {

        Date date = new Date(cc_time * 1000L);
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");//获取中国的时区
            sdf.setTimeZone(timeZoneChina);//设置系统时区
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }

    // 将时间戳转为字符串
    public static String getStrDataNoTime(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    // 将时间戳转为字符串
    public static String getStrDataTime(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    // 将字符串转为时间戳
    public static long getTimeC(String cc_time) {
        if (TextUtils.isEmpty(cc_time)) {
            return 00000000;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = simpleDateFormat.parse(cc_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timeStemp = date.getTime();
        return timeStemp;
    }

    /**
     * 获取精确到秒的时间戳
     *
     * @return
     */
    public static String getSecondTimestampTwo() {
        Date date = new Date();
        if (null == date) {
            return "";
        }
        String timestamp = String.valueOf(date.getTime() / 1000);
        return timestamp;
    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     *
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param mobiles 手机号正则
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * @param mobiles 身份证号正则
     * @return
     */
    public static boolean isVerifyNO(String mobiles) {
        Pattern p = Pattern.compile("^var reg = /(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/;");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static long mGetTime(String time) {
        long unixTimestamp = 0;
        try {
            Date date = new SimpleDateFormat("yyyy/MM").parse(time);
            unixTimestamp = date.getTime() / 1000;
            System.out.println(unixTimestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return unixTimestamp;
    }

    /**
     * 从文件中读取地址数据
     */
    public static String initJsonData(Context mContext, String fileName) {
        String addressAllString = "";
        AssetManager assetManager = mContext.getAssets();
        try {
            InputStream is = assetManager.open(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer stringBuffer = new StringBuffer();
            String str = null;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
            addressAllString = stringBuffer.toString();
            LogUtil.d("tag", "loadingLocalJson:字符串 " + addressAllString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressAllString;
    }


    public static String starPhone(String tel) {
        // 括号表示组，被替换的部分$n表示第n组的内容
        if (!TextUtils.isEmpty(tel))
            tel = tel.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return tel;
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }
}

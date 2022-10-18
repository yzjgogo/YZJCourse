package zhl.common.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import zhl.common.base.BaseActivity;

public class Tools {

    public static float toFloat(Object obj) {
        try {
            String str = obj.toString();
            Float value = Float.valueOf(str);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int toInt(Object obj) {
        try {
            String str = obj.toString();
            // 浮点型处理
            if (str.contains(".")) {
                if (str.indexOf(".") > 0)
                    str = str.substring(0, str.indexOf("."));
            }
            int value = Integer.valueOf(str);

            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Boolean isEmpty(Object obj) {
        try {
            if (obj == null)
                return true;
            if (obj.toString().trim().equals(""))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public static String toStr(Object str) {
        if (str == null || str.equals("null")) {
            return "";
        } else {
            String string = String.valueOf(str);
            string = string.trim();
            return string;
        }
    }

    public static String toF(Object str, int num) {
        try {
            String zero = "";
            for (int i = 0; i < num; i++) {
                zero += "0";
            }
            DecimalFormat df = new DecimalFormat("#." + zero);
            return df.format(str);
        } catch (Exception e) {
            return "";
        }
    }

    public static String toF2(Object str) {
        return toF(str, 2);
    }

    public static String toPriceF2(Integer v) {
        try {
            float fv = (float) v / 100F;
            DecimalFormat df = new DecimalFormat("#.00");
            if (v == 0) {
                return "0.00";
            }
            return df.format(fv);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 返回符号
     *
     * @param v
     * @return
     * @author ylf
     * @createTime Apr 26, 2014 12:54:49 AM
     */
    public static String toDPriceF2(Integer v) {
        try {
            float fv = (float) v / 100F;
            DecimalFormat df = new DecimalFormat("#.00");
            if (v == 0) {
                return "¥ 0.00";
            }
            return "¥ " + df.format(fv);
        } catch (Exception e) {
            return "";
        }
    }

    public static int getDimens(Context context, int dimensId) {
        return context.getResources().getDimensionPixelSize(dimensId);
    }

    public static String cutStr(Object str, Integer length) {
        if (str == null || str.equals("null")) {
            return "";
        } else {
            String string = String.valueOf(str);
            string = string.trim();
            if (string.length() <= length)
                return string;
            else {
                return string.substring(0, length - 1);
            }
        }
    }

    public static String getFileString(String path) {
        String str = path;

        File f = new File(str);
        if (f.exists()) {
            try {
                FileInputStream inStream = new FileInputStream(f);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                int len = -1;
                byte[] buffer = new byte[1024];
                while ((len = inStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }

                byte[] data = outputStream.toByteArray();
                return new String(data, "utf-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "";
    }

    /**
     * 判断sd卡是否存在
     **/
    public static boolean isSdCardExist() {
        return android.os.Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 取SD卡路径
     **/
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = isSdCardExist();
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory(); // 获取根目录
        }
        if (sdDir != null) {
            return sdDir.toString();
        } else {
            return "";
        }
    }

    /**
     * 获取输入流
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static InputStream GetInputStream(String str) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(str.getBytes("utf-8"));
    }

    public static String StrToBase64(String strIn) {
        return Base64.encodeToString(strIn.getBytes(), Base64.DEFAULT).trim();
    }

    public static String Base64ToStr(String strIn) {
        byte b[] = android.util.Base64.decode(strIn, Base64.DEFAULT);
        return new String(b);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int spTopx(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dipToPxDemons(Context context, int dimensid) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (context.getResources().getDimensionPixelSize(dimensid) * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int pxToDip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 用普通context获取宽度，当隐藏、显示虚拟导航键时，获取不准确
     *
     * @param context
     * @return
     * @author zqs
     * @createTime 2015年3月21日 下午3:13:16
     */
    public static int getScreenWidth(Context context) {
        if (context != null) {
            Resources res = context.getResources();
            DisplayMetrics metrics = res.getDisplayMetrics();
            return metrics.widthPixels;
        }
        return 0;
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * 用普通context获取高度，当隐藏、显示虚拟导航键时，获取不准确
     *
     * @param context
     * @return
     * @author zqs
     * @createTime 2015年3月21日 下午3:13:16
     */
    public static int getScreenHeight(Context context) {
        Resources res = context.getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        return metrics.heightPixels;
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;

    }

    public static Drawable BitmapTodrawable(Bitmap bmp) {
        return new BitmapDrawable(bmp);
    }

    /**
     * 安装文件
     *
     * @param path 文件路径（不包含文件名称）
     * @param name 文件名称
     * @param c
     */
    public static void installApk(String path, String name, Context c) {
        File apkfile = new File(path, name);
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(i);
    }

    /**
     * 安装文件
     *
     * @param path 文件路径包含文件名称
     * @param c
     */
    public static void installApk(String path, Context c) {
        File apkfile = new File(path);
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        installApkN(c, apkfile);
    }

    private static void installApkN(Context context, File apkfile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", apkfile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            MLog.d("yy----AndroidN安装");
        } else {
            intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
            MLog.d("yy----低版本安装");
        }
        context.startActivity(intent);
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;
        } catch (NameNotFoundException e1) {
            e1.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (NameNotFoundException e1) {
            e1.printStackTrace();
        }
        return versionName;
    }

    public static void mkdir(String dir) {
        File file = new File(dir);
        // 判断文件目录是否存在
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * 获取客户端ip地址
     *
     * @return
     */
    public static String getHostIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> ipAddr = intf.getInetAddresses(); ipAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = ipAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 获取当前
     */

    public static boolean getSystemService(Context context, String servicename) {
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> mserviceTasks = mActivityManager.getRunningTasks(100);
        for (RunningTaskInfo serinfo : mserviceTasks) {
            if (serinfo.baseActivity.getPackageName().equals(servicename)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasRecentTaskByPkg(Context context, String pkg) {
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> mserviceTasks = mActivityManager.getRunningTasks(100);
        for (RunningTaskInfo serinfo : mserviceTasks) {
            ComponentName cn = serinfo.topActivity;
            String pkgString = cn.getPackageName();
            if (pkgString.equals(pkg)) {
                return true;
            }
        }
        return false;
    }

	/* ==========ui====== */

    public static void toast(Context c, int srcId) {
        if (c != null) {
            Toast toast = Toast.makeText(c, srcId, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public static void toast(Context c, String str) {
        if (c != null && !Tools.isEmpty(str)) {
            Toast toast = Toast.makeText(c, str, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public static String getFromAssets(String fileName, Context mContext) {
        try {
            InputStreamReader inputReader = new InputStreamReader(mContext.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 时间戳转换成指定格式输出
     *
     * @param timestamp 时间戳
     * @param formatStr yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String formatTimestamp(long timestamp, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        String date = sdf.format(new Date(timestamp));
        return date;
    }

    /**
     * 秒转换成 00:00:00输出
     *
     * @param timeCount 秒
     * @return 00:00:00
     */
    public static String formatSecondString(int timeCount) {
        int hour = timeCount / 3600;
        int minute = (timeCount % 3600) / 60;
        int second = timeCount % 60;
        String hou = hour >= 10 ? hour + "" : "0" + hour;
        String min = minute >= 10 ? minute + "" : "0" + minute;
        String sec = second >= 10 ? second + "" : "0" + second;
        return hou + ":" + min + ":" + sec;
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                result = EncryptTool.base64Encode(bitmapBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * 校验电话号码的合法性
     *
     * @param mobileNumber
     * @return
     * @author yuxh
     * @createTime 2015年7月2日 上午10:46:21
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("1\\d{10}");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /***
     * 真实姓名合法性检查。 2~7个中文
     *
     * @return
     * @author zqs
     * @createTime 2015年12月9日 下午9:18:26
     */
    public static boolean checkChineseRealName(String realName) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("[\u4E00-\u9FA5]{2,7}");
            Matcher matcher = regex.matcher(realName);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 获取application里面配置的值
     *
     * @return
     * @author yuxh
     * @createTime 2014年11月27日 下午6:01:56
     */
    public static String getMetaData(String name, Context context) {
        if (context == null) {
            return "";
        }
        // 在application应用<meta-data>元素。
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.get(name).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param context
     * @return 1:android phone; 2:android:pad; 3:iphone; 4:ipad;
     * @author zqs
     * @createTime 2016年03月15日14:13:23
     */
    public static String getTerminalType(Context context) {
        String terminalType = "1";
        try {
            terminalType = (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE ? "2" : "1";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return terminalType;
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     *
     * @param str
     * @return
     * @author yuxh
     * @createTime 2014年12月9日 上午3:02:25
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 获取线的唯一标识
     *
     * @return
     * @author yuxh
     * @createTime 2015年1月4日 下午8:57:41
     */
    public static long getSequenceId() {
        long l = System.currentTimeMillis();
        Random rd = new Random();
        return Long.parseLong(l + "" + rd.nextInt(10000));
    }

    /**
     * @return
     * @author yuxh
     * @createTime 2015年1月8日 下午5:52:56
     */
    public static int getIntSeq() {
        return (int) (System.currentTimeMillis() / 1000l);
    }

    /**
     * 将任意日期的时间设置成00:00
     *
     * @return
     * @author zqs
     * @createTime 2015年1月27日 下午4:37:15
     */
    public static long convertTime00_00(long dateTimeMills) {
        boolean isTimeMills = true; // 是否精确到毫秒
        String nowTime = System.currentTimeMillis() + ""; // 精确到毫秒级别的时间戳长度
        String target = dateTimeMills + "";
        // 非标准的 毫秒或者秒时间 不予转换
        if (nowTime.length() - target.length() != 3 && nowTime.length() - target.length() != 0) {
            return dateTimeMills;
        }
        if (nowTime.length() - target.length() == 3) {
            isTimeMills = false;
            dateTimeMills = dateTimeMills * 1000;
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.setTimeInMillis(dateTimeMills);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (!isTimeMills)
            return calendar.getTimeInMillis() / 1000;
        else {
            return calendar.getTimeInMillis();
        }
    }

    /**
     * 将任意日期的时间设置成23:59 ， 精确到毫秒
     *
     * @return
     * @author zqs
     * @createTime 2015年1月27日 下午4:37:15
     */
    public static long convertTime23_59(long dateTimeMills) {
        boolean isTimeMills = true; // 是否精确到毫秒
        String nowTime = System.currentTimeMillis() + ""; // 精确到毫秒级别的时间戳长度
        String target = dateTimeMills + "";
        // 非标准的 毫秒或者秒时间 不予转换
        if (nowTime.length() - target.length() != 3 && nowTime.length() - target.length() != 0) {
            return dateTimeMills;
        }
        if (nowTime.length() - target.length() == 3) {
            isTimeMills = false;
            dateTimeMills = dateTimeMills * 1000;
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.setTimeInMillis(dateTimeMills);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        if (!isTimeMills)
            return calendar.getTimeInMillis() / 1000;
        else
            return calendar.getTimeInMillis();
    }
    //获取指定毫秒数的对应星期
    public static String getWeek(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        String week = "";
        int cweek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (cweek) {
            case 1:
                week = "日";
                break;
            case 2:
                week = "一";
                break;
            case 3:
                week = "二";
                break;
            case 4:
                week = "三";
                break;
            case 5:
                week = "四";
                break;
            case 6:
                week = "五";
                break;
            case 7:
                week = "六";
                break;
        }
        return week;

    }

    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     *
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 获取当前Url链接的域名
     *
     * @param url
     * @return
     */
    public static String getHttpHost(String url) {
        if (url == null) {
            return "";
        }
        if (!url.contains("://")) {
            return "";
        }
        String[] http = url.split("://");
        String[] host = url.split("/");
        return http[0] + "://" + host[2] + "/";
    }

    /**
     * 适配.net和java的token
     *
     * @param accessToken 原始token
     * @return 兼容的token, .net 带bearer java不带
     */
    public static String getAccessToken(@NonNull String accessToken) {
        if (accessToken.length() <= 64) {
            return accessToken;
        }
        return "bearer " + accessToken;
    }

    public static boolean isNetToken(String accessToken) {
        return accessToken.contains("fxd!");
    }

    // 获取ApiKey
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return apiKey;
    }
    //获取虚拟按键的高度
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (hasNavBar(context)) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 检查是否存在虚拟按键栏
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     *
     * @return
     */
    public static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }

    /**
     * 判断虚拟按键栏是否显示
     *
     * @return
     */
    public static boolean isNavigationBarShow(Context mContext) {
        if (mContext instanceof BaseActivity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                Display display = ((BaseActivity) mContext).getWindowManager().getDefaultDisplay();
                Point size = new Point();
                Point realSize = new Point();
                display.getSize(size);
                display.getRealSize(realSize);
                boolean result = realSize.y != size.y;
                return realSize.y != size.y;
            } else {
                boolean menu = ViewConfiguration.get(mContext).hasPermanentMenuKey();
                boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
                if (menu || back) {
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            return false;
        }

    }
    private static long lastClickTime = 0;
    private static long DIFF = 1000;
    private static int lastButtonId = -1;
    /**
     * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击
     *
     * @param diff
     * @return
     */
    public static boolean isFastDoubleClick(int buttonId, long diff) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < diff) {
            MLog.v("isFastDoubleClick", "短时间内按钮多次触发");
            MLog.v("isFastDoubleClick", timeD+"");
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }
    /**
     * 列表分组
     * @param list  分组的列表
     * @param quantity 一组的个数
     * @return
     */
    public static List groupListByQuantity(List list, int quantity) {
        if (list == null || list.size() == 0) {
            return list;
        }

        if (quantity <= 0) {
            new IllegalArgumentException("Wrong quantity.");
        }

        List wrapList = new ArrayList();
        int count = 0;
        while (count < list.size()) {
            wrapList.add(new ArrayList(list.subList(count, (count + quantity) > list.size() ? list.size() : count + quantity)));
            count += quantity;
        }

        return wrapList;
    }
    /**
     * 生成0 到size-1之间的四个不重复的随机数
     *
     * @param size
     * @return
     */
    public static int[] getRandomNumber(int size) {
        if (size <= 4 && size > 0) {
            int[] arr = new int[size];
            for (int j = 0; j <= size - 1; j++) {
                arr[j] = j;
            }
            return arr;
        } else {
            Random random = new Random();
            int[] arr = new int[4];
            arr[0] = random.nextInt(size - 1);
            int i = 1;
            //外循环定义四个数
            while (i <= 3) {
                int x = random.nextInt(size - 1);
                /*内循环：新生成随机数和已生成的比较，
                 *相同则跳出内循环，再生成一个随机数进行比较
                 *和前几个生成的都不同则这个就是新的随机数
                 */
                for (int j = 0; j <= i - 1; j++) {
                    //相同则跳出内循环，再生成一个随机数进行比较
                    if (arr[j] == x) {
                        break;
                    }
                    //执行完循环和前几个生成的都不同则这个就是新的随机数
                    if (j + 1 == i) {
                        arr[i] = x;
                        i++;
                    }
                }
            }
            return arr;
        }
    }
}

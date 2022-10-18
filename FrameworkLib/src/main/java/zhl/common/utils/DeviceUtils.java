package zhl.common.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class DeviceUtils {
    private static float textDensity = 0;
    private static float textScaledDensity = 0;

    /**
     * 今日头条的屏幕适配方案
     * 根据当前设备物理尺寸和分辨率去动态计算density、densityDpi、scaledDensity
     * 同时也解决了用户修改系统字体的情况
     * @param activity
     * @param application
     */
    public static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application) {
        setCustomDensity(activity, application, false, false);
    }


    /**
     * @param activity
     * @param application
     * @param isLandscape 是否是横屏
     */
    public static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application, boolean isLandscape, boolean isSystem) {
        final DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        SystemDisplayMetrics system = new SystemDisplayMetrics();
        system.density = 2.0f; //新平板物理尺寸,对于不做适配的轨迹视频刚好合适
        system.scaledDensity = 2.0f;
        system.densityDpi = 240;

        if (textDensity == 0) {
            textDensity = displayMetrics.density;
            textScaledDensity = displayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration configuration) {
                    if (configuration != null && configuration.fontScale > 0) {
                        textScaledDensity = displayMetrics.scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        final float targetDensity;
        if (isLandscape) {//横屏
            targetDensity = displayMetrics.widthPixels / 667f; //当前UI标准1200*1920
        } else {
            targetDensity = displayMetrics.widthPixels / 375f; //当前UI标准1200*1920
        }

        final float targetScaledDensity = targetDensity * (textScaledDensity / textDensity);
        final int targetDpi = (int) (160 * targetDensity);

        if (isSystem) {
            displayMetrics.density = system.density;
            displayMetrics.scaledDensity = system.scaledDensity;
            displayMetrics.densityDpi = system.densityDpi;
        } else {
            displayMetrics.density = targetDensity;
            displayMetrics.scaledDensity = targetScaledDensity;
            displayMetrics.densityDpi = targetDpi;
        }

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        if (isSystem) {
            activityDisplayMetrics.density = system.density;
            activityDisplayMetrics.scaledDensity = system.scaledDensity;
            activityDisplayMetrics.densityDpi = system.densityDpi;
        } else {
            activityDisplayMetrics.density = targetDensity;
            activityDisplayMetrics.scaledDensity = targetScaledDensity;
            activityDisplayMetrics.densityDpi = targetDpi;
        }
    }

    /** 检测是否是中兴机器 */
    public static boolean isZte() {
        return getDeviceModel().toLowerCase().indexOf("zte") != -1;
    }
    /**
     * 获得设备型号
     *
     * @return
     */
    public static String getDeviceModel() {
        return trim(Build.MODEL);
    }

    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    public static String getSystemModel() {

        return Build.BRAND + Build.MODEL;
    }
}



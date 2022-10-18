package zhl.common.request;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.text.TextUtils;

import java.util.Locale;

import zhl.common.utils.CommonApkShare;
import zhl.common.utils.Tools;

/**
 * @author ylf
 * @createTime 2014-6-14 上午11:21:00
 */
public class UserAgentUtils {

    private static String sUserAgent = "";
    private static String deviceId = "";// 设备序列好
    private static String deviceType = "";// 机型
    private static String OS = "";//

    private UserAgentUtils() {
        // No public constructor
    }

    /**
     * Get the User-Agent with the following syntax:
     * <p>
     * Mozilla/5.0 (Linux; U; Android {Build.VERSION.RELEASE}; {locale.toString()}[; {Build.MODEL}] [; Build/{Build.ID}]) {getPackageName()}/{getVersionCode()}
     *
     * @param context The context to use to generate the User-Agent.
     * @return The User-Agent.
     */
    public synchronized static String get(Context context) {
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        if (Tools.isEmpty(sUserAgent)) {
            if ("".equals(deviceId)) {
                deviceId = CommonApkShare.getDeviceId(context);
            }
            if ("".equals(deviceType)) {
                deviceType = CommonApkShare.getModel();
            }
            if ("".equals(OS)) {
                OS = CommonApkShare.getOS();
            }
            sUserAgent = generate(context);
        }
        return sUserAgent;
    }

    private static String generate(Context context) {
        StringBuilder sb = new StringBuilder();

        sb.append("Mozilla/5.0 (Linux; Android; OS/Android");
        sb.append(Build.VERSION.RELEASE);
        sb.append("; Terminal/" + Tools.getTerminalType(context) + "; ");
        sb.append(Locale.getDefault().toString());

        String model = Build.MODEL;
        if (!TextUtils.isEmpty(model)) {
            sb.append("; deviceType/");
            sb.append(model);
        }

        if (!TextUtils.isEmpty(deviceId)) {
            sb.append("; DeviceId/");
            sb.append(deviceId);
        }

        String buildId = Build.ID;
        if (!TextUtils.isEmpty(buildId)) {
            sb.append("; Build/");
            sb.append(buildId);
        }

        int versionCode = 0;
        String packageName = context.getPackageName();
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo packageInfo = manager.getPackageInfo(packageName, 0);
            versionCode = packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            // Keep the versionCode 0 as default.
        }

        sb.append("; Scope/" + packageName);
        sb.append("; VersionCode/" + versionCode);
        sb.append(";) ");

        return sb.toString();
    }

    public static String getsUserAgent() {
        return sUserAgent;
    }

}

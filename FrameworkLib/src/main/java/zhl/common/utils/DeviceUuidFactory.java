package zhl.common.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import androidx.core.app.ActivityCompat;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class DeviceUuidFactory {
    protected static final String PREFS_FILE = "device_id.xml";
    protected static final String PREFS_DEVICE_ID = "device_id";
    protected static UUID uuid;

    public DeviceUuidFactory(Context context) {
        if (uuid == null) {
            synchronized (DeviceUuidFactory.class) {
                if (uuid == null) {
                    final SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, 0);
                    final String id = prefs.getString(PREFS_DEVICE_ID, null);
                    if (id != null) {
                        // Use the ids previously computed and stored in the
                        // prefs file
                        uuid = UUID.fromString(id);
                    } else {
                        final String androidId = GetANDROIDID(context);
                        // Use the Android ID unless it's broken, in which case
                        // fallback on deviceId,
                        // unless it's not available, then fallback on a random
                        // number which we store
                        // to a prefs file
                        try {
                            if (!"9774d56d682e549c".equals(androidId)) {
                                uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                            } else {
                                final String deviceId = GetDeviceid(context);
                                uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")) : UUID.randomUUID();
                            }
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                        // Write the value out to the prefs file
                        prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString()).commit();
                    }
                }
            }
        }
    }

    /**
     * 获取md5加密的设备号
     *
     * @param mContext
     * @return
     */
    public static String GetCombineDeviceID(Context mContext) {
        return GetCombineDeviceID(mContext, "key!12@3");
    }

    /**
     * 获取OS版本
     *
     * @return
     * @author yuxh
     * @createTime 2014年9月28日 下午10:27:26
     */
    public static String getOS() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return
     * @author yuxh
     * @createTime 2014年9月28日 下午10:29:19
     */
    public static String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取md5加密的设备号
     *
     * @param mContext
     * @return
     */
    public static String GetCombineDeviceID(Context mContext, String desKey) {
        String deviceid = GetCombineDeviceIDNormal(mContext, desKey);
        if (!deviceid.equals("")) {
            try {
                return MD5Tool.md5Digest(deviceid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deviceid;
    }

    public static String GetCombineDeviceIDNormal(Context mContext, String desKey) {
        if (NetHp.isNetworkAvailable(mContext)) {
            String combineDeviceIdFormat = "%s|%s|%s";
            String deviceid = GetDeviceid(mContext);
            String buildSerial = GetANDROIDID(mContext);
            String macaddress = GetWifiMac(mContext);
            String combineId = String.format(combineDeviceIdFormat, deviceid, buildSerial, macaddress);
            try {
                return EncryptTool.desEncrypt(combineId, desKey);
            } catch (Exception e) {
                try {
                    return UUID.nameUUIDFromBytes(combineId.getBytes("utf8")).toString();
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                    return combineId;
                }
            }
        }
        return "";
    }

    public static String GetANDROIDID(Context mContext) {
        return Secure.getString(mContext.getContentResolver(), Secure.ANDROID_ID);
    }

    public static String GetDeviceid(Context context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PERMISSION_GRANTED){
                return null;
            }
        }
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String GetSerialNumber(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if(ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PERMISSION_GRANTED){
                    return null;
                }
                return Build.getSerial();
            }
            String serial = Build.SERIAL;
            if(TextUtils.isEmpty(serial) || serial.equals(Build.UNKNOWN))return null;
            return serial;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static String GetWifiMac(Context mContext) {
        /*WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
        return m_szWLANMAC == null ? "" : m_szWLANMAC;*/
        return "";
    }

    // public static String GetBuild() {
    // String str = "BOARD=" + Build.BOARD + " BRAND=" + Build.BRAND +
    // " CPU_ABI=" + Build.CPU_ABI + " DEVICE=" + Build.DEVICE + " DISPLAY" +
    // Build.DISPLAY + " HOST="
    // + Build.HOST + " ID=" + Build.ID + " MANUFACTURER=" + Build.MANUFACTURER
    // + " MODEL=" + Build.MODEL + " PRODUCT=" + Build.PRODUCT + " TAGS=" +
    // Build.TAGS + " TYPE="
    // + Build.TYPE + " SERIAL=" + Build.SERIAL + " USER=" + Build.USER; // 13
    // // digits
    // return str;
    // }

    public static String GetBuildSERIALs() {
        String serial = Build.SERIAL;
        return serial == null ? "" : serial;
    }
}
package zhl.common.my;


import android.content.Context;

import androidx.annotation.NonNull;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by 阿毛 on 2016/3/10.
 */
public class DigestUtils {

    public static String getDigest(Map<String, String> params) throws Exception {
        String secret = "mingyi!@#$%";
//        String secret = MYiCache.getObject(CommonConstants.SALT_VALUE);
        Set<String> keySet = params.keySet();
        Set<String> sortSet = new TreeSet<>();
        sortSet.addAll(keySet);
        StringBuilder kvStr = new StringBuilder();
        for (String key : sortSet) {
            String value = params.get(key);
            kvStr.append(key).append(value);
        }
        kvStr.append(secret);
        byte[] bytes = getMD5(kvStr.toString());
        return Base64.encode(bytes);

    }

    public static boolean validate(Map<String, String[]> params, String digest) throws Exception {
        String secret = "mingyi!@#$%";
        Set<String> keySet = params.keySet();
        Set<String> sortSet = new TreeSet<>();
        sortSet.addAll(keySet);
        StringBuilder kvStr = new StringBuilder();
        for (String key : sortSet) {
            String[] values = params.get(key);
            kvStr.append(key).append(values[0]);
        }
        kvStr.append(secret);
        byte[] bytes = getMD5(kvStr.toString());
        String base64Str = Base64.encode(bytes);
        return base64Str.equals(digest);

    }

    public static byte[] getMD5(String content) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(content.getBytes(StandardCharsets.UTF_8));
    }


//    public static long getAppTime(@NonNull Context context) {
//        // 如果本地时间大于服务器时间，本地时间需要减去差值反之则加
//        // 具体参考UpdateConfig.getServerTime()处理方式
//        long diffValue = RxCache.getLong(context, RxCache.CACHE_KEY_SERVER_TIME);
//        return DateUtil.getSystemCurrentTimeMillis() - (diffValue);// diffValue可能是正数也有可能是负数
//    }
}

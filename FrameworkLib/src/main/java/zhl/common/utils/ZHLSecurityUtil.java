package zhl.common.utils;

//import com.zhl.security.sdk.SecurityTool;

/**
 * Time: 2020/6/3
 * Author: qj
 * Description:
 */
public class ZHLSecurityUtil {

    /**
     * 数据签名
     *
     * @param data
     * @return
     */
    public static String sign(String data) {
        return "tem";
//        return SecurityTool.sign(data);
    }

    /**
     * 数据加密
     *
     * @param data
     * @return
     */
    public static String encrypt(String data) {
        return "";
//        return SecurityTool.encrypt(data);
    }

    /**
     * 数据解密
     *
     * @param data
     * @return
     */
    public static String decrypt(String data) {
        return "";
//        return SecurityTool.decrypt(data);
    }

}

package zhl.common.request.eas;

import android.text.TextUtils;
import android.util.Base64;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import zhl.common.utils.DebugUrl;
import zhl.common.utils.JsonHp;

public class AesEncryptUtils {
    public static final String MSG_CODE_PATH = "checkmsgcodeencrypted";
    public static final String APPLY_REGIST_PATH = "applyregistencrypted";
    public static final String REGISTER_INFO_PATH = "getregisterinfoencrypted";
    public static final String BINDING_INFO_PATH = "submitbindinginfoencrypted";

    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * AES加密
     *
     * @param content 待加密文本
     * @param key     密钥
     * @return
     */
    public static String encrypt(String content, String key, String charset) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes(charset);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(key.getBytes(charset), KEY_ALGORITHM));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //通过Base64转码返回
//            return Base64Utils.encodeToString(result);
            return Base64.encodeToString(result, Base64.NO_WRAP);

        } catch (Exception e) {

        }

        return null;
    }

    /**
     * AES解密
     *
     * @param content
     * @param key     密钥
     * @return
     */
    public static String decrypt(String content, String key, String charset) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE,
                    new SecretKeySpec(key.getBytes(charset), KEY_ALGORITHM));
            //执行操作
//            byte[] result = cipher.doFinal(Base64Utils.decodeFromString(content));
            byte[] result = cipher.doFinal(Base64.decode(content, Base64.NO_WRAP));

            return new String(result, charset);

        } catch (Exception e) {

        }

        return null;
    }

    public static boolean isNeedEncrypt(String paramString){
        if (!TextUtils.isEmpty(paramString) && (paramString.contains(MSG_CODE_PATH) ||
                paramString.contains(APPLY_REGIST_PATH) ||
                paramString.contains(REGISTER_INFO_PATH) ||
                paramString.contains(BINDING_INFO_PATH))) {
            return true;
        }
        return false;
    }
    public static String getRequestBodyAfterAesEncrypt(String paramString) throws JSONException {
        String secretKey = "zhl@k*^5f45H$r3*";//正式环境的aes秘钥
        if (DebugUrl.HOST_TYPE == DebugUrl.TYPE_DEBUG) {
            secretKey = "fjdskfjssdffdfdf";
        }
        String encryStr = encrypt(paramString,secretKey,"utf-8");
        Map<String, Object> msgcodeMap = new HashMap<>();
        msgcodeMap.put("encrypt_body",encryStr);
        return JsonHp.Serialization(msgcodeMap);
    }
}

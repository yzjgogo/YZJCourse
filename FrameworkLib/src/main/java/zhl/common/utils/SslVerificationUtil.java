package zhl.common.utils;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/**
 * @author hzh
 * @date 2020/6/3
 * 说明：
 */
public class SslVerificationUtil {
    private static Map<String,X509Certificate> serverCerts = new HashMap<>(4);

    public static void init(Context ctx,String...certNames){
        new Thread(()->{
            for (String certName : certNames) {
                try {
                    BufferedInputStream certInput = new BufferedInputStream(ctx.getAssets().open(certName));
                    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                    X509Certificate serverCert = (X509Certificate) certificateFactory.generateCertificate(certInput);
                } catch (IOException | CertificateException e) {
                    MLog.e("证书"+certName+"未能正常初始化");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void checkServerTrusted(X509Certificate[] certs,String serverCertName) throws CertificateException {
        if (certs == null) {
            throw new IllegalArgumentException("checkServerTrusted: X509Certificate array is null");
        }

        if (!(certs.length > 0)) {
            throw new IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
        }

        X509Certificate serverCert = serverCerts.get(serverCertName);
        if(null == serverCert){
            throw new IllegalArgumentException("local serverCert "+serverCertName+" is empty");
        }

        for (X509Certificate cert : certs) {
            cert.checkValidity();
            try {
                cert.verify(serverCert.getPublicKey());
            } catch (InvalidKeyException | SignatureException | NoSuchProviderException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * HostnameVerifier类的verify 校验域名
     * @param session verify方法的session
     * @param urls 域名列表，格式：*.xxfz.com.cn
     * @return 域名是否符合预期
     */
    public static boolean verify(SSLSession session,String...urls){
        HostnameVerifier verifier = HttpsURLConnection.getDefaultHostnameVerifier();
        if(null == urls){
            boolean result1 = verifier.verify("*.xxfz.com.cn", session);
            boolean result2 = verifier.verify("*.zhihuiliu.com", session);
            return result1||result2;
        } else{
            for (String url : urls) {
                if(verifier.verify("*.xxfz.com.cn", session)){
                    return true;
                }
            }
            return false;
        }
    }
}

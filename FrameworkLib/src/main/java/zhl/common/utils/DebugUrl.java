package zhl.common.utils;

import com.lidroid.xutils.BuildConfig;

/**
 * Created by HB on 2017/11/22 0022.
 * Description
 */

public class DebugUrl {
    /**
     * 切换环境
     */
    public static String LOCAL_ADDRESS = "192.168.1.148:8099";

    public static final int TYPE_ONLINE = 1;

    public static final int TYPE_TEST = 2;

    public static final int TYPE_DEBUG = 3;

    public static final int TYPE_LOCAL = 4;
    /** 环境与TYPE_ONLINE一样，只是为了避免在上线前神策或bugly统计到开发和测试期间的信息 */
    public static final int TYPE_DISTRIBUTION = 5;

    // TODO:  要修改环境，建议在gradle.properties文件里改 BuildConfig.ENV_VALUE
    public static int HOST_TYPE = 1;
    // TODO: 2020/6/3 true情况仅限内部环境切换测试使用 打包对外发布需要改为false
    public static boolean APP_INNER = false;
    /**
     * 切换对应的IP名
     */

    //LOCAL_IP
    public static final String ENGLISH_LOCAL_IP = "http://" + LOCAL_ADDRESS + "/zhl-english/api";

    //DEBUG_IP对应的是英语的地址
    public static final String ENGLISH_DEBUG_IP = "http://222.212.88.30:8113/debug-8099/zhl-english/api";
    //巧考
    public static final String QK_DEBUG_IP = "http://222.212.88.30:8113/debug-8085/zhl-qiaokao/api";
    //数学
    public static final String MATH_DEBUG_IP = "http://222.212.88.30:8113/debug-8088/zhl-math/api";
    //语文
    public static final String CHINESE_DEBUG_IP = "http://222.212.88.30:8113/debug-8077/zhl-chinese/api";
    //巧练英语
    public static final String COMMUNITY_DEBUG_IP = "http://222.212.88.30:8113/debug-8066/zhl-community/api";
    //服务端埋点
    public static final String COLLECTION_DEBUG_IP = "http://222.212.88.30:8113/debug-8100/zhl-data-collection/api";

    //DEBUG_IP对应的是英语的地址--------临时测试本地 邱小亮：192.168.50.122  张帆：192.168.50.178:8099
//    public static final String ENGLISH_DEBUG_IP = "http://192.168.1.14:8099/zhl-english/api";

    //TEST_IP对应的是英语的地址
    public static final String ENGLISH_TEST_IP = "http://101.132.26.110:8098/api";
    //数学
    public static final String MATH_TEST_IP = "http://101.132.26.110:8088/zhl-math/api";
    //语文
    public static final String CHINESE_TEST_IP = "http://101.132.26.110:8077/zhl-chinese/api";
    //巧考
    public static final String QK_TEST_IP = "http://101.132.26.110:8085/zhl-qiaokao/api";
    //巧练英语
    public static final String QL_ENGLISH_TEST_IP = "https://zhl-hyw.xxfz.com.cn/zhlcommunity/api";
    //服务端埋点
    public static final String COLLECTION_TEST_IP = "http://101.132.26.110:8100/zhl-data-collection/api";


    /**
     * 正式TOKEN域名
     */
    public static final String ONLINE_HOST_TOKEN = "https://zhl-oauth.xxfz.com.cn";
    /**
     * 上传路径
     */
    public static final String REQUEST_URL_UPLOAD = "http://file-manage.xxfz.com.cn/";
    /**
     * edu接口
     */
    public static final String REQUEST_URL_EDUCATION = "https://zhl-education.xxfz.com.cn/api";
    public static final String REQUEST_URL_EDUCATION_MATH = "https://zhl-education.xxfz.com.cn/api";
    public static final String REQUEST_URL_EDUCATION_CHINESE = "https://zhl-education.xxfz.com.cn/api";
    //巧考线上
    public static final String REQUEST_URL_QK = "https://zhl-qiaokao.xxfz.com.cn/api";
    //巧练英语线上
    public static final String REQUEST_URL_QL_ENGLISH = "https://zhl-hyw.xxfz.com.cn/zhlcommunity/api";

    public static final String REQUEST_URL_PAY = "https://api-pay.xxfz.com.cn/api";
    //服务端埋点
    public static final String REQUEST_URL_COLLECTION = "https://zhl-education.xxfz.com.cn/api";


    //神策正式
    public static final String SENSORS_RELEASE = "https://zhl-dc.zhihuiliu.com/sa?project=production";
    //神策测试
    public static final String SENSORS_DEBUG = "https://zhl-dc.zhihuiliu.com/sa?project=default";

    public static String getNewMathUrl() {
        String requestUrl;
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
                requestUrl = MATH_DEBUG_IP;
                break;
            case TYPE_TEST:
                requestUrl = MATH_TEST_IP;
                break;
            case TYPE_DISTRIBUTION:
            case TYPE_ONLINE:
            default:
                requestUrl = REQUEST_URL_EDUCATION_MATH;
                break;
        }
        return requestUrl;
    }

    public static String getNewChineseUrl() {
        String requestUrl;
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
                requestUrl = CHINESE_DEBUG_IP;
                break;
            case TYPE_TEST:
                requestUrl = CHINESE_TEST_IP;
                break;
            case TYPE_DISTRIBUTION:
            case TYPE_ONLINE:
            default:
                requestUrl = REQUEST_URL_EDUCATION_CHINESE;
                break;
        }
        return requestUrl;
    }

    public static String getCommunityUrl() {
        String requestUrl;
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
                requestUrl = COMMUNITY_DEBUG_IP;
                break;
            case TYPE_TEST:
                requestUrl = QL_ENGLISH_TEST_IP;
                break;
            case TYPE_DISTRIBUTION:
            case TYPE_ONLINE:
            default:
                requestUrl = REQUEST_URL_QL_ENGLISH;
                break;
        }
        return requestUrl;
    }


    public static String getEduUrl() {
        String requestUrl;
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
                requestUrl = ENGLISH_DEBUG_IP;
                break;
            case TYPE_LOCAL:
                requestUrl = ENGLISH_LOCAL_IP;
                break;
            case TYPE_TEST:
                requestUrl = ENGLISH_TEST_IP;
                break;
            case TYPE_DISTRIBUTION:
            case TYPE_ONLINE:
            default:
                requestUrl = REQUEST_URL_EDUCATION;
                break;
        }
        return requestUrl;
    }


    public static String getDataCollectUrl() {
        String requestUrl;
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
                requestUrl = COLLECTION_DEBUG_IP;
                break;
            case TYPE_TEST:
                requestUrl = COLLECTION_TEST_IP;
                break;
            case TYPE_DISTRIBUTION:
            case TYPE_ONLINE:
            default:
                requestUrl = REQUEST_URL_COLLECTION;
                break;
        }
        return requestUrl;
    }


    public static String getQKUrl() {
        String requestUrl;
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
                requestUrl = QK_DEBUG_IP;
                break;
            case TYPE_TEST:
                requestUrl = QK_TEST_IP;
                break;
            case TYPE_DISTRIBUTION:
            case TYPE_ONLINE:
            default:
                requestUrl = REQUEST_URL_QK;
                break;
        }
        return requestUrl;
    }


    public static String getUploadUrl() {
        String requestUrl;
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
                requestUrl = "http://222.212.88.30:8113/debug-8055/zhl-file";
                break;
            case TYPE_TEST:
            case TYPE_DISTRIBUTION:
            case TYPE_ONLINE:
            default:
                requestUrl = REQUEST_URL_UPLOAD;
                break;
        }
        return requestUrl;
    }

    public static String getPayUrl() {
        String requestUrl;
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
                requestUrl = ENGLISH_DEBUG_IP;
                break;

            case TYPE_LOCAL:
                requestUrl = ENGLISH_LOCAL_IP;
                break;

            case TYPE_TEST:
                requestUrl = ENGLISH_DEBUG_IP;
                break;
            case TYPE_DISTRIBUTION:
            case TYPE_ONLINE:
            default:
                requestUrl = REQUEST_URL_PAY;
                break;
        }
        return requestUrl;
    }

    public static String getTokenUrl() {
        String requestUrl;
        switch (HOST_TYPE) {

            case TYPE_DEBUG:
                requestUrl = "http://222.212.88.30:8113/debug-8080/zhloauth2-api-project/oauth/token";
                break;

            case TYPE_TEST:
                requestUrl = "http://101.132.26.110:8081/zhloauth2-api-project/oauth/token";
                break;

            case TYPE_DISTRIBUTION:
            case TYPE_ONLINE:
            default:
                requestUrl = ONLINE_HOST_TOKEN + "/oauth/token";
                break;
        }
        return requestUrl;

    }

    //通过token获取userId
    public static String getTokenById() {
        String requestRrl;
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
                requestRrl = ENGLISH_DEBUG_IP + "/information/userinfo/getuseridbytoken";
                break;
            case TYPE_LOCAL:
                requestRrl = ENGLISH_LOCAL_IP + "/information/userinfo/getuseridbytoken";
                break;
            case TYPE_TEST:
                requestRrl = ENGLISH_TEST_IP + "/information/userinfo/getuseridbytoken";
                break;
            case TYPE_DISTRIBUTION:
            case TYPE_ONLINE:
            default:
                requestRrl = "https://zhl-education.xxfz.com.cn/api/information/userinfo/getuseridbytoken";
                break;
        }
        return requestRrl;
    }


    public static String getSensorsUrl() {
        String requestUrl;
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
            case TYPE_TEST:
            case TYPE_DISTRIBUTION:
                requestUrl = SENSORS_DEBUG;
                break;
            case TYPE_ONLINE:
            default:
                requestUrl = SENSORS_RELEASE;
                break;
        }
        return requestUrl;
    }


    //网页
    public static final String WEBVIEW_XXXX_LOCAL_BASE = "http://192.168.50.106:8091";//网页本地地址
    public static final String WEBVIEW_XXXX_DEBUG_BASE = "http://222.212.88.30:8113/debug-8080/xxxx-web";//153服务器
    public static final String WEBVIEW_XXXX_TEST_BASE = "http://222.212.88.30:8113/debug-8080/xxxx-web-110";//110服务器
    public static final String WEBVIEW_XXXX_LINE_BASE = "https://student-primary.zhihuiliu.com";// 线上网页地址

    public static String getWebBaseUrl() {
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
                return WEBVIEW_XXXX_DEBUG_BASE;
            case TYPE_TEST:
                return WEBVIEW_XXXX_TEST_BASE;
            case TYPE_DISTRIBUTION:
            case TYPE_ONLINE:
                return WEBVIEW_XXXX_LINE_BASE;
            default:
                return WEBVIEW_XXXX_LINE_BASE;
        }
    }
    public static final String WEBVIEW_WYS_XXXX_LOCAL_BASE = "http://192.168.2.74:8092";//本地
    public static final String WEBVIEW_WYS_XXXX_DEBUG_BASE = "http://222.212.88.30:8113/debug-8080/qiaokao-web-test";//DEBUG
    public static final String WEBVIEW_WYS_XXXX_TEST_BASE = "http://222.212.88.30:8113/debug-8080/qiaokao-web";//110服务器
    public static final String WEBVIEW_WYS_XXXX_ONLINE_BASE = "https://student-qk.zhihuiliu.com";//线上正式服务器

    public static String getWyWebBaseUrl() {
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
                return WEBVIEW_WYS_XXXX_DEBUG_BASE;
            case TYPE_TEST:
                return WEBVIEW_WYS_XXXX_TEST_BASE;
            case TYPE_ONLINE:
                return WEBVIEW_WYS_XXXX_ONLINE_BASE;
            default:
                return WEBVIEW_WYS_XXXX_ONLINE_BASE;
        }
    }

    public static final String DO_EXERCISE_BASE_URL_DEBUG = "http://222.212.88.30:8113/debug-8080/qiaokao-web-test";
    public static final String DO_EXERCISE_BASE_URL_TEST = "http://222.212.88.30:8113/debug-8080/qiaokao-web";
    public static final String DO_EXERCISE_BASE_URL_ONLINE = "https://student-primary.zhihuiliu.com/app/do-exercise";

    public static String getExerciseBaseUrl() {
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
                return DO_EXERCISE_BASE_URL_DEBUG;
            case TYPE_TEST:
                return DO_EXERCISE_BASE_URL_TEST;
            case TYPE_ONLINE:
                return DO_EXERCISE_BASE_URL_ONLINE;
            default:
                return DO_EXERCISE_BASE_URL_ONLINE;
        }
    }


    //课件地址
    public static String getCourseWareUrl() {
        String requestUrl;
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
                requestUrl = "http://222.212.88.30:8113/d225-8099/zhl-english";
                break;
            case TYPE_TEST:
                requestUrl = "https://zhl-test.xxfz.com.cn";
                break;
            case TYPE_ONLINE:
            default:
                requestUrl = "https://zhl-education.xxfz.com.cn";
                break;
        }
        return requestUrl;
    }

    /**
     * 客服相关地址
     *
     * @return
     */
    public static String getCallCenterUrl() {
        String requestUrl;
        switch (HOST_TYPE) {
            case TYPE_DEBUG:
            case TYPE_TEST:
                requestUrl = "http://192.168.2.36:3001";
                break;
            case TYPE_ONLINE:
            default:
                requestUrl = "http://app-csm.zhihuiliu.com:3001";
                break;
        }
        return requestUrl;
    }
}

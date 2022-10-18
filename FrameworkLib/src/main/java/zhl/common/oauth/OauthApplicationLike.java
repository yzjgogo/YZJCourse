package zhl.common.oauth;

import android.app.Application;
import android.content.Context;
import android.content.Intent;


import androidx.multidex.MultiDexApplication;

import zhl.common.request.AbsResult;
import zhl.common.utils.CommonApkShare;
import zhl.common.utils.MLog;

/**
 * Created by zhaojh
 * Date: 2016/10/19
 * Time: 10:12
 */
public class OauthApplicationLike extends MultiDexApplication {

    private static final String OAUTH_LOGIN_TOKEN_INFO = "OAUTH_LOGIN_TOKEN_INFO";
    private static Application mContext;
    public static OauthApplicationLike instance;
    private Object userEntity; // 用户实体信息
    private TokenEntity tokenEntity; // 用户登录token信息

//    public OauthApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
//        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
//    }

    public static Context getOauthApplicationContext() {
        return mContext;
    }
    public static Application getInstance() {
        return mContext;
    }

    /**
     * 使用账号密码进行登录操作
     *
     * @param context
     * @param userAccount 用户名
     * @param pwd 密码
     * @param result 子类AbsResult
     * @param needRememberPwd 是否记住登录密码
     * @return 返回是否登录成功的结果
     * @author zqs
     * @createTime 2015年2月14日 下午2:19:11
     */
    public static AbsResult<Boolean> loginOauth(Context context, String userAccount, String pwd, AbsResult<?> result, boolean needRememberPwd) {
        return LoginStateUtil.login(context, userAccount, pwd, result, needRememberPwd);
    }

    /**
     * 退出登录操作
     *
     * @param context
     * @author zqs
     * @createTime 2015年2月14日 上午10:56:26
     */
    public static void loginOut(Context context) {
        LoginStateUtil.saveTokenOnShare(context, false, null);
        OAuthApi.getInstance(mContext).loginOut(mContext); // 清除数据库中的token
        if (mContext != null) {
            instance.userEntity = null;
            instance.tokenEntity = new TokenEntity();
        }
    }

    /**
     * 判断是否已登录
     *
     * @return
     * @author zqs
     * @createTime 2015年2月14日 上午11:44:56
     */
    public static boolean isLogin() {
        return LoginStateUtil.isLogin(mContext) && getToken().accessLogin == 1;
    }

    /**
     * 获取token信息(Share和DB中各存储一份token保障toke不丢失)
     *
     * @return
     * @author zqs
     * @createTime 2015年2月27日 下午4:06:18
     */
    public synchronized static TokenEntity getToken() {
        if (mContext != null && instance.tokenEntity != null) {
            return instance.tokenEntity;
        } else if (mContext != null) {
            TokenEntity tokenEntity = null;
            try {
                tokenEntity = TokenDAO.getInstance(mContext).findTokenById(getUserId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tokenEntity != null) {
                instance.tokenEntity = tokenEntity;
                refreshToken(tokenEntity);
                return tokenEntity;
            }
        }
        return new TokenEntity();
    }

    /**
     * 用户退出登录，需要刷新静态变量token值
     *
     * @author zqs
     * @createTime 2015年2月27日 下午8:15:57
     */
    private static void refreshToken() {
        if (mContext != null) {
            instance.tokenEntity = LoginStateUtil.getTokenOnShare(mContext);
        }
    }

    /**
     * protected仅仅提供OauthApi中调用，外部类不允许任何地方更改token<br>
     * 用户token信息过期时，重新获取token，需要刷新当前静态变量token值
     *
     * @param tokenEntity
     * @author zqs
     * @createTime 2015年2月27日 下午8:16:48
     */
    protected static void refreshToken(TokenEntity tokenEntity) {
        LoginStateUtil.saveTokenOnShare(mContext, true, tokenEntity);
    }

    /**
     * 获取当前登录用户ID，未登录返回0
     *
     * @return
     * @author zqs
     * @createTime 2015年2月28日 上午9:30:01
     */
    public static long getUserId() {
        if (mContext != null) {
            if (instance.tokenEntity != null)
                return instance.tokenEntity.user_id;
        }
        return 0;
    }

    /**
     * 是否记住密码
     *
     * @return
     * @author zqs
     * @createTime 2015年2月14日 下午2:58:12
     */
    public static boolean isRememberPwd() {
        return LoginStateUtil.isRememberPwd(mContext);
    }

    /**
     * 获取记住的登录用户名
     *
     * @return
     * @author zqs
     * @createTime 2015年2月14日 下午2:58:21
     */
    public static String getLoginAccount() {
        return LoginStateUtil.getLoginAccount(mContext);
    }

    // ---------------------------------------------登录token操作 end-------------------------------------------

    /**
     * 获取记住的登录密码
     *
     * @return
     * @author zqs
     * @createTime 2015年2月14日 下午2:58:51
     */
    public static String getLoginPwd() {
        return LoginStateUtil.getLoginPwd(mContext);
    }

    /**
     * 保存登录实体
     *
     * @param userEntity
     * @author zqs
     * @createTime 2015年2月14日 下午2:59:15
     */
    public static void loginUser(Object userEntity) {
        LoginStateUtil.saveUseInfo(mContext, userEntity);
        if (mContext != null) {
            instance.userEntity = null;
        }
        getUserEntity();
    }

    /**
     * 获取登录的实体
     *
     * @return
     * @author zqs
     * @createTime 2015年2月14日 下午2:59:04
     */
    public static Object getUserEntity() {
        if (mContext != null && instance.userEntity == null) {
            if (isLogin()) {
                instance.userEntity = LoginStateUtil.getUserInfo(mContext);
                if (instance.userEntity == null) { // 没获取到用户信息，则退出登录！（原因可能是userEntity实体变更等）
                    loginOut(mContext);
                }
            }
        } else if (mContext == null) {
            return null;
        }
        return instance.userEntity;
    }

    // ----------------------------------------------账号密码保存问题 end-----------------------------------------

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplication();
        instance = this;
        long time = System.currentTimeMillis();
        getUserEntity();
        tokenEntity = LoginStateUtil.getTokenOnShare(mContext);
        MLog.d("-------OauthApplication启动耗时---" + (System.currentTimeMillis() - time) + "-------");
//        try {
//            EventBus.getDefault().register(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

//    /**
//     * http请求到的系统消息Error
//     *
//     * @param loginEvent
//     * @author zqs
//     * @createTime 2016年5月9日 下午2:43:27
//     */
//    public void onEventMainThread(OauthErrorEvent loginEvent) {
//        if (isLogin() || LoginStateUtil.isLogin(mContext)) {
//            loginOut(getApplication());
//        }
//    }

    protected Application getApplication(){
        return this;
    }

    // ---------------------------------------------获取用户信息操作end----------------------------------------------

    public static class LoginStateUtil {

        /**
         * 进行登录操作
         *
         * @param context
         * @param userAccount 用户名
         * @param pwd 密码
         * @param result 子类AbsResult
         * @param needRememberPwd 是否记住登录密码
         * @return 返回是否登录成功的结果
         * @author zqs
         * @createTime 2015年2月14日 下午2:19:11
         */
        private static AbsResult<Boolean> login(Context context, String userAccount, String pwd, AbsResult<?> result, boolean needRememberPwd) {
            // loginOut(context);
            AbsResult<TokenEntity> loginResult = OAuthApi.getInstance(context).Login(userAccount, pwd, result.getClientId(), result.getClientKey());
            FrameResult<Boolean> returnResult = new FrameResult<Boolean>();
            if (loginResult.getR()) { // 登录成功
                returnResult.setT(true);
                saveTokenOnShare(context, true, loginResult.getT());
                saveUserPwd(context, userAccount, pwd, needRememberPwd);
            } else {
                returnResult.setT(false);
                saveUserPwd(context, userAccount, "", needRememberPwd); // 清除密码保存
            }
            returnResult.setCode(loginResult.getCode());
            returnResult.setData(loginResult.getData());
            returnResult.setMsg(loginResult.getMsg());
            returnResult.setR(loginResult.getR());
            return returnResult;
        }

        public static long getTokenUserIdWhenRealNameLogin(Context context,String token){
            return OAuthApi.getInstance(context).getTokenUserIdWhenRealNameLogin(token);
        }

        /**
         * 1，数据库中保存token信息 2，share中保存一份token信息
         *
         * @param tokenEntity
         * @author zqs
         * @createTime 2015年2月14日 上午10:58:50
         */
        public static void saveTokenOnShare(Context context, boolean isLoginSucc, TokenEntity tokenEntity) {
            if (isLoginSucc) {
                CommonApkShare.saveObj(context, OAUTH_LOGIN_TOKEN_INFO, tokenEntity);
            } else {
                CommonApkShare.putBoolean(context, CommonApkShare.KEY_IS_LOGINED, false);
                CommonApkShare.saveObj(context, OAUTH_LOGIN_TOKEN_INFO, "");
            }
            refreshToken();
        }

        private static TokenEntity getTokenOnShare(Context context) {
            TokenEntity tokenEntity = null;
            Object object = CommonApkShare.getObj(context, OAUTH_LOGIN_TOKEN_INFO);
            if (!"".equals(object) && object != null) {
                tokenEntity = (TokenEntity) object;
            }
            if (tokenEntity == null)
                tokenEntity = new TokenEntity();
            return tokenEntity;
        }

        /**
         * 判断是否已登录
         *
         * @param context
         * @return
         * @author zqs
         * @createTime 2015年2月14日 上午11:44:56
         */
        private static boolean isLogin(Context context) {
            return CommonApkShare.getBoolean(context, CommonApkShare.KEY_IS_LOGINED, false);
        }

        /**
         * 是否记住密码
         *
         * @param context
         * @return
         * @author zqs
         * @createTime 2015年2月14日 下午2:50:36
         */
        private static boolean isRememberPwd(Context context) {
            return CommonApkShare.getBoolean(context, CommonApkShare.KEY_ISREMEMBER_LOGINPASSWORD, false);
        }

        /**
         * 获取记住的用户名
         *
         * @param context
         * @return
         * @author zqs
         * @createTime 2015年2月14日 下午2:50:45
         */
        private static String getLoginAccount(Context context) {
            return CommonApkShare.getString(context, CommonApkShare.KEY_COMMON_LOGINNAME);
        }

        /**
         * 获取记住的密码
         *
         * @param context
         * @return
         * @author zqs
         * @createTime 2015年2月14日 下午2:50:56
         */
        private static String getLoginPwd(Context context) {
            Object object = CommonApkShare.getObj(context, CommonApkShare.KEY_COMMON_LOGINPASSWORD);
            if (object != null)
                return object.toString();
            return "";
        }

        /**
         * 保存登录用户实体信息
         *
         * @param context
         * @param userEntity
         * @author zqs
         * @createTime 2015年2月27日 下午2:35:28
         */
        private static void saveUseInfo(Context context, Object userEntity) {
            if (userEntity != null) {
                // sdk判断用户登录 必须是外层已经获取用户信息后才算真正登录
                CommonApkShare.putBoolean(context, CommonApkShare.KEY_IS_LOGINED, true);
            }
            CommonApkShare.saveObj(context, CommonApkShare.KEY_LOGIN_INFO, userEntity);
        }

        /**
         * 获取保存的用户实体
         *
         * @param context
         * @return
         * @author zqs
         * @createTime 2015年2月14日 下午2:52:44
         */
        private static Object getUserInfo(Context context) {
            Object object = CommonApkShare.getObj(context, CommonApkShare.KEY_LOGIN_INFO);
            if ("".equals(object))
                return null;
            return object;
        }

        /**
         * 保存用户名密码
         *
         * @param context
         * @param userAccount
         * @param needRememberPwd
         * @param pwd
         * @author zqs
         * @createTime 2015年2月14日 下午2:52:59
         */
        private static void saveUserPwd(Context context, String userAccount, String pwd, boolean needRememberPwd) {
            CommonApkShare.putString(context, CommonApkShare.KEY_COMMON_LOGINNAME, userAccount); // 无论是否记住密码，都缓存用户名
            if (needRememberPwd) {
                CommonApkShare.putBoolean(context, CommonApkShare.KEY_ISREMEMBER_LOGINPASSWORD, true);
                CommonApkShare.saveObj(context, CommonApkShare.KEY_COMMON_LOGINPASSWORD, pwd); // 密码加密缓存
            } else {
                CommonApkShare.putBoolean(context, CommonApkShare.KEY_ISREMEMBER_LOGINPASSWORD, false);
                CommonApkShare.saveObj(context, CommonApkShare.KEY_COMMON_LOGINPASSWORD, ""); // 密码加密缓存
            }
        }
    }

}

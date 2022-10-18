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
    private static Application mContext;
    public static OauthApplicationLike instance;
    private TokenEntity tokenEntity; // 用户登录token信息

    public static Context getOauthApplicationContext() {
        return mContext;
    }
    public static Application getInstance() {
        return mContext;
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
//            instance.tokenEntity = LoginStateUtil.getTokenOnShare(mContext);
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
//        LoginStateUtil.saveTokenOnShare(mContext, true, tokenEntity);
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


    // ----------------------------------------------账号密码保存问题 end-----------------------------------------

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplication();
        instance = this;
    }

    protected Application getApplication(){
        return this;
    }
}

package zhl.common.oauth;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.lidroid.xutils.exception.DbException;

import org.json.JSONObject;

import java.util.HashMap;

import de.greenrobot.event.EventBus;
import zhl.common.request.AbsResult;
import zhl.common.request.UserAgentUtils;
import zhl.common.utils.DebugUrl;
import zhl.common.utils.JsonHp;
import zhl.common.utils.MD5Tool;
import zhl.common.utils.MLog;
import zhl.common.utils.SyncHttpHp;
import zhl.common.utils.Tools;

/**
 * 服务端oauth2验证机制
 *
 * @author ylf
 * @createTime 2014年6月14日 下午8:28:57
 */
public class OAuthApi {

    private static OAuthApi instance;
    private Context mContext;
    private String userTag = "User-I:9999_999";

    private OAuthApi(Context context) {
        mContext = context;
    }

    public static synchronized OAuthApi getInstance(Context context) {
        if (instance == null) {
            instance = new OAuthApi(context);
        }
        return instance;
    }

    /**
     * 登录接口
     *
     * @param userAccount
     * @param pwd
     * @throws Exception
     */
    public FrameResult<TokenEntity> Login(String userAccount, String pwd, String clientId, String clientSecret) {
        //异常情况下返回值处理
        FrameResult<TokenEntity> exResult = new FrameResult<>();
        try {
            FrameResult<TokenEntity> result = getToken(userAccount, MD5Tool.md5Digest(pwd).toUpperCase(), clientId, clientSecret, mContext.getPackageName());
            if (result.getR()) {
                TokenEntity tk = result.getT();
                // 保存到数据库
                tk.account = userAccount;
                tk.accessLogin = 1;// 登录
                long tokenUserId = getTokenUserId(tk.access_token);
                if (tokenUserId == -1) {
                    exResult.setR(false).setMsg("登录失败，请稍后重试");
                    return exResult;
                }
                tk.user_id = tokenUserId;
                TokenDAO.getInstance(mContext).saveOrUpdateToken(tk);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            exResult.setR(false).setMsg("登录失败，请稍后重试");
        }
        return exResult;
    }
    public long getTokenUserIdWhenRealNameLogin(String token){
        return getTokenUserId(token);
    }


    /**
     * 退出登录
     *
     * @param mContext
     * @author ylf
     * @createTime 2014年8月10日 下午1:44:11
     */
    public void loginOut(Context mContext) {
//        try {
        TokenDAO.getInstance(mContext).deleteTokenById(OauthApplicationLike.getUserId());
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
    }


    /**
     * 获取token
     *
     * @param userId
     * @param result
     * @param userId >0表示已登录的token,<=0获取未登录token
     * @return
     * @throws DbException
     * @author ylf
     * @createTime 2014年8月10日 下午2:30:28
     */
    public TokenEntity getValidToken(long userId, AbsResult result) throws DbException, AuthFailureError {
        TokenEntity pckToken = OauthApplicationLike.getToken();
        if (pckToken != null && !Tools.isEmpty(pckToken.access_token)) {
            long timespan = (System.currentTimeMillis() / 1000 - pckToken.currentTimestamp);// 计算分差
            // 已过期或者时间超前，提前半小时超时
            if ((timespan + 3600) >= pckToken.expires_in || timespan < 0) {
                if (pckToken.accessLogin == 1 && !Tools.isEmpty(pckToken.access_token)) {
                    if (Tools.isNetToken(pckToken.access_token)) {
                        return pckToken;
                    }
                    // 已经存在带用户信息的token,且token过期
                    FrameResult<TokenEntity> tResult = freshToken(pckToken.refresh_token, result.getClientId(), result.getClientKey(), mContext.getPackageName());
                    if (tResult.getR()) {
                        TokenEntity tk = tResult.getT();
                        if (!TextUtils.isEmpty(tk.access_token)) {
                            tk.accessLogin = 1;
                        } else {
                            tk.accessLogin = 0;
                        }
                        tk.account = pckToken.account;
                        tk.user_id = userId;
                        TokenDAO.getInstance(mContext).saveOrUpdateToken(tk);
                        OauthApplicationLike.refreshToken(tk); // 刷新当前内存中静态变量的token值
                        return tk;
                    } else {
                        throw new AuthFailureError("登录已过期,请重新登录");
                    }
                }
            }
            // 未过期
            else {
                return pckToken;
            }
        }
        return null;
    }


    /**
     * 新版获取token的方法
     *
     * @param username 用户名
     * @param pwd 密码
     * @param clientId clientId
     * @param client_secret clientSecret
     * @param scope 包名
     * @return 返回值
     */
    private FrameResult<TokenEntity> getToken(String username, String pwd, String clientId, String client_secret, String scope) {
        HashMap<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("username", username);
        parameterMap.put("password", pwd);
        parameterMap.put("grant_type", "password");
        parameterMap.put("client_id", clientId);
        parameterMap.put("client_secret", client_secret);
        parameterMap.put("scope", scope);
        return getTokenBase(DebugUrl.getTokenUrl(), parameterMap);
    }

    private FrameResult<TokenEntity> freshToken(String refresh_token, String clientId, String clientSecret, String scope) {
        HashMap<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("refresh_token", refresh_token);
        parameterMap.put("grant_type", "refresh_token");
        parameterMap.put("client_id", clientId);
        parameterMap.put("client_secret", clientSecret);
        parameterMap.put("scope", scope);
        return getTokenBase(DebugUrl.getTokenUrl(), parameterMap);
    }

    /**
     * 获取oauth2 token
     *
     * @param
     * @param
     */
    private FrameResult<TokenEntity> getTokenBase(String url, HashMap<String, Object> parameterMap) {
        HashMap<String, String> header = new HashMap<String, String>();

        FrameResult<String> r = new SyncHttpHp(mContext).http(url, parameterMap, SyncHttpHp.METHOD_POST, header);
        FrameResult<TokenEntity> rrs = new FrameResult<TokenEntity>();
        rrs.setCode(r.getCode()).setMsg(r.getMsg()).setR(r.getR());
        try {
            JSONObject jsonObject = new JSONObject(r.getT());
            int code = r.getCode();
            if (jsonObject.has("code")) {
                code = jsonObject.getInt("code");
                rrs.setCode(code);
                rrs.setR(code == 0);
            }
            if (jsonObject.has("msg")) {
                String msg = jsonObject.getString("msg");
                rrs.setMsg(msg);
            }
            if (code == 0) {
                TokenEntity accessTokenEntity = new TokenEntity();
                String data = jsonObject.getString("data");
                accessTokenEntity = JsonHp.getGsonConverter().fromJson(data, TokenEntity.class);
                accessTokenEntity.currentTimestamp = System.currentTimeMillis() / 1000;
                rrs.setT(accessTokenEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rrs.setR(false);
            if (rrs.getCode() == 401 || rrs.getCode() == 400) {
                rrs.setMsg("用户名或密码错误");
            } else {
                rrs.setMsg("网络异常");
            }
        } finally {
            int code = rrs.getCode();
            if (OauthErrorEvent.findErrorEventByCode(code) != null) {
                EventBus.getDefault().post(OauthErrorEvent.findErrorEventByCode(code));
            }
            return rrs;
        }
    }

    /**
     * 根据token获取userId
     *
     * @return
     */
    private long getTokenUserId(String token) {
        try {
            HashMap<String, String> header = new HashMap<>();
            header.put("Authorization", token);
            header.put("User-Agent", UserAgentUtils.get(mContext));
            FrameResult<String> result = new SyncHttpHp(mContext).http(DebugUrl.getTokenById(), "", SyncHttpHp.METHOD_POST, header);

            if (result.getR()) {
                JSONObject jsonObject = new JSONObject(result.getT());
                if (jsonObject.has("data")) {
                    String data = jsonObject.getString("data");
                    return Long.valueOf(data);
                }
            }
            return -1;
        } catch (Exception e) {
            Toast.makeText(mContext, "登录异常，稍候再试", Toast.LENGTH_SHORT).show();
            MLog.d("获取用户id异常");
            return -1;
        }
    }
}

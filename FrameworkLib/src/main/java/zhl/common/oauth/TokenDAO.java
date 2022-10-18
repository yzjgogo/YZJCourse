package zhl.common.oauth;

import android.content.Context;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;

/**
 * token管理类
 *
 * @author ylf
 * @createTime 2014年6月14日 下午3:17:36
 */
public class TokenDAO extends OauthDbImpl<TokenEntity> {

    private static TokenDAO instance;

    private TokenDAO(Context mContext) {
        super(mContext.getApplicationContext(), TokenEntity.class);
    }

    public static synchronized TokenDAO getInstance(Context mContext) {
        if (instance == null)
            instance = new TokenDAO(mContext);
        return instance;
    }

    public void saveToken(TokenEntity tk) throws DbException {
        deleteAll();
        save(tk);
    }

    /**
     * 查找当前登陆用户token
     */
    public TokenEntity findTokenById(long user_id) {
        try {
            TokenEntity token = findFirst(Selector.from(classT).where("user_id", "=", user_id));
            return token;
        } catch (DbException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加或者刷新token
     *
     * @param tk
     */
    public void saveOrUpdateToken(TokenEntity tk) {
        try {
            super.saveOrUpdate(tk);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除某个登陆用户
     */
    public void deleteTokenById(long user_id) {
        try {
            super.delete(WhereBuilder.b("user_id", "=", user_id));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}

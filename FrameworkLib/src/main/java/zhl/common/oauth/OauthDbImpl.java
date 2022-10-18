/**
 * v 1.0.0
 * ylf create at 2014年6月14日 下午3:11:00
 */
package zhl.common.oauth;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.DAOImpl;

/**
 * oauth数据访问db
 *
 * @author ylf
 * @createTime 2014-6-14 下午3:58:22
 */
public class OauthDbImpl<T extends Object> extends DAOImpl<T> {

    public OauthDbImpl(Context mContext, Class<T> classT) {
        // 不存在则自动创建
        super(mContext, classT, getDbName(mContext));
    }

    private static String getDbName(Context mContext) {
        // if (OauthWsConfig.IsTest) {
        // return Tools.getSDPath() + "/zhl/" + mContext.getPackageName() + "_test.db";
        // }
        // if (OauthWsConfig.IsDebug) {
        // return Tools.getSDPath() + "/zhl/" + mContext.getPackageName() + "_debug.db";
        // }
        return mContext.getPackageName() + "_oauth.db";
    }

    @Override
    public void onUpdate(DbUtils db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                try {
                    try {
                        // 新增token user_id机制
                        db.execNonQuery("alter table zhl_common_oauth_tokenentity add column user_id INTEGER");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getVersion() {
        return 2;
    }

}

package zhl.common.oauth;

import com.lidroid.xutils.db.annotation.Id;

import java.io.Serializable;

public class TokenEntity implements Serializable {
    // 使用了加密存储、串码字段值不允许更改
    private static final long serialVersionUID = 183062929542821651L;

    @Id public int id;
    public String access_token;
    public String token_type;
    public long expires_in;
    public String refresh_token;
    public long currentTimestamp;
    public int accessLogin;// 是否登录模式，1登录，0未登录
    public int isdev;// 内外网，1外网，0内网
    public String account;
    public long user_id; // 用户id

    @Override
    public String toString() {
        return "TokenEntity{" +
                "id=" + id +
                ", access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", currentTimestamp=" + currentTimestamp +
                ", accessLogin=" + accessLogin +
                ", isdev=" + isdev +
                ", account='" + account + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}

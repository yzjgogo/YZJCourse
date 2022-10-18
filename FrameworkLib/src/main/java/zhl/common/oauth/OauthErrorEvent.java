package zhl.common.oauth;

/**
 * http 400 错误通知
 *
 * @author zqs
 * @createTime 2016年5月9日 上午11:16:07
 */
public enum OauthErrorEvent {

    Oauth_Not_Allow(100000, "登录信息已过期，请重新登录"), // 非法的包名、token过期或者token内容或者格式错误

    Data_Not_Validate(100004, "数据校验不合法"), // 提交的数据 validata不合法

    TOKEN_NOT_FOUND(400001, "您未登录"),
    TOKEN_EXPIRED(400002, "您的登录信息已过期，请重新登录"),
    TOKEN_FAIL(400003, "您未登录"),
    TOKEN_ABANDON(400004, "您已在其他地方登录,请重新登录"),
    TOKEN_UNKNOW(400005, "您未登录"),
    SCOPE_FAIL(400006, "您未登录"),
    TOKEN_CHANGE_PWD(400007, "您的登录信息已过期，请重新登录");

    private int code;
    private String msg;

    OauthErrorEvent(int coode, String msg) {
        this.code = coode;
        this.msg = msg;
    }

    public static OauthErrorEvent findErrorEventByCode(int code) {
        for (OauthErrorEvent event : OauthErrorEvent.values()) {
            if (event.code == code) {
                return event;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

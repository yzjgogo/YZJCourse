package zhl.common.request;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import zhl.common.utils.JsonHp;

public abstract class AbsResult<T> implements Serializable {
    private static final long serialVersionUID = 978493611637353210L;
    private static final int SUCCESS_CODE = 0;
    /** 是否正常 */
    private Boolean r = false;
    /** 异常信息 */
    private String msg = "";
    /** 解析后的数据 */
    private T t;
    /** 服务端状态码 */
    private int code = -1;
    /** 服务端返回数据流 */
    private String data = "";
    /** 单个类 */
    private Class<T> clsT = null;
    /** 含有泛型变量 */
    private TypeToken<T> token = null;
    private String clientKey;
    private String clientId;
    private String secret;

    public AbsResult() {
        this.clientKey = getRequestClientKey();
        this.clientId = getRequestClientId();
        this.secret = getRequestSecret();
    }

    public AbsResult(Class<T> clsT) {
        this();
        this.clsT = clsT;
    }

    public AbsResult(TypeToken<T> token) {
        this();
        this.token = token;
    }

    /**
     * 获取系统json转换器
     *
     * @return
     * @author zqs
     * @createTime 2016年6月30日 下午11:00:56
     * @see JsonHp#getGsonConverter()
     * @deprecated Use {@link JsonHp#getGsonConverter()} instead
     */
    public static Gson getGsonConverter() {
        return JsonHp.getGsonConverter();
    }

    public abstract String getRequestClientKey();

    public abstract String getRequestClientId();

    public abstract String getRequestSecret();

    public abstract int getRequestBusinessId();

    public abstract int getEditionId();

    public T getT() {
        return this.t;
    }

    public AbsResult<T> setT(T t) {
        this.t = t;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public AbsResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean getR() {
        return this.r;
    }

    public AbsResult<T> setR(Boolean r) {
        this.r = r;
        return this;
    }

    public int getCode() {
        return this.code;
    }

    public AbsResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getData() {
        return data;
    }

    public AbsResult<T> setData(String data) {
        this.data = data;
        return this;
    }

    public String getClientKey() {
        return clientKey;
    }

    public String getClientId() {
        return clientId;
    }

    public String getSecret() {
        return secret;
    }

    /**
     * { "Code":"myop", "Data":{ "count": #String }, "Msg":0 #Integer }
     *
     * @throws JSONException
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("unchecked")
    public void setRawContent(String response) throws JSONException {
        if (response == null || response.equals("")) {
            return;
        }
        this.setData(response);
        JSONObject jsonObject = new JSONObject(response);
        this.setCode(jsonObject.getInt("code"));
        if (this.getCode() == SUCCESS_CODE)
            setR(true);
        setMsg(jsonObject.getString("msg"));

        if (jsonObject.has("data")) {
            String data = jsonObject.getString("data");
            if (clsT != null) {
                if (clsT == String.class) {
                    setT((T) data);
                } else {
                    setT(JsonHp.getGsonConverter().fromJson(data, this.clsT));
                }
            } else if (token != null) {
                if (this.token.getType() == String.class) {
                    setT((T) data);
                } else {
                    setT((T) JsonHp.getGsonConverter().fromJson(data, this.token.getType()));
                }
            }
        }
    }

    // TODO: 2017/6/5 正式环境 TYPE_ONLINE 测试环境修改成DebugUrl.TYPE_DEBUG. test模式 修改成 TYPE_TEST
    //    public static String urlPrefix = DebugUrl.TYPE_ONLINE;

    public ZHLRequest post(Map<String, Object> param, String url) {
        if (!param.containsKey("business_id")) {
            param.put("business_id", getRequestBusinessId());
        }
        if (!param.containsKey("edition_id")) {
            if (getEditionId() != 0) {
                param.put("edition_id", getEditionId());
            }
        }
        return new ZHLRequest(url, param, this);
    }

    public ZHLRequest get(Map<String, Object> param, String url) {
        if (!param.containsKey("business_id")) {
            param.put("business_id", getRequestBusinessId());

        }
        if (!param.containsKey("subject_id")) {
            param.put("subject_id", getEditionId());
        }
        return new ZHLRequest(Request.Method.GET, url, param, this);
    }

}

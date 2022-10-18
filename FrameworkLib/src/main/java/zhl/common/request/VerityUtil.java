package zhl.common.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lidroid.xutils.exception.HttpException;
//import com.zhl.security.sdk.SecurityTool;

import org.json.JSONException;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.greenrobot.event.EventBus;
import zhl.common.oauth.OauthErrorEvent;
import zhl.common.utils.JsonHp;
import zhl.common.utils.MD5Tool;
import zhl.common.utils.MLog;
import zhl.common.utils.Tools;
import zhl.common.utils.ZHLSecurityUtil;

/**
 * Created by zhaojh
 * Date: 2016/9/6
 * Time: 11:41
 */
public class VerityUtil {
    public static final String VALIDATE_KEY = "validate";
    public static final String SEC_VALIDATE_KEY = "validate_sec";

    /**
     * 对参数升序排序，并进行加密验证
     *
     * @param map
     * @throws HttpException
     * @throws Exception
     * @author ylf
     * @createTime 2014年7月12日 下午3:54:35
     */
    public static String sortMap(Map<String, Object> map, long userId, String secret) throws HttpException {
        Exception exception = null;
        try {
            TreeMap<String, Object> treeMap = new TreeMap<>(map);
            String noSecStr = ZHLSecurityUtil.sign(MD5Tool.md5Digest(getParamStr(treeMap, userId, secret)).toLowerCase());
            treeMap.put(SEC_VALIDATE_KEY, noSecStr);
            map.put(SEC_VALIDATE_KEY, noSecStr);
            map.put(VALIDATE_KEY, MD5Tool.md5Digest(getParamStr(treeMap, userId, secret)).toLowerCase());
            return JsonHp.Serialization(map);
        } catch (JSONException e) {
            e.printStackTrace();
            exception = e;
        } catch (Exception e) {
            e.printStackTrace();
            exception = e;
        }
        if (exception != null) {
            throw new HttpException(exception);
        }
        return "";
    }


    private static String getParamStr(TreeMap<String, Object> treeMap, long userId, String secret) throws JSONException {
        StringBuffer paramStr = new StringBuffer();
        Set<String> keySet = treeMap.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if (treeMap.get(key) instanceof String) {
                paramStr.append(key).append(treeMap.get(key));
            } else {
                String temp = JsonHp.Serialization(treeMap.get(key));
                if (temp != null) {
                    // replaceAll对应String中的[一个\]需要[四个\]来表示
                    temp = temp.replaceAll("\\\\\"", "\"") // 把【\"】替换成【"】
                            .replaceAll("\\\\\\\\\"", "\\\\\""); // 把【\\"】替换成 【\"】
                }
                paramStr.append(key).append(temp);
            }
        }
        String userIdStr = "";
        if (userId != 0) {
            userIdStr = String.valueOf(userId);
        }
        paramStr.insert(0, userIdStr).append(secret);
        return paramStr.toString();
    }


    /**
     * 判断oauth2是否可用，否则删除
     *
     * @param responStr
     * @author ylf
     * @createTime 2014-4-23 下午4:14:01
     */
    public static int checkTokenValid(String responStr) {
        try {
            if (!Tools.isEmpty(responStr) && responStr.startsWith("{")) {
                JsonParser parser = new JsonParser();
                JsonElement jElement = parser.parse(responStr);
                if (jElement instanceof JsonObject) {
                    JsonObject jObject = jElement.getAsJsonObject();
                    jElement = jObject.get("code");
                    int code = jElement.getAsInt();
                    if (OauthErrorEvent.findErrorEventByCode(code) != null) {
                        MLog.e("---------!!!oauth system error !!!-----" + responStr);
                        // TokenDAO.getInstance(context).deleteAll();
                        EventBus.getDefault().post(OauthErrorEvent.findErrorEventByCode(code));
                        return code;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 判断返回的数据是否合法
     *
     * @param responseStr
     * @param userId
     * @param secret
     * @return
     * @throws Exception
     * @author ylf
     * @createTime 2014年7月12日 下午5:57:35
     */
    public static Boolean validateResponse(String responseStr, long userId, String secret) throws Exception {
        //		System.out.println("原始数据\n" + responseStr);
        // JSONObject会排序混乱，使用Gson提供的进行排序
        //		JsonObject jObject = new JsonObject();
        //		jObject = JsonHp.Deserializate(responseStr, JsonObject.class);
        //
        //		StringBuffer finalBuffer = new StringBuffer("");
        //
        //		String userIdStr = "";
        //		if (userId != 0) {
        //			userIdStr = String.valueOf(userId);
        //		}
        //		String codeStr = jObject.get("code").toString();
        //		StringBuffer msgBufferStr = new StringBuffer(jObject.get("msg").toString());
        //		String msgStr = msgBufferStr.deleteCharAt(0).deleteCharAt(msgBufferStr.length() - 1).toString(); // 去除msg结果中的首尾""
        //		Object dataStr = jObject.get("data");
        //
        //		if (dataStr == null) { // json数据中没有data
        //			finalBuffer.append(userIdStr).append("code").append(codeStr).append("msg").append(msgStr).append(secret);
        //		} else if (dataStr == JsonNull.INSTANCE) { // data为null
        //			finalBuffer.append(userIdStr).append("code").append(codeStr).append("datanullmsg").append(msgStr).append(secret);
        //		} else { // data为正常数据
        //			String signData = "{\"data\":";
        //			finalBuffer.append(responseStr.substring(responseStr.indexOf(signData) + signData.length()));
        //
        //			String codeSign = ",\"code\"";
        //			finalBuffer.delete(finalBuffer.lastIndexOf(codeSign), finalBuffer.length());
        //
        //			String s = finalBuffer.toString().substring(0, 1); // 字符串 第一个是“去掉
        //			if (s.equals("\"")) {
        //				finalBuffer.deleteCharAt(0);
        //				finalBuffer.deleteCharAt(finalBuffer.length() - 1);
        //			}
        //			finalBuffer.insert(0, (userIdStr + "code" + codeStr + "data")).append("msg").append(msgStr).append(secret);
        //		}
        //		// replaceAll对应String中的[一个\]需要[四个\]来表示
        //		String finalString = finalBuffer.toString().replaceAll("\\\\\"", "\"") // 把【\"】替换成【"】
        //				.replaceAll("\\\\\\\\\"", "\\\\\""); // 把【\\"】替换成 【\"】
        ////		System.out.println("finalString："+finalString);
        //
        //		// System.out.println("组合后数据\n" + finalString.toString());
        //		String myValidate = "\"" + MD5Tool.md5Digest(finalString) + "\""; // 把\"替换成"
        //		// System.out.println("我的加密:\n" + myValidate);
        //		String validate = (String) jObject.get(VALIDATE_KEY).toString();
        ////		System.out.println("加密字符串："+myValidate);
        //		if (myValidate.equalsIgnoreCase(validate) || !"0".equals(codeStr)) {
        //			return true;
        //		} else {
        //			return false;
        //		}
        return true;
    }
}

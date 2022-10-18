package zhl.common.request;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 用于构造request
 *
 * @author zqs
 * @createTime 2015年1月16日 下午4:48:36
 */
public final class BaseRequest {

    // requestType---Op映射关系
    private static HashMap<Integer, Class<BaseApi>> requestMapOp = new HashMap<>();
    // requestConfig
    private static Set<Class<? extends BasePocConfig>> requestConfigClass = new HashSet<>();

    /**
     * 初始化注解
     *
     * @param clazz
     * @throws InvocationTargetException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @author zqs
     * @createTime 2015年1月16日 下午5:32:24
     */
    private static void initRequestType(Class<? extends BasePocConfig> clazz) throws InvocationTargetException, SecurityException, NoSuchMethodException, InstantiationException {
        for (Field field : clazz.getDeclaredFields()) {
            ApiClass opClass = field.getAnnotation(ApiClass.class);
            if (opClass != null) {
                if (field.getModifiers() != (Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL) || field.getType() != int.class) {
                    Log.e("error", "===@ApiClass注解只能配置于 public static final int 修饰的常量中===");
                    throw new IllegalArgumentException("==@ApiClass注解只能配置于 public static final int 修饰的常量中==");
                }
                Class<BaseApi> op = (Class<BaseApi>) opClass.ApiClassName();
                try {
                    int requestType = (Integer) field.get(clazz);
                    // 不允许重复配置同一个requestType
                    if (requestMapOp.containsKey(requestType)) {
                        String errorMsg = "@ApiClass 配置的public static final int 数值【" + requestType + "】存在冲突，请更换";
                        Log.e("error", errorMsg);
                        throw new IllegalArgumentException(errorMsg);
                    }
                    requestMapOp.put((Integer) field.get(clazz), op);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ZHLRequest getRequest(int requestType, Object... params) {
        // 获取api
        Class<BaseApi> operationClass = requestMapOp.get(requestType);
        BaseApi operation = null;
        if (operationClass != null) {
            try {
                operation = operationClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (operation != null) {
            ZHLRequest request = operation.getRequest(params);
            request.setRequestType(requestType);
            return request;
        } else {
            return null;
        }
    }

    /**
     * 用于获取requestType---Api的配置类
     *
     * @return
     * @author zqs
     * @createTime 2015年1月16日 下午5:42:32
     */
    // public abstract Class<?> getRequestConfigClass();

    /**
     * 注册请求配置
     *
     * @param requestTypeClass
     * @author zqs
     * @createTime 2015年3月3日 上午9:42:23
     */
    protected static void registerRequestConfigClass(Class<? extends BasePocConfig> requestTypeClass) {
        if (!requestConfigClass.contains(requestTypeClass)) {
            requestConfigClass.add(requestTypeClass);
            try {
                initRequestType(requestTypeClass);
            } catch (InvocationTargetException | SecurityException | NoSuchMethodException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}

package zhl.common.request;

/**
 * 用于构造api请求
 *
 * @author zqs
 * @createTime 2015年1月16日 下午4:46:09
 */
public abstract class BaseApi {
    public abstract ZHLRequest getRequest(Object... params);
}

/**
 * @author ylf
 * @createTime 2014年7月12日 下午4:27:34
 */
package zhl.common.oauth;


import zhl.common.request.AbsResult;

/**
 * @author ylf
 * @createTime 2014年7月12日 下午4:27:34
 */
public class FrameResult<T> extends AbsResult<T> {

    @Override
    public String getRequestClientKey() {
        return null;
    }

    @Override
    public String getRequestClientId() {
        return null;
    }

    @Override
    public String getRequestSecret() {
        return null;
    }

    @Override
    public int getRequestBusinessId() {
        return 0;
    }

    @Override
    public int getEditionId() {
        return 0;
    }
}

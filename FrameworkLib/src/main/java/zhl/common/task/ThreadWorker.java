/**
 * @author ylf
 * @createTime 2014-6-11 上午11:59:22
 */
package zhl.common.task;

import android.os.HandlerThread;

/**
 * @author ylf
 * @createTime 2014-4-10 下午2:18:49
 */

public class ThreadWorker extends HandlerThread {

    /**
     * @param name
     */
    public ThreadWorker(String name) {
        super(name);
    }


}

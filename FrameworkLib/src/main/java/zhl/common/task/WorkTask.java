/**
 * @author ylf
 * @createTime 2014-6-11 上午11:41:07
 */
package zhl.common.task;


/**
 * @author ylf
 * @createTime 2014-4-10 下午2:18:49
 */

public abstract class WorkTask extends Task {

    /**
     * @param status
     */
    public WorkTask() {
        super(RunningStatus.WORK_THREAD);
    }

}

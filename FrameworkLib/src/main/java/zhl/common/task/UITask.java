/**
 * @author ylf
 * @createTime 2014-6-11 上午11:41:07
 */
package zhl.common.task;

/**
 * @author ylf
 * @createTime 2014-4-10 下午2:18:49
 */

public class UITask extends Task {

    /**
     * @param status
     */
    public UITask() {
        super(RunningStatus.UI_THREAD);
    }

    @Override
    public TaskOperation onExecute(TaskOperation operation) {
        onExecute();
        return null;
    }

    public void onExecute() {
    }

}

/**
 * @author ylf
 * @createTime 2014-6-11 上午11:41:07
 */
package zhl.common.task;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ylf
 * @createTime 2014-4-10 下午2:18:49
 */

public abstract class Task {

    /**
     * The id of the task, typically you need NOT set it, if will set
     * automatically when you add this task into {@link TaskManager} class.
     */
    private int mId = 0;

    /**
     * The task name.
     */
    private String mName = null;

    /**
     * Indicate this task is canceled or not.
     */
    private AtomicBoolean mCancelled = new AtomicBoolean(false);

    /**
     * The task status, default value is {@link Status#PENDING}.
     */
    private volatile Status mStatus = Status.PENDING;

    /**
     * The running status, default value is {@link RunningStatus#UI_THREAD}.
     */
    private volatile RunningStatus mRunStatus = RunningStatus.UI_THREAD;

    /**
     * The constructor method.
     *
     * @param runInBackground
     * @param name
     */
    public Task(Task task) {
        this.mRunStatus = task.mRunStatus;
        this.mName = task.mName;
        this.mStatus = task.mStatus;
    }

    /**
     * The constructor method.
     *
     * @param status indicate the task is running in background thread or not.
     */
    public Task(RunningStatus status) {
        this(status, null);
    }

    /**
     * The constructor method.
     *
     * @param runInBackground
     * @param name
     */
    public Task(RunningStatus status, String name) {
        mRunStatus = status;
        mName = name;
    }

    /**
     * Override this method to do you works.
     *
     * @param operation The operation is passed from previous task.
     * @return Typically you should return the {@link #operation}.
     */
    public abstract TaskOperation onExecute(TaskOperation operation);

    /**
     * Called when change the progress, this method is running in UI thread.
     *
     * @param progresses
     */
    public void onProgressUpdate(Object progresses) {
    }

    /**
     * Cancel the task.
     */
    public void cancel() {
        mCancelled.set(true);
    }

    /**
     * Indicate the task is canceled or not.
     *
     * @return
     */
    public boolean isCancelled() {
        return mCancelled.get();
    }

    /**
     * Get the running status.
     *
     * @return
     */
    public RunningStatus getRunningStatus() {
        return mRunStatus;
    }

    /**
     * Get the task name.
     *
     * @return the task name.
     */
    public String getTaskName() {
        return mName;
    }

    /**
     * Set the name of the task.
     *
     * @param name The task name.
     */
    public void setTaskName(String name) {
        mName = name;
    }

    /**
     * Get the status of the task.
     *
     * @return
     */
    public Status getStatus() {
        return mStatus;
    }

    /**
     * Set the status of the task.
     *
     * @param status
     */
    public void setStatus(Status status) {
        mStatus = status;
    }

    /**
     * Get the task id.
     */
    public int getTaskId() {
        return mId;
    }

    /**
     * Set the id of the task.
     *
     * @param id
     */
    public void setTaskId(int id) {
        mId = id;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name = ").append(mName).append("  ");
        sb.append("id = ").append(mId).append("  ");
        sb.append(super.toString());

        return sb.toString();
    }

    /**
     * Indicates the current status of the task. Each status will be set only
     * once during the lifetime of a task.
     */
    public enum Status {
        /**
         * Indicates that the task has not been executed yet.
         */
        PENDING,

        /**
         * Indicates that the task is running.
         */
        RUNNING,

        /**
         * Indicates that {@link Task#onExecute} has finished.
         */
        FINISHED,
    }

    /**
     * Indicate the task running status.
     */
    public enum RunningStatus {
        /**
         * Indicate the task is running in the background thread.
         */
        WORK_THREAD,

        /**
         * Indicate the task is running in the UI thread.
         */
        UI_THREAD,
    }
}

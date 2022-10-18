package zhl.common.base;

import  androidx.fragment.app.FragmentActivity;
import android.util.Log;

import com.lidroid.xutils.BuildConfig;
import com.lidroid.xutils.util.LogUtils;

import java.util.Iterator;
import java.util.Stack;

public class AppManager {

    private Stack<FragmentActivity> activityStack;
    private static AppManager instance;

    private AppManager() {
        activityStack = new Stack<>();
    }

    /**
     * 单一实例
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(FragmentActivity activity) {
        try {
            if (!BuildConfig.IS_RELEASE) {
                Log.e(LogUtils.TAG,"add-->" + activity.getClass().getPackage().getName() + "." + activity.getClass().getSimpleName());
            }
        } catch (Exception e) {
        }
        activityStack.add(activity);
    }

    /**
     * 获取栈顶Activity（堆栈中最后一个压入的）
     */
    public FragmentActivity getTopActivity() {
        return activityStack.peek();
    }

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    public void finishTopActivity() {
        activityStack.pop().finish();

    }

    /**
     * 判断栈顶的页面是否为绘本阅读页面
     *
     * @return
     */
    public boolean isReadTopActivity() {
        if ("ZHLBookReadActivity".equals(getTopActivity().getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 判断栈顶的页面是否为汉字听写
     *
     * @return
     */
    public boolean isReadLWStartActivity() {
        if ("LWStartActivity".equals(getTopActivity().getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断栈顶的页面是否为笔画笔顺
     *
     * @return
     */
    public boolean isStrokeOrderActivity() {
        if ("StrokeOrderActivity".equals(getTopActivity().getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断栈顶的页面是否为Web页面
     *
     * @return
     */
    public boolean isCommonWebViewActivity() {
        if ("CommonWebViewActivity".equals(getTopActivity().getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 判断栈顶的页面是否为音频播放页面
     *
     * @return
     */
    public boolean isAudioTopActivity() {
        if ("AudioCommonActivity".equals(getTopActivity().getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 判断栈顶的页面是否为首页
     *
     * @return
     */
    public boolean isFrameHomeActivity() {
        if ("FrameHomeActivity".equals(getTopActivity().getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 判断栈顶的页面是否为朗读详情
     *
     * @return
     */
    public boolean isReadDetailsActivity() {
        if ("ReadDetailsActivity".equals(getTopActivity().getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断栈顶的页面是否为个人空间
     *
     * @return
     */
    public boolean isStudentInfoFragmentActivity() {
        if ("StudentInfoFragmentActivity".equals(getTopActivity().getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断栈顶的页面是否为课文背诵选段页
     *
     * @return
     */
    public boolean isReciteArticleHomeActivity() {
        if ("ReciteArticleHomeActivity".equals(getTopActivity().getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 判断栈顶的页面是否为课文朗读
     *
     * @return
     */
    public boolean isListenToClassActivity() {
        if ("ListenToClassActivity".equals(getTopActivity().getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断栈顶的页面是否为课文背诵页
     *
     * @return
     */
    public boolean isReciteArticleDetailActivity() {
        if ("ReciteArticleDetailActivity".equals(getTopActivity().getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断栈顶的页面是否为课文背诵完成页
     *
     * @return
     */
    public boolean isReciteArticleResultActivity() {
        if ("ReciteArticleResultActivity".equals(getTopActivity().getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        Iterator iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            FragmentActivity activity = (FragmentActivity) iterator.next();
            if (activity.getClass().equals(cls)) {
                iterator.remove();
                activity.finish();
            }
        }
    }

    /**
     * 结束指定个数的Activity，当堆栈中个数大于指定个数时
     */
    public void finishMoreActivity(int page) {
        if (page >= activityStack.size()) {
            page = activityStack.size() - 1;
        }
        for (int i = 0; i < page; i++) {
            finishTopActivity();
        }
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (FragmentActivity activity : activityStack) {
            activity.finish();
        }
        activityStack.clear();
    }

    /**
     * 结束所有除了LoginActivity
     */
    public void finishAllNotLoginActivity() {
        for (FragmentActivity activity : activityStack) {
            if (!"LoginActivity".equals(getTopActivity().getClass().getSimpleName())) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            finishAllActivity();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());

        } catch (Exception e) {
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(FragmentActivity activity) {
        if (activity != null) {
            try {
                if (!BuildConfig.IS_RELEASE) {
                    Log.e(LogUtils.TAG,"remove-->" + activity.getClass().getSimpleName());
                }
            } catch (Exception e) {
            }
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 得到指定类名的Activity
     */
    public FragmentActivity getActivity(Class<?> cls) {
        for (FragmentActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

}

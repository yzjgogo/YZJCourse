package com.yin.yzjcourse;

import android.app.Activity;
import androidx.core.app.ActivityCompat;


import com.yin.yzjcourse.utils.DLog;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kougin on 2016/9/21.
 */
public class ActivityStack {
    private List<Activity> activities = new LinkedList<>();
    private static ActivityStack sActivityStack;

    public ActivityStack() {
    }

    public static ActivityStack getInstance() {
        if (sActivityStack == null) {
            sActivityStack = new ActivityStack();
        }
        return sActivityStack;
    }

    public void addActivity(Activity activity) {
        DLog.eLog("add-->" + activity.getClass().getPackage().getName() + "." + activity.getClass().getSimpleName());
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        DLog.eLog("remove-->" + activity.getClass().getSimpleName());
        if (activities.contains(activity)) {
            activities.remove(activity);
        }
    }

    public boolean isContainActivity(Class clazz) {
        for (Activity activity : activities) {
            final String clazzName = activity.getClass().getSimpleName();
            if (clazzName.equals(clazz.getSimpleName())) {
                return true;
            }
        }
        return false;
    }

    public boolean isContainActivity(Activity activity) {
        if (activities.contains(activity)) {
            return true;
        }
        return false;
    }
}

package com.yin.yzjcourse;

import android.content.Context;

/**
 * Created by think on 2017/3/9.
 */

public class Utils {

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}

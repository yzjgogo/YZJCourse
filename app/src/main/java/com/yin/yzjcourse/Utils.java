package com.yin.yzjcourse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.InputStream;

/**
 * Created by think on 2017/3/9.
 */

public class Utils {
    public static final String INTENT_BUNDLE_KEY = "bundle";
    public static final String ANIM_TYPE_KEY = "anim_type_key";
    public static final String ANIM_TYPE_SCALE = "anim_type_scale";
    public static final String ANIM_TYPE_ALPHA = "anim_type_alpha";
    public static final String ANIM_TYPE_ROTATE = "anim_type_rotate";
    public static final String ANIM_TYPE_TRANSLATE = "anim_type_translate";

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static void startActivity(Context context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(INTENT_BUNDLE_KEY, bundle);
        context.startActivity(intent);
    }
    public static void log(String content){
        Log.e("yin",content);
    }
    public static void showToast(Context context,String content){
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取屏幕的宽
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        final Resources resources = context.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        final Resources resources = context.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 给定一个字符判断是不是中文字符
     * @param c 任意字符
     * @return 如果任意字符c是中文字符则返回true，否则返回false
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
}

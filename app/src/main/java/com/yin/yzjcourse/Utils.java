package com.yin.yzjcourse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
}

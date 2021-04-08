package com.yin.yzjcourse.DiyWidget;

import android.os.Bundle;
import androidx.core.view.ViewCompat;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

/**
 * 其实就是View的animate()的兼容实现，参考View的animate()即可；
 * ViewPropertyAnimatorCompat参考ViewPropertyAnimator
 */
public class ViewCompatAnimateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_compat_animate);

        //用法举例
        View view = new View(this);
        ViewCompat.animate(view).scaleX(1).scaleY(1).setDuration(200).start();
    }
}

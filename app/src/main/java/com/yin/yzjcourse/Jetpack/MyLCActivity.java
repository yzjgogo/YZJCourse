package com.yin.yzjcourse.Jetpack;

import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

/**
 * 注意：
 * Activity和Fragment只是帮我们实现好了这一套方案，可以进去看看，也用到了LifecycleRegistry，参考ActivityDaiLi代理MyLCActivity
 * Activity和Fragment都是LifecycleOwner类型
 *
 * 我们可以像Activity和Fragment一样，实现自己的类的相同功能；参考CustomDaiLi代理CustomOwner
 */
public class MyLCActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lc);
        //LifecycleObserver和GenericLifecycleObserver代理Activity的生命周期，系统已经实现，这里ActivityDaiLi代理了MyLCActivity的生命周期
        //使用LifecycleObserver
        getLifecycle().addObserver(new ActivityDaiLi());
        //使用GenericLifecycleObserver 继承自LifecycleObserver
        getLifecycle().addObserver(new ActivityDaiLiGeneric());

        //自己的类的生命周期代理实现，实现方式与Activity和Fragment完全一样
        //这里CustomDaiLi代理了CustomOwner的生命周期
        //主动调用生命周期的方法onOpen()，onRun()，onRelease()，模拟触发生命周期
        CustomOwner customOwner = new CustomOwner();//自定义的CustomOwner类似Activity和Fragment
        customOwner.onOpen();
        customOwner.onRun();
        customOwner.onRelease();
    }
}

package com.yin.yzjcourse.Base;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GaussActivity extends BaseActivity {


    @BindView(R.id.ll_gauss)
    LinearLayout ll_gauss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauss);
        ButterKnife.bind(this);
        /*
        Android 12 (API 31) 引入了 RenderEffect.createBlurEffect()，可以非常方便地对整个 View 加模糊：
        20f 是模糊半径，可以调节。

这种方式性能好，不需要额外库。

但仅支持 Android 12+。
        * */
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (Build.VERSION.SDK_INT >= 31) {
            //因为这个项目编辑运行版本没到android 12所以找不到这些api
//            View rootView = findViewById(android.R.id.content);
//            rootView.setRenderEffect(RenderEffect.createBlurEffect(20f, 20f, Shader.TileMode.CLAMP));
        }
    }
}

package com.yin.yzjcourse.Coordinator;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

public class FlexibleSpaceExampleActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener{

    private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;
    private View mFab;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexible_space_example);

        mFab = findViewById(R.id.flexible_example_fab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.flexible_example_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        AppBarLayout appbar = (AppBarLayout) findViewById(R.id.flexible_example_appbar);
        appbar.addOnOffsetChangedListener(this);
    }

    /**
     * AppBarLayout垂直滚动的偏移距离监听
     * getTotalScrollRange()返回AppBarLayout可滚动的总范围，正数。
     * @param appBarLayout
     * @param i 偏移距离px，负数，例如从折叠到展开时-500~0,从展开到折叠是0~-500
     */
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
//		Log.e("yin","参数："+i);
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();
        Log.e("yin","总范围："+appBarLayout.getTotalScrollRange()+",当前："+i);

        int currentScrollPercentage = (Math.abs(i)) * 100
                / mMaxScrollSize;

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;
                //ViewCompat主要是用来提供不同Android版本的兼容性的
                ViewCompat.animate(mFab).scaleY(0).scaleX(0).start();
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false;
                ViewCompat.animate(mFab).scaleY(1).scaleX(1).start();
            }
        }
    }
}

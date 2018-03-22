package com.yin.yzjcourse.DiyWidget.DrawView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrawViewActivity extends BaseActivity {

    @BindView(R.id.tv_bse)
    Button tvBse;
    @BindView(R.id.sv_Skew)
    SkewView svSkew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_view);
        ButterKnife.bind(this);

        ObjectAnimator animator = ObjectAnimator.ofInt(svSkew,"angle",0,90);
        animator.setDuration(9000);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }

    @OnClick(R.id.tv_bse)
    public void onClick() {
        startActivity(new Intent(this, BezierLineActivity.class));
    }
}

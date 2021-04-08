package com.yin.yzjcourse.DiyWidget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.yin.yzjcourse.DiyWidget.DrawView.SkewView;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CanvasSkewActivity extends AppCompatActivity {
    @BindView(R.id.sv_Skew)
    SkewView svSkew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_skew);
        ButterKnife.bind(this);

        ObjectAnimator animator = ObjectAnimator.ofInt(svSkew,"angle",0,90);
        animator.setDuration(9000);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }
}

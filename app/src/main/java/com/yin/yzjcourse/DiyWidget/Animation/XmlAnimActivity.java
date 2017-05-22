package com.yin.yzjcourse.DiyWidget.Animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by think on 2017/5/18.
 */

public class XmlAnimActivity extends AppCompatActivity {
    @BindView(R.id.bt_alpha)
    Button btAlpha;
    @BindView(R.id.bt_scale)
    Button btScale;
    @BindView(R.id.bt_rotate)
    Button btRotate;
    @BindView(R.id.bt_trans)
    Button btTrans;
    @BindView(R.id.view_targent)
    View viewTargent;
    @BindView(R.id.bt_set)
    Button btSet;
    @BindView(R.id.bt_interpolator)
    Button btInterpolator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_anim_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_alpha, R.id.bt_scale, R.id.bt_rotate, R.id.bt_trans, R.id.bt_set, R.id.bt_interpolator})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_scale:
                viewTargent.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scaleanim));//缩放
                break;
            case R.id.bt_alpha:
                viewTargent.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alphaanim));//透明度
                break;
            case R.id.bt_rotate:
                viewTargent.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotateanim));//旋转
                break;
            case R.id.bt_trans:
                viewTargent.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translateanim));//平移
                break;
            case R.id.bt_set:
                viewTargent.startAnimation(AnimationUtils.loadAnimation(this, R.anim.setanim));//动画集合
                break;
            case R.id.bt_interpolator:
                startActivity(new Intent(this,FourAnimInterpolatorActivity.class));
                break;
        }
    }
}

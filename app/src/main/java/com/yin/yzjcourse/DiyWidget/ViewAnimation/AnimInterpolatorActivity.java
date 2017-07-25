package com.yin.yzjcourse.DiyWidget.ViewAnimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
      1:accelerate_decelerate_interpolator
        整个动画过程先加速后减速，也就是说动画开始和结束的地方速度最小，中间速度最大
      2:accelerate_interpolator
        在整个动画开始结束的过程中，速度由慢到快，开始最慢结束时最快
      3：decelerate_interpolator
        在整个动画开始结束的过程中，速度由快到慢，开始时最快结束时最慢
      4：anticipate_interpolator
         在整个动画过程中，动画开始一小段时间后又回退（或前进）到动画的最开始，紧接着快速重新动画，直到动画结束时的画面
         就像拉弹弓一样，蓄力前进那种感觉
      5：anticipate_overshoot_interpolator
        在整个动画过程中，动画开始一小段时间后又回退到动画的最开始，紧接着快速重新动画，甚至超出动画结束时的状态，
        然后又回退到动画结束时的状态。
       与anticipate_interpolator类似，区别在于会超出动画正常结束时的状态然后向动画正常结束时的状态回退。
      6：bounce_interpolator
        动画结束的时候会弹起，类似弹球落在地上多次弹起后逐渐静止。
      7：cycle_interpolator
        动画循环播放特定的次数，速率改变沿着正弦曲线。
      8：linear_interpolator
        整个动画过程速度不变，即匀速执行动画。
      9：overshoot_interpolator
        动画快结束时并没有停止，而是继续向超出动画正常结束时的状态继续前进，然后又回退到动画正常结束时的状态。

 */
public class AnimInterpolatorActivity extends BaseActivity {

    @BindView(R.id.bt_acceleratedecelerate)
    Button btAcceleratedecelerate;
    @BindView(R.id.bt_accelerate)
    Button btAccelerate;
    @BindView(R.id.bt_anticipate)
    Button btAnticipate;
    @BindView(R.id.bt_anticipateovershoot)
    Button btAnticipateovershoot;
    @BindView(R.id.bt_bounce)
    Button btBounce;
    @BindView(R.id.bt_cycle)
    Button btCycle;
    @BindView(R.id.bt_decelerate)
    Button btDecelerate;
    @BindView(R.id.bt_listener)
    Button btLinear;
    @BindView(R.id.bt_overshoot)
    Button btOvershoot;
    @BindView(R.id.view_target)
    View viewTarget;
    private String currentAnimType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_interpolator);
        ButterKnife.bind(this);
        initIntent();
    }

    private void initIntent() {
        currentAnimType = getIntent().getBundleExtra(Utils.INTENT_BUNDLE_KEY).getString(Utils.ANIM_TYPE_KEY,"");
        Utils.log("当前类型："+currentAnimType);
    }

    @OnClick({R.id.bt_acceleratedecelerate, R.id.bt_accelerate, R.id.bt_anticipate, R.id.bt_anticipateovershoot, R.id.bt_bounce, R.id.bt_cycle, R.id.bt_decelerate, R.id.bt_listener, R.id.bt_overshoot})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_acceleratedecelerate:
                btAcceleratedecelerate();
                break;
            case R.id.bt_accelerate:
                btAccelerate();
                break;
            case R.id.bt_anticipate:
                btAnticipate();
                break;
            case R.id.bt_anticipateovershoot:
                btAnticipateovershoot();
                break;
            case R.id.bt_bounce:
                btBounce();
                break;
            case R.id.bt_cycle:
                btCycle();
                break;
            case R.id.bt_decelerate:
                btDecelerate();
                break;
            case R.id.bt_listener:
                btLinear();
                break;
            case R.id.bt_overshoot:
                btOvershoot();
                break;
        }
    }

    private void btAcceleratedecelerate() {
        switch (currentAnimType) {
            case Utils.ANIM_TYPE_SCALE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_accelerate_decelerate));
                break;
            case Utils.ANIM_TYPE_ROTATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate_accelerate_decelerate));
                break;
            case Utils.ANIM_TYPE_ALPHA:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.alpha_accelerate_decelerate));
                break;
            case Utils.ANIM_TYPE_TRANSLATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.translate_accelerate_decelerate));
                break;
        }
    }

    private void btAccelerate() {
        switch (currentAnimType) {
            case Utils.ANIM_TYPE_SCALE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_accelerate));
                break;
            case Utils.ANIM_TYPE_ROTATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate_accelerate));
                break;
            case Utils.ANIM_TYPE_ALPHA:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.alpha_accelerate));
                break;
            case Utils.ANIM_TYPE_TRANSLATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.translate_accelerate));
                break;
        }
    }

    private void btAnticipate() {
        switch (currentAnimType) {
            case Utils.ANIM_TYPE_SCALE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anticipate));
                break;
            case Utils.ANIM_TYPE_ROTATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate_anticipate));
                break;
            case Utils.ANIM_TYPE_ALPHA:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.alpha_anticipate));
                break;
            case Utils.ANIM_TYPE_TRANSLATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.translate_anticipate));
                break;
        }
    }

    private void btAnticipateovershoot() {
        switch (currentAnimType) {
            case Utils.ANIM_TYPE_SCALE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anticipate_overshoot));
                break;
            case Utils.ANIM_TYPE_ROTATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate_anticipate_overshoot));
                break;
            case Utils.ANIM_TYPE_ALPHA:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.alpha_anticipate_overshoot));
                break;
            case Utils.ANIM_TYPE_TRANSLATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.translate_anticipate_overshoot));
                break;
        }
    }

    private void btBounce() {
        switch (currentAnimType) {
            case Utils.ANIM_TYPE_SCALE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_bounce));
                break;
            case Utils.ANIM_TYPE_ROTATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate_bounce));
                break;
            case Utils.ANIM_TYPE_ALPHA:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.alpha_bounce));
                break;
            case Utils.ANIM_TYPE_TRANSLATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.translate_bounce));
                break;
        }
    }

    private void btCycle() {
        switch (currentAnimType) {
            case Utils.ANIM_TYPE_SCALE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_cycle));
                break;
            case Utils.ANIM_TYPE_ROTATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate_cycle));
                break;
            case Utils.ANIM_TYPE_ALPHA:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.alpha_cycle));
                break;
            case Utils.ANIM_TYPE_TRANSLATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.translate_cycle));
                break;
        }
    }

    private void btDecelerate() {
        switch (currentAnimType) {
            case Utils.ANIM_TYPE_SCALE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_decelerate));
                break;
            case Utils.ANIM_TYPE_ROTATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate_decelerate));
                break;
            case Utils.ANIM_TYPE_ALPHA:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.alpha_decelerate));
                break;
            case Utils.ANIM_TYPE_TRANSLATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.translate_decelerate));
                break;
        }
    }

    private void btLinear() {
        switch (currentAnimType) {
            case Utils.ANIM_TYPE_SCALE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_linear));
                break;
            case Utils.ANIM_TYPE_ROTATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate_linear));
                break;
            case Utils.ANIM_TYPE_ALPHA:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.alpha_linear));
                break;
            case Utils.ANIM_TYPE_TRANSLATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.translate_linear));
                break;
        }
    }

    private void btOvershoot() {
        switch (currentAnimType) {
            case Utils.ANIM_TYPE_SCALE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_overshoot));
                break;
            case Utils.ANIM_TYPE_ROTATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate_overshoot));
                break;
            case Utils.ANIM_TYPE_ALPHA:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.alpha_overshoot));
                break;
            case Utils.ANIM_TYPE_TRANSLATE:
                viewTarget.startAnimation(AnimationUtils.loadAnimation(this,R.anim.translate_overshoot));
                break;
        }
    }
}

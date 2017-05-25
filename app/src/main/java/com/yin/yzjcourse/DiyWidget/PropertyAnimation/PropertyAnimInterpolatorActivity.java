package com.yin.yzjcourse.DiyWidget.PropertyAnimation;

import android.animation.ArgbEvaluator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PropertyAnimInterpolatorActivity extends AppCompatActivity {

    @BindView(R.id.bt_start_property_anim)
    Button btStartPropertyAnim;
    @BindView(R.id.tv_target)
    TextView tvTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim_interpolator);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_start_property_anim, R.id.tv_target, R.id.bt_custom_interpolator, R.id.bt_custom_evaluator,R.id.bt_argb_evaluator})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_start_property_anim:
                startPropertyAnim();
            case R.id.bt_custom_interpolator:
                customInterpolator();
                break;
            case R.id.bt_custom_evaluator:
                useEvaluator();
                break;
            case R.id.bt_argb_evaluator:
                useArgbEvaluator();
                break;
            case R.id.tv_target:
                break;
        }
    }

    /**
     * 思考：监听器的onAnimationUpdate方法是如何得到当前的计算值的
     * 在固定的时间区间内(setDuration决定)，怎么实现动画不同的运行效果或者运行轨迹
     * <p>
     * 对于属性动画ValueAnimator,即使你没有setInterpolator，也会有一个默认的插值器，插值器的作用是
     * 在固定的时间区间内，决定了某一时刻ofInt或ofFloat指定区域内的值，参考图形：interpolator_method.png
     * TimeInterpolator是每一个插值器的基类，其只有一个方法float getInterpolation(float input)，每一个插值器都重写了该
     * 方法。这个方法返回值并不是当前的点的值，而是当前点的百分比的值，参考图形：interpolator_method.png
     * 因此要想实现自定义插值器，只需要实现TimeInterpolator并重写其getInterpolation方法即可
     */
    private void startPropertyAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(100, 700);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();//这个当前的点由插值器的getInterpolation决定
                tvTarget.layout(tvTarget.getLeft(), curValue, tvTarget.getRight(), curValue + tvTarget.getHeight());
            }
        });
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }

    /**
     * 由log可知，动画从终点开始到起点结束
     * 05-23 18:25:35.416 19978-19978/com.yin.yzjcourse E/yin: 800
     * 05-23 18:25:35.420 19978-19978/com.yin.yzjcourse E/yin: 800
     * 05-23 18:25:35.436 19978-19978/com.yin.yzjcourse E/yin: 796
     * 05-23 18:25:35.453 19978-19978/com.yin.yzjcourse E/yin: 793
     * 05-23 18:25:35.470 19978-19978/com.yin.yzjcourse E/yin: 790
     * 05-23 18:25:35.487 19978-19978/com.yin.yzjcourse E/yin: 786
     * .
     * .
     * .
     * 05-23 18:25:38.343 19978-19978/com.yin.yzjcourse E/yin: 215
     * 05-23 18:25:38.360 19978-19978/com.yin.yzjcourse E/yin: 211
     * 05-23 18:25:38.377 19978-19978/com.yin.yzjcourse E/yin: 208
     * 05-23 18:25:38.393 19978-19978/com.yin.yzjcourse E/yin: 205
     * 05-23 18:25:38.410 19978-19978/com.yin.yzjcourse E/yin: 201
     * 05-23 18:25:38.427 19978-19978/com.yin.yzjcourse E/yin: 200
     */
    private void customInterpolator() {
        ValueAnimator animator = ValueAnimator.ofInt(200, 800);
        animator.setDuration(3000);
        animator.setInterpolator(new SimpleInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Utils.log(animation.getAnimatedValue() + "");
                int curValue = (int) animation.getAnimatedValue();
                tvTarget.layout(tvTarget.getLeft(), curValue, tvTarget.getRight(), curValue + tvTarget.getHeight());
            }
        });
        animator.start();
    }

    /**
     * 插值器Interpolator用于确定某一时刻动画进度数值的百分比位置(getInterpolation返回值)
     * 拿到这个百分比位置后需要转化成确切的进度位置，这时候就需要用到Evaluator，Evaluator用于根据
     * 百分比位置、起点位置、终点位置计算出此时的确切位置。
     * 对于ValueAnimator及时你没有setEvaluator，也会有一个默认的Evaluator，如果你用的是ofInt则这个默认的就是IntEvaluator
     * 如果你用的ofFloat则这个默认的就是FloatEvaluator
     * <p>
     * 每一种Evaluator都会实现TypeEvaluator接口并重写其public T evaluate(float fraction, T startValue, T endValue)方法
     * 因此我们可以自定义Evaluator
     * 参考图片interpolator_method.png
     */
    private void useEvaluator() {
        ValueAnimator animator = ValueAnimator.ofInt(100, 500);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                tvTarget.layout(tvTarget.getLeft(), curValue, tvTarget.getRight(), curValue + tvTarget.getHeight());
            }
        });
//        animator.setInterpolator(new LinearInterpolator());
//        animator.setEvaluator(new IntEvaluator());
        animator.setEvaluator(new MyIntEvaluator());
        animator.start();
    }

    /**
     * 自定义插值器只需实现TimeInterpolator并重写其getInterpolation
     */
    class SimpleInterpolator implements TimeInterpolator {

        @Override
        public float getInterpolation(float input) {
            return 1 - input;//开始时动画在终点，结束时动画在起点
        }
    }

    /**
     * 自定义Evaluator：参考图片interpolator_method.png
     */
    class MyIntEvaluator implements TypeEvaluator<Integer> {
        //fraction：插值器的getInterpolation返回的百分比数值
        //startValue：进度的开始值,由ofInt或ofFloat确定
        //endValue：进度的结束值,由ofInt或ofFloat确定
        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            int start = startValue;
            return (int) (start + (endValue - startValue) * fraction);
        }
    }

    /**
     * ArgbEvalutor是用来做颜色值过渡转换的
     */
    private void useArgbEvaluator() {
        ValueAnimator animator = ValueAnimator.ofInt(0xffffff00, 0xff0000ff);
        animator.setEvaluator(new ArgbEvaluator());
//        animator.setInterpolator(new BounceInterpolator());
        animator.setDuration(3000);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                tvTarget.setBackgroundColor(curValue);

            }
        });
        animator.start();
    }
}

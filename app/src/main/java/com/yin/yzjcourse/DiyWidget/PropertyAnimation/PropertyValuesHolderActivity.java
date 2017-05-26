package com.yin.yzjcourse.DiyWidget.PropertyAnimation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yin.yzjcourse.DiyWidget.PropertyAnimation.View.MyTextView;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PropertyValuesHolderActivity extends AppCompatActivity {

    @BindView(R.id.tv_target)
    MyTextView tvTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_values_holder);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_float_int, R.id.bt_object})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_float_int:
                showFloatIntAnim();
                break;
            case R.id.bt_object:
                showObjectIntAnim();
                break;
        }
    }

    /**
     * ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvTarget,"rotation",-60f,60f);
     * ValueAnimator valueAnimator = ValueAnimator.ofInt(1,2,3);
     * 无论是ObjectAnimator还是ValueAnimator的ofInt,ofFloat,ofObject方法，构建出来的动画都只能真对控件的某一个属性做动画，即只能
     * 实现控件的某一个维度的控件效果，如果想对一个控件组合使用多个控件怎么办呢，例如我想同时对控件执行旋转和变色动画，则PropertyValuesHolder
     * 可以实现组合动画的效果。
     * 仔细观察属性动画的构造方法对比ObjectAnimator和ValueAnimator的ofInt,ofFloat,ofObject方法可发现规律：
     * 其实ofInt,ofFloat,ofObject内部也是把参数封装成PropertyValuesHolder后才执行动画
     */
    private void showFloatIntAnim() {
        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat("rotation", -60f, 60f, -40f, 40f, -20f, 20f, 0f);
        PropertyValuesHolder colorHolder = PropertyValuesHolder.ofInt("BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);
        //可传入多个PropertyValuesHolder实现组合动画
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tvTarget, rotationHolder, colorHolder);
        animator.setDuration(3000);
        animator.start();
    }

    /**
     * 因为Object是你自定义的，所以TypeEvaluator也要自定义保证其evaluate方法返回值与你自定义相同
     */
    private void showObjectIntAnim() {
        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat("rotation", -60f, 60f, -40f, 40f, -20f, 20f, 0f);
        PropertyValuesHolder letterHolder = PropertyValuesHolder.ofObject("CharText", new LetterEvaluator(), new Character('A'), new Character('Z'));
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tvTarget, letterHolder, rotationHolder);
        animator.setDuration(2000);
        animator.start();
    }

    class LetterEvaluator implements TypeEvaluator<Character> {
        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int startInt = (int) startValue;
            int endInt = (int) endValue;
            int curInt = (int) (startInt + fraction * (endInt - startInt));
            char result = (char) curInt;
            return result;
        }
    }
}

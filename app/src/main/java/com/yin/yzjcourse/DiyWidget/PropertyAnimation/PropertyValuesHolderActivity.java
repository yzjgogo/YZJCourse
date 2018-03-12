package com.yin.yzjcourse.DiyWidget.PropertyAnimation;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.DiyWidget.PropertyAnimation.View.MyTextView;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PropertyValuesHolderActivity extends BaseActivity {

    @BindView(R.id.tv_target)
    MyTextView tvTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_values_holder);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_float_int, R.id.bt_object, R.id.bt_valueanimator,
            R.id.bt_keyframe_of_float,R.id.bt_keyframe_of_int,R.id.bt_keyframe_of_object,
            R.id.bt_keyframe_use,R.id.bt_keyframe_phone
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_float_int:
                showFloatIntAnim();
                break;
            case R.id.bt_object:
                showObjectIntAnim();
                break;
            case R.id.bt_valueanimator:
                showValueAnimatorHolder();
                break;
            case R.id.bt_keyframe_of_float:
                showKeyframeOfFloat();
                break;
            case R.id.bt_keyframe_of_int:
                showKeyframeOfInt();
            case R.id.bt_keyframe_of_object:
                showKeyframeOfObject();
                break;
            case R.id.bt_keyframe_use:
                showKeyframeUse();
                break;
            case R.id.bt_keyframe_phone:
                showKeyframePhone();
                break;
        }
    }

    /**
     * ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvTarget,"rotation",-60f,60f);
     * ValueAnimator valueAnimator = ValueAnimator.ofInt(1,2,3);
     * 无论是ObjectAnimator还是ValueAnimator的ofInt,ofFloat,ofObject方法，构建出来的动画都只能真对控件的某一个属性做动画，即只能
     * 实现控件的某一个维度的控件效果，如果想对一个控件组合使用多个动画怎么办呢，例如我想同时对控件执行旋转和变色动画，则PropertyValuesHolder
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

    /**
     * ValutAnimator的PropertyValuesHolder用法
     */
    private void showValueAnimatorHolder() {
        PropertyValuesHolder myWidthHolder = PropertyValuesHolder.ofFloat("myWidth", 50, 200);
        PropertyValuesHolder myHeightHolder  = PropertyValuesHolder.ofFloat("myHeight",  100, 500);
        ValueAnimator ani = ValueAnimator
                .ofPropertyValuesHolder(myWidthHolder, myHeightHolder)
                .setDuration(400);
        ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float myWidth = (Float) valueAnimator.getAnimatedValue("myWidth");
                float myHeight  = (Float) valueAnimator.getAnimatedValue("myHeight");
                LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) tvTarget.getLayoutParams();
                p.width = (int) myWidth;
                p.height = (int) myHeight;
                tvTarget.setLayoutParams(p);
            }
        });
        ani.start();
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
    private void showKeyframeOfFloat() {
        Keyframe keyframe0 = Keyframe.ofFloat(0f);
        keyframe0.setValue(0);
        Keyframe keyframe1 = Keyframe.ofFloat(0.1f);
        keyframe1.setValue(-20f);
        Keyframe keyframe2 = Keyframe.ofFloat(0.2f,20f);
        Keyframe keyframe3 = Keyframe.ofFloat(0.3f,-20f);
        Keyframe keyframe4 = Keyframe.ofFloat(0.4f,20f);
        Keyframe keyframe5 = Keyframe.ofFloat(0.5f,-20f);
        Keyframe keyframe6 = Keyframe.ofFloat(0.6f,20f);
        Keyframe keyframe7 = Keyframe.ofFloat(0.7f,-20f);
        Keyframe keyframe8 = Keyframe.ofFloat(0.8f,20f);
        Keyframe keyframe9 = Keyframe.ofFloat(0.9f,-20f);
        Keyframe keyframe10 = Keyframe.ofFloat(1f,0);
        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofKeyframe("rotation",keyframe0,keyframe1,keyframe2,keyframe3,keyframe4,keyframe5,keyframe6,keyframe7,keyframe8,keyframe9,keyframe10);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tvTarget,valuesHolder);
        animator.setDuration(3000);
        animator.start();
    }

    private void showKeyframeOfInt() {
        Keyframe keyframe0 = Keyframe.ofInt(0f,0x00000000);
        Keyframe keyframe1 = Keyframe.ofInt(1f,0xff000000);
        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofKeyframe("BackgroundColor",keyframe0,keyframe1);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tvTarget,valuesHolder);
        animator.setDuration(3000);
        animator.start();
    }

    /**
     * Keyframe的ofObject方法没有传入TypeEvaluator的方法，所以需要通过PropertyValuesHolder设置一个TypeEvaluator
     */
    private void showKeyframeOfObject() {
        Keyframe keyframe0 = Keyframe.ofObject(0f,new Character('A'));
        Keyframe keyframe1 = Keyframe.ofObject(0.1f,new Character('L'));
        Keyframe keyframe2 = Keyframe.ofObject(1f,new Character('Z'));
        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofKeyframe("CharText",keyframe0,keyframe1,keyframe2);
        //使用ofObject要给PropertyValuesHolder设置自定义Evaluator
        valuesHolder.setEvaluator(new CharEvaluator());
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tvTarget, valuesHolder);
        animator.setDuration(3000);
        animator.start();
    }


    /**
     给某一个KeyFrame设置插值器，该插值器会作用于上一个Keyframe到当前Keyframe;
     例如给Keyframe4设置插值器，则该插值器的作用范围是从Keyframe3到Keyframe4
     *
     如果去掉第0帧，将以第一个关键帧为起始位置
     如果去掉结束帧，将以最后一个关键帧为结束位置
     使用Keyframe来构建动画，至少要有两个或两个以上帧
     */
    private void showKeyframeUse() {
        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.5f, 100f);
        frame1.setInterpolator(new LinearInterpolator());
        Keyframe frame2 = Keyframe.ofFloat(1,0);
        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0,frame1,frame2);

        Animator animator = ObjectAnimator.ofPropertyValuesHolder(tvTarget, frameHolder);
        animator.setDuration(3000);
        animator.start();
    }

    private void showKeyframePhone() {
        /**
         * 左右震动效果
         */
        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.1f, -20f);
        Keyframe frame2 = Keyframe.ofFloat(0.2f, 20f);
        Keyframe frame3 = Keyframe.ofFloat(0.3f, -20f);
        Keyframe frame4 = Keyframe.ofFloat(0.4f, 20f);
        Keyframe frame5 = Keyframe.ofFloat(0.5f, -20f);
        Keyframe frame6 = Keyframe.ofFloat(0.6f, 20f);
        Keyframe frame7 = Keyframe.ofFloat(0.7f, -20f);
        Keyframe frame8 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe frame9 = Keyframe.ofFloat(0.9f, -20f);
        Keyframe frame10 = Keyframe.ofFloat(1, 0);
        PropertyValuesHolder frameHolder1 = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10);


/**
 * scaleX放大1.1倍
 */
        Keyframe scaleXframe0 = Keyframe.ofFloat(0f, 1);
        Keyframe scaleXframe1 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe scaleXframe2 = Keyframe.ofFloat(0.2f, 1.1f);
        Keyframe scaleXframe3 = Keyframe.ofFloat(0.3f, 1.1f);
        Keyframe scaleXframe4 = Keyframe.ofFloat(0.4f, 1.1f);
        Keyframe scaleXframe5 = Keyframe.ofFloat(0.5f, 1.1f);
        Keyframe scaleXframe6 = Keyframe.ofFloat(0.6f, 1.1f);
        Keyframe scaleXframe7 = Keyframe.ofFloat(0.7f, 1.1f);
        Keyframe scaleXframe8 = Keyframe.ofFloat(0.8f, 1.1f);
        Keyframe scaleXframe9 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleXframe10 = Keyframe.ofFloat(1, 1);
        PropertyValuesHolder frameHolder2 = PropertyValuesHolder.ofKeyframe("ScaleX", scaleXframe0, scaleXframe1, scaleXframe2, scaleXframe3, scaleXframe4, scaleXframe5, scaleXframe6, scaleXframe7, scaleXframe8, scaleXframe9, scaleXframe10);


/**
 * scaleY放大1.1倍
 */
        Keyframe scaleYframe0 = Keyframe.ofFloat(0f, 1);
        Keyframe scaleYframe1 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe scaleYframe2 = Keyframe.ofFloat(0.2f, 1.1f);
        Keyframe scaleYframe3 = Keyframe.ofFloat(0.3f, 1.1f);
        Keyframe scaleYframe4 = Keyframe.ofFloat(0.4f, 1.1f);
        Keyframe scaleYframe5 = Keyframe.ofFloat(0.5f, 1.1f);
        Keyframe scaleYframe6 = Keyframe.ofFloat(0.6f, 1.1f);
        Keyframe scaleYframe7 = Keyframe.ofFloat(0.7f, 1.1f);
        Keyframe scaleYframe8 = Keyframe.ofFloat(0.8f, 1.1f);
        Keyframe scaleYframe9 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleYframe10 = Keyframe.ofFloat(1, 1);
        PropertyValuesHolder frameHolder3 = PropertyValuesHolder.ofKeyframe("ScaleY", scaleYframe0, scaleYframe1, scaleYframe2, scaleYframe3, scaleYframe4, scaleYframe5, scaleYframe6, scaleYframe7, scaleYframe8, scaleYframe9, scaleYframe10);

/**
 * 构建动画
 */
        Animator animator = ObjectAnimator.ofPropertyValuesHolder(tvTarget, frameHolder1, frameHolder2, frameHolder3);
        animator.setDuration(1000);
        animator.start();
    }
    class CharEvaluator implements TypeEvaluator<Character>{

        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int start = (int)startValue;
            int end = (int)endValue;
            return (char)(start+(end-start)*fraction);
        }
    }
}

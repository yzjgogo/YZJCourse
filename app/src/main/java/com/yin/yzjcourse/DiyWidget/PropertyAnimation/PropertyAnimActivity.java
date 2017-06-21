package com.yin.yzjcourse.DiyWidget.PropertyAnimation;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.yin.yzjcourse.DiyWidget.PropertyAnimation.View.PointView;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 视图动画与属性动画的区别，参考图viewanim_propertyanim.png
 * 对于视图动画，一个view在动画过程中和动画结束后(view停留在动画结束时的状态)，都没有改变该view的任何属性或任何值甚至view的位置大小等都没有改变，只是
 * 这个view看起来和动画之前不一样了，以单击事件为例，即使动画使view看起来离开的原来的位置，但是其单击事件的响应区域仍然是view的原来的位置。
 * 对于属性动画：Property Animator是通过改变控件内部的属性值来做动画的，以单击事件为例，属性动画view的响应区域随着view的位置变化而变化，即view通过动画编程什么样了
 * view就真的变成什么样了。
 * 一句话总结：视图动画没有改变view本身，你所看到的动画都是假象，是视觉效果
 * 而属性动画改变了view的本身，你所看到的动画都是真实的，是view改变之后的样子
 *
 * 属性动画的整个路程参考：animator_process.png
 */
public class PropertyAnimActivity extends AppCompatActivity {

    @BindView(R.id.bt_value_int)
    Button btValueAnim;
    @BindView(R.id.tv_target)
    TextView tvTarget;
    @BindView(R.id.pv)
    PointView pv;
    private ValueAnimator intAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_value_int, R.id.tv_target, R.id.bt_value_float, R.id.bt_int_cancel, R.id.bt_of_object, R.id.bt_of_object_hard})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_value_int:
                intAnimator = doIntAnimator();
                break;
            case R.id.bt_int_cancel:

                //克隆一个新的ValueAnimator，然后开始动画
//                ValueAnimator newAnimator = intAnimator.clone();
                /**
                 * 移除AnimatorUpdateListener
                 */
//                void removeUpdateListener(AnimatorUpdateListener listener);
//                void removeAllUpdateListeners();
                /**
                 * 移除AnimatorListener
                 */
//                void removeListener(AnimatorListener listener);
//                void removeAllListeners();


//                if(intAnimator!=null && intAnimator.isRunning()){
                if (intAnimator != null && intAnimator.isStarted()) {
                    intAnimator.cancel();//在当前动画状态停止，而不是回到动画开始状态
//                    intAnimator.isStarted()
                } else {
                    intAnimator.start();//从头开始，而不是从停止的地方开始
                }

                break;
            case R.id.bt_value_float:
                doFloatAnimator();
                break;
            case R.id.bt_of_object:
                doObjectAnimator();
                break;
            case R.id.bt_of_object_hard:
                doViewAnimator();
                break;
            case R.id.tv_target:
                Utils.showToast(this, "我被点击了");
                break;
        }
    }

    /**
     * 05-23 14:58:08.933 20156-20156/com.yin.yzjcourse E/yin: 0
     * 05-23 14:58:08.939 20156-20156/com.yin.yzjcourse E/yin: 0
     * 05-23 14:58:08.954 20156-20156/com.yin.yzjcourse E/yin: 0
     * 05-23 14:58:08.971 20156-20156/com.yin.yzjcourse E/yin: 1
     * 05-23 14:58:08.988 20156-20156/com.yin.yzjcourse E/yin: 2
     * 05-23 14:58:09.005 20156-20156/com.yin.yzjcourse E/yin: 4
     * 05-23 14:58:09.021 20156-20156/com.yin.yzjcourse E/yin: 6
     * .
     * .
     * .
     * .
     * 05-23 14:58:09.640 20156-20156/com.yin.yzjcourse E/yin: 318
     * 05-23 14:58:09.657 20156-20156/com.yin.yzjcourse E/yin: 326
     * 05-23 14:58:09.673 20156-20156/com.yin.yzjcourse E/yin: 334
     * 05-23 14:58:09.690 20156-20156/com.yin.yzjcourse E/yin: 342
     * 05-23 14:58:09.707 20156-20156/com.yin.yzjcourse E/yin: 349
     * 05-23 14:58:09.724 20156-20156/com.yin.yzjcourse E/yin: 356
     * 05-23 14:58:09.741 20156-20156/com.yin.yzjcourse E/yin: 362
     * 05-23 14:58:09.757 20156-20156/com.yin.yzjcourse E/yin: 368
     * 05-23 14:58:09.774 20156-20156/com.yin.yzjcourse E/yin: 373
     * 05-23 14:58:09.790 20156-20156/com.yin.yzjcourse E/yin: 378
     * 05-23 14:58:09.807 20156-20156/com.yin.yzjcourse E/yin: 383
     * 05-23 14:58:09.824 20156-20156/com.yin.yzjcourse E/yin: 387
     * 05-23 14:58:09.840 20156-20156/com.yin.yzjcourse E/yin: 390
     * 05-23 14:58:09.857 20156-20156/com.yin.yzjcourse E/yin: 393
     * 05-23 14:58:09.874 20156-20156/com.yin.yzjcourse E/yin: 395
     * 05-23 14:58:09.891 20156-20156/com.yin.yzjcourse E/yin: 397
     * 05-23 14:58:09.907 20156-20156/com.yin.yzjcourse E/yin: 399
     * 05-23 14:58:09.924 20156-20156/com.yin.yzjcourse E/yin: 399
     * 05-23 14:58:09.941 20156-20156/com.yin.yzjcourse E/yin: 400
     * <p>
     * (0,400)的输出日志
     * 动画时间短(setDuration)则0-400可能有部分数字没有执行
     * 如果时间长，可能有部分数字执行多次
     * <p>
     * AnimatorListener监听log
     * 05-23 15:33:14.673 13642-13642/com.yin.yzjcourse E/yin: start
     * 05-23 15:33:20.701 13642-13642/com.yin.yzjcourse E/yin: repeat
     * 05-23 15:33:26.699 13642-13642/com.yin.yzjcourse E/yin: repeat
     * 05-23 15:33:32.697 13642-13642/com.yin.yzjcourse E/yin: repeat
     * 05-23 15:33:38.695 13642-13642/com.yin.yzjcourse E/yin: repeat //如果不取消就一直repeat
     * 05-23 15:33:41.121 13642-13642/com.yin.yzjcourse E/yin: cancel //点击取消按钮后
     * 05-23 15:33:41.121 13642-13642/com.yin.yzjcourse E/yin: end
     */
    private ValueAnimator doIntAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 600);//可变参数
        valueAnimator.setDuration(6000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                Utils.log(animation.getAnimatedValue() + "");
                int curValue = (int) animation.getAnimatedValue();//获取ValueAnimator当前的计算点
                //可看出，ValueAnimator没有跟任何控件相关联，ValueAnimator只是对值做动画运算，而由程序员自己根据其运算的值
                //改变目标view，即ValueAnimator不操控控件，而是由程序员自己操控控件
                //layout函数改变view的文字，是真的改变的view的原位置
                tvTarget.layout(curValue, curValue, curValue + tvTarget.getWidth(), curValue + tvTarget.getHeight());
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {//动画开始时调用
                Utils.log("start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {//动画结束时调用,如果取消就在cancel之后调用
                Utils.log("end");
            }

            @Override
            public void onAnimationCancel(Animator animation) {//动画取消时调用
                Utils.log("cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {//动画重复时调用，每重复一次就调用一次
                Utils.log("repeat");
            }
        });

        //setRepeatMode(int value)用于设置循环模式，取值为ValueAnimation.RESTART时,表示正序重新开始，当取值为ValueAnimation.REVERSE表示倒序重新开始
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);//INFINITE表示无线循环，0表示不循环
        /**
         * 延时多久时间开始，单位是毫秒
         */
//        public void setStartDelay(long startDelay)
        valueAnimator.start();
        return valueAnimator;
    }

    private void doFloatAnimator() {
        ValueAnimator floatAnimator = ValueAnimator.ofFloat(0f, 600f, 100f, 400f);//可变参数，参数越多可操控的动画就可以约复杂
        floatAnimator.setDuration(5000);
        floatAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float curFloat = (Float) animation.getAnimatedValue();//返回Object类型
                int curInt = curFloat.intValue();
                tvTarget.layout(curInt, curInt, curInt + tvTarget.getWidth(), curInt + tvTarget.getHeight());
            }
        });
        floatAnimator.start();
    }

    /**
     * 如果你用的ofInt则Evaluator的evaluate的返回值就是Integer,如果你用的是ofFloat则Evaluator的evaluate的返回值就是Float,即你传入的参数类型，决定了
     * 动画处理后返回的参数类型
     * 因此当你用ofObject时，需要传入自定义的Evaluator，因为你传入的动画值参数类型时不固定的(Object)，如果你不传入一个自定义的Evaluator，则Evaluator无法
     * 知道处理后返回什么样的数据类型
     */
    private void doObjectAnimator() {
        ValueAnimator animator = ValueAnimator.ofObject(new CharEvaluator(), new Character('A'), new Character('Z'));
        animator.setDuration(3000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //你在ofObject,ofInt,ofFloat方法传入的什么类型，则getAnimatedValue()就会返回什么类型
                char curValue = (char) animation.getAnimatedValue();
                tvTarget.setText(String.valueOf(curValue));
            }
        });
        animator.start();
    }

    class CharEvaluator implements TypeEvaluator<Character> {

        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int start = (int) startValue;
            int end = (int) endValue;
            return (char) (start + fraction * (end - start));
        }
    }

    private void doViewAnimator() {
        pv.doAnimator();
    }
    /************************************************ObjectAnimator**********************************************/
    /**
     * ValueAnimator与ObjectAnimator的对比
     *
     * ObjectAnimator继承自ValueAnimator
     *
     * ValueAnimator只是单纯的用于计算动画的进度值，至于你取不取这个值，或者你取到这个值(通过监听)之后对哪一个控件做什么操作，ValueAnimator是不管的，完全在于程序员自己
     * 而ObjectAnimator不需要设置监听，你在构建这个动画的同时就把要作用的控件传入进去，则ObjectAnimator计算完动画的进度值之后就直接作用于该控件、
     *
     * ObjectAnimator.ofFloat(Object target, String propertyName, float... values)中第一个参数是要作用的控件View，第二个参数来自于View的下面几个set方法名
     * //1、透明度：alpha
     public void setAlpha(float alpha)

     //2、旋转度数：rotation、rotationX、rotationY
     public void setRotation(float rotation)
     public void setRotationX(float rotationX)
     public void setRotationY(float rotationY)

     //3、平移：translationX、translationY
     public void setTranslationX(float translationX)
     public void setTranslationY(float translationY)

     //缩放：scaleX、scaleY
     public void setScaleX(float scaleX)
     public void setScaleY(float scaleY)

     即：alpha、rotation、rotationX、rotationY、translationX、translationY  、scaleX、scaleY，首字母可以大小写均可，其它必须是原样
     传入这些参数后，根据属性值拼装成对应的set函数的名字，比如这里的scaleY的拼装方法就是将属性的第一个字母强制大写后，
     与set拼接，所以就是setScaleY。然后通过反射找到对应控件的setScaleY(float scaleY)函数，将当前数字值做为setScaleY(float scale)的参数将其传入。
     *
     * 参考：animator_process.png
     */
    @OnClick({R.id.bt_object_animator_alpha, R.id.bt_object_animator_rotation, R.id.bt_object_animator_rotation_x,
            R.id.bt_object_animator_rotation_y, R.id.bt_object_animator_translationx, R.id.bt_object_animator_translationy,
            R.id.bt_object_animator_scale_x, R.id.bt_object_animator_scale_y, R.id.bt_object_animator_custom,
            R.id.bt_object_animator_get,R.id.bt_object_animator_bg})
    public void onClickObjectAnimator(View view) {
        switch (view.getId()) {
            case R.id.bt_object_animator_alpha:
                showObjectAnimAlpha();
                break;
            case R.id.bt_object_animator_rotation:
                showObjectAnimRotation();
                break;
            case R.id.bt_object_animator_rotation_x:
                showObjectAnimRotationX();
                break;
            case R.id.bt_object_animator_rotation_y:
                showObjectAnimRotationY();
                break;
            case R.id.bt_object_animator_translationx:
                showObjectAnimTranslationX();
                break;
            case R.id.bt_object_animator_translationy:
                showObjectAnimTranslationY();
                break;
            case R.id.bt_object_animator_scale_x:
                showObjectAnimScaleX();
                break;
            case R.id.bt_object_animator_scale_y:
                showObjectAnimScaleY();
                break;
            case R.id.bt_object_animator_bg:
                showObjectAnimBg();
                break;
            case R.id.bt_object_animator_custom:
                showObjectAnimCustom();
                break;
            case R.id.bt_object_animator_get:
                testObjectAnimSetGet();
                break;
        }
    }

    private void showObjectAnimAlpha() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvTarget,"alpha",1,0,1);//不透明到透明再到不透明
        animator.setDuration(3000);
//        animator.setInterpolator(new BounceInterpolator());
        animator.start();

    }

    private void showObjectAnimRotation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvTarget,"rotation",0,180,20);
        animator.setDuration(3000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }

    private void showObjectAnimRotationX() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvTarget,"rotationX",0,180,20);
        animator.setDuration(3000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }

    private void showObjectAnimRotationY() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvTarget,"rotationY",0,180,20);
        animator.setDuration(3000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }

    private void showObjectAnimTranslationX() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvTarget,"translationX",0,200,-200,0);
        animator.setDuration(3000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }

    private void showObjectAnimTranslationY() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvTarget,"translationY",0,200,-200,0);
        animator.setDuration(3000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }

    private void showObjectAnimScaleX() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvTarget,"ScaleX",0,3,1);
        animator.setDuration(3000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }

    private void showObjectAnimScaleY() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvTarget,"ScaleY",0,3,1);
        animator.setDuration(3000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }

    private void showObjectAnimBg() {
        ObjectAnimator animator = ObjectAnimator.ofInt(tvTarget, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        animator.setDuration(8000);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();
    }

    /**
     * 自定义一个属性pointRadius，只需在pv中添加setPointRadius即可
     */
    private void showObjectAnimCustom() {
        ObjectAnimator animator = ObjectAnimator.ofInt(pv,"pointRadius",0,300,100);
        animator.setDuration(3000);
        animator.start();
    }

    /**
      目标view get方法的作用
     1：当构造动画时的过度值只有一个时，即ofInt,ofFloat等的第三个参数只有一个值时
        如果目标view没有第二个参数对应的get方法，则系统默认从默认值到指定值，这里是从0到300
        如果有get方法，则会调用view的get方法获取动画的起始值即从get方法得到的值到300
     */
    private void testObjectAnimSetGet() {
        ObjectAnimator animator = ObjectAnimator.ofInt(pv,"pointRadius",300);
        animator.setDuration(3000);
        animator.start();
    }
}

package com.yin.yzjcourse.DiyWidget.PropertyAnimation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 视图动画与属性动画的区别，参考图viewanim_propertyanim.png
 对于视图动画，一个view在动画过程中和动画结束后(view停留在动画结束时的状态)，都没有改变该view的任何属性或任何值甚至view的位置大小等都没有改变，只是
 这个view看起来和动画之前不一样了，以单击事件为例，即使动画使view看起来离开的原来的位置，但是其单击事件的响应区域仍然是view的原来的位置。
 对于属性动画：Property Animator是通过改变控件内部的属性值来做动画的，以单击事件为例，属性动画view的响应区域随着view的位置变化而变化，即view通过动画编程什么样了
 view就真的变成什么样了。
 一句话总结：视图动画没有改变view本身，你所看到的动画都是假象，是视觉效果
 而属性动画改变了view的本身，你所看到的动画都是真实的，是view改变之后的样子
 */
public class PropertyAnimActivity extends AppCompatActivity {

    @BindView(R.id.bt_value_int)
    Button btValueAnim;
    @BindView(R.id.tv_target)
    TextView tvTarget;
    private ValueAnimator intAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_value_int, R.id.tv_target, R.id.bt_value_float,R.id.bt_int_cancel})
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
                if(intAnimator!=null && intAnimator.isStarted()){
                    intAnimator.cancel();//在当前动画状态停止，而不是回到动画开始状态
//                    intAnimator.isStarted()
                }else {
                    intAnimator.start();//从头开始，而不是从停止的地方开始
                }

                break;
            case R.id.bt_value_float:
                doFloatAnimator();
                break;
            case R.id.tv_target:
                Utils.showToast(this, "我被点击了");
                break;
        }
    }

    /**
     05-23 14:58:08.933 20156-20156/com.yin.yzjcourse E/yin: 0
     05-23 14:58:08.939 20156-20156/com.yin.yzjcourse E/yin: 0
     05-23 14:58:08.954 20156-20156/com.yin.yzjcourse E/yin: 0
     05-23 14:58:08.971 20156-20156/com.yin.yzjcourse E/yin: 1
     05-23 14:58:08.988 20156-20156/com.yin.yzjcourse E/yin: 2
     05-23 14:58:09.005 20156-20156/com.yin.yzjcourse E/yin: 4
     05-23 14:58:09.021 20156-20156/com.yin.yzjcourse E/yin: 6
     .
     .
     .
     .
     05-23 14:58:09.640 20156-20156/com.yin.yzjcourse E/yin: 318
     05-23 14:58:09.657 20156-20156/com.yin.yzjcourse E/yin: 326
     05-23 14:58:09.673 20156-20156/com.yin.yzjcourse E/yin: 334
     05-23 14:58:09.690 20156-20156/com.yin.yzjcourse E/yin: 342
     05-23 14:58:09.707 20156-20156/com.yin.yzjcourse E/yin: 349
     05-23 14:58:09.724 20156-20156/com.yin.yzjcourse E/yin: 356
     05-23 14:58:09.741 20156-20156/com.yin.yzjcourse E/yin: 362
     05-23 14:58:09.757 20156-20156/com.yin.yzjcourse E/yin: 368
     05-23 14:58:09.774 20156-20156/com.yin.yzjcourse E/yin: 373
     05-23 14:58:09.790 20156-20156/com.yin.yzjcourse E/yin: 378
     05-23 14:58:09.807 20156-20156/com.yin.yzjcourse E/yin: 383
     05-23 14:58:09.824 20156-20156/com.yin.yzjcourse E/yin: 387
     05-23 14:58:09.840 20156-20156/com.yin.yzjcourse E/yin: 390
     05-23 14:58:09.857 20156-20156/com.yin.yzjcourse E/yin: 393
     05-23 14:58:09.874 20156-20156/com.yin.yzjcourse E/yin: 395
     05-23 14:58:09.891 20156-20156/com.yin.yzjcourse E/yin: 397
     05-23 14:58:09.907 20156-20156/com.yin.yzjcourse E/yin: 399
     05-23 14:58:09.924 20156-20156/com.yin.yzjcourse E/yin: 399
     05-23 14:58:09.941 20156-20156/com.yin.yzjcourse E/yin: 400

     (0,400)的输出日志
     动画时间短(setDuration)则0-400可能有部分数字没有执行
     如果时间长，可能有部分数字执行多次

     AnimatorListener监听log
     05-23 15:33:14.673 13642-13642/com.yin.yzjcourse E/yin: start
     05-23 15:33:20.701 13642-13642/com.yin.yzjcourse E/yin: repeat
     05-23 15:33:26.699 13642-13642/com.yin.yzjcourse E/yin: repeat
     05-23 15:33:32.697 13642-13642/com.yin.yzjcourse E/yin: repeat
     05-23 15:33:38.695 13642-13642/com.yin.yzjcourse E/yin: repeat //如果不取消就一直repeat
     05-23 15:33:41.121 13642-13642/com.yin.yzjcourse E/yin: cancel //点击取消按钮后
     05-23 15:33:41.121 13642-13642/com.yin.yzjcourse E/yin: end
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
        ValueAnimator floatAnimator = ValueAnimator.ofFloat(0f,600f,100f,400f);//可变参数，参数越多可操控的动画就可以约复杂
        floatAnimator.setDuration(5000);
        floatAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float curFloat = (Float) animation.getAnimatedValue();//返回Object类型
                int curInt = curFloat.intValue();
                tvTarget.layout(curInt,curInt,curInt+tvTarget.getWidth(),curInt+tvTarget.getHeight());
            }
        });
        floatAnimator.start();
    }
}

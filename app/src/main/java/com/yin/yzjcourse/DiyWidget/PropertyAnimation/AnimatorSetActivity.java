package com.yin.yzjcourse.DiyWidget.PropertyAnimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimatorSetActivity extends AppCompatActivity {

    @BindView(R.id.bt_sequentially)
    Button btSequentially;
    @BindView(R.id.bt_together)
    Button btTogether;
    @BindView(R.id.tv_target_1)
    TextView tvTarget1;
    @BindView(R.id.tv_target_2)
    TextView tvTarget2;
    private AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_sequentially, R.id.bt_together,R.id.bt_builder_play_with,R.id.bt_before,
    R.id.bt_after_anim,R.id.bt_after_time,R.id.bt_set_listener,R.id.bt_cover,R.id.bt_set_start_delay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sequentially:
                doSequentiallyAnim();
                break;
            case R.id.bt_together:
                doTogetherAnim();
                break;
            case R.id.bt_builder_play_with:
                doBuilderPlayWithAnim();
                break;
            case R.id.bt_before:
                doBuilderBeforeAnim();
                break;
            case R.id.bt_after_anim:
                doBuilderAfterAnim();
                break;
            case R.id.bt_after_time:
                doBuilderAfterTime();
                break;
            case R.id.bt_set_listener:
                doListener();
                break;
            case R.id.bt_cover:
                doCover();
                break;
            case R.id.bt_set_start_delay:
                doStartDelay();
                break;
        }
    }

    /**
     * playSequentially表示所有动画依次播放
     * 激活一个动画之后，动画之后的操作就是动画自己来负责了，这个动画结束之后，再激活下一个动画。如果上一个动画没有结束，那下一个动画就永远也不会被激活
     * 总之：playTogether和playSequentially只是负责指定什么时候开始动画，不干涉动画自己的运行过程。
     * 换言之：playTogether和playSequentially只是赛马场上的每个赛道的门，门打开以后，赛道上的那匹马怎么跑跟它没什么关系。
     */
    private void doSequentiallyAnim() {
        ObjectAnimator tv1BgAnimator = ObjectAnimator.ofInt(tvTarget1, "BackgroundColor",  0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator tv1TranslateY = ObjectAnimator.ofFloat(tvTarget1, "translationY", 0, 300, 0);
        ObjectAnimator tv2TranslateY = ObjectAnimator.ofFloat(tvTarget2, "translationY", 0, 400, 0);
        List<Animator> animList = new ArrayList<>();
        animList.add(tv1BgAnimator);
        animList.add(tv1TranslateY);
        animList.add(tv2TranslateY);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(tv1BgAnimator,tv1TranslateY,tv2TranslateY);//通过可变参数的方式
//        animatorSet.playSequentially(animList);//通过传入动画集合的方式
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    /**
     * playTogether：同一时刻允许播放所有动画
     * playTogether只是一个时间点上的一起开始，对于开始后，各个动画怎么操作就是他们自己的事了，至于各个动画结不结束也是他们自已的事了
     * 类似于马场的赛马
     * 总之：playTogether和playSequentially只是负责指定什么时候开始动画，不干涉动画自己的运行过程。
     * 换言之：playTogether和playSequentially只是赛马场上的每个赛道的门，门打开以后，赛道上的那匹马怎么跑跟它没什么关系。
     */
    private void doTogetherAnim() {
        ObjectAnimator tv1BgAnimator = ObjectAnimator.ofInt(tvTarget1, "BackgroundColor",  0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator tv1TranslateY = ObjectAnimator.ofFloat(tvTarget1, "translationY", 0, 400, 0);
        ObjectAnimator tv2TranslateY = ObjectAnimator.ofFloat(tvTarget2, "translationY", 0, 200, 0);
        List<Animator> animList = new ArrayList<>();
        animList.add(tv1BgAnimator);
        animList.add(tv1TranslateY);
        animList.add(tv2TranslateY);
        AnimatorSet animatorSet = new AnimatorSet();
        //通过可变参数的方式
        animatorSet.playTogether(tv1BgAnimator,tv1TranslateY,tv2TranslateY);
        //通过动画集合的方式
//        animatorSet.playTogether(animList);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    /**
     * 播放tv1BgAnimator的同时，同时播放tv1TranslateY
     */
    private void doBuilderPlayWithAnim() {
        ObjectAnimator tv1BgAnimator = ObjectAnimator.ofInt(tvTarget1, "BackgroundColor",  0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator tv1TranslateY = ObjectAnimator.ofFloat(tvTarget2, "translationY", 0, 400, 0);

        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(tv1BgAnimator);//play是生成Builder对象的唯一途径
        builder.with(tv1TranslateY);
        animatorSet.start();
    }

    /**
     * 先执行foreAnim后执行latterAnim
     */
    private void doBuilderBeforeAnim() {
        ObjectAnimator foreAnim = ObjectAnimator.ofInt(tvTarget1, "BackgroundColor",  0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator latterAnim = ObjectAnimator.ofFloat(tvTarget2, "translationY", 0, 400, 0);

        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(foreAnim).before(latterAnim);
        animatorSet.setDuration(3000);
        animatorSet.start();
    }

    /**
     * 先执行foreAnim后执行latterAnim
     */
    private void doBuilderAfterAnim() {
        ObjectAnimator latterAnim = ObjectAnimator.ofInt(tvTarget1, "BackgroundColor",  0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator foreAnim = ObjectAnimator.ofFloat(tvTarget2, "translationY", 0, 400, 0);

        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(latterAnim).after(foreAnim);
        animatorSet.setDuration(3000);
        animatorSet.start();
    }

    /**
     * after:一段时间后执行动画
     */
    private void doBuilderAfterTime() {
        ObjectAnimator latterAnim = ObjectAnimator.ofInt(tvTarget1, "BackgroundColor",  0xffff00ff, 0xffffff00, 0xffff00ff);

        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(latterAnim).after(3000);
        animatorSet.setDuration(3000);
        animatorSet.start();
    }

    /**
     * 1、AnimatorSet的监听函数也只是用来监听AnimatorSet的状态的，与其中的动画无关；
     2、AnimatorSet中没有设置循环的函数，所以AnimatorSet监听器中永远无法运行到onAnimationRepeat()中！
     */
    private void doListener() {
        ObjectAnimator tv1BgAnimator = ObjectAnimator.ofInt(tvTarget1, "BackgroundColor",  0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator tv1TranslateY = ObjectAnimator.ofFloat(tvTarget1, "translationY", 0, 400, 0);
        ObjectAnimator tv2TranslateY = ObjectAnimator.ofFloat(tvTarget2, "translationY", 0, 200, 0);
        animatorSet = new AnimatorSet();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                DLog.eLog("执行onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                DLog.eLog("执行onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                DLog.eLog("执行onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                /**
                 * 当AnimatorSet重复时调用，由于AnimatorSet没有设置repeat的函数，所以这个方法永远不会被调用
                 */
                DLog.eLog("执行onAnimationRepeat");
            }
        });
        animatorSet.playTogether(tv1BgAnimator,tv1TranslateY,tv2TranslateY);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    /**
     * 在AnimatorSet中设置以后，会覆盖单个ObjectAnimator中的设置；即如果AnimatorSet中没有设置，
     * 那么就以ObjectAnimator中的设置为准。如果AnimatorSet中设置以后，ObjectAnimator中的设置就会无效。
     */
    private void doCover() {
//设置单次动画时长
//        public AnimatorSet setDuration(long duration);
//设置加速器
//        public void setInterpolator(TimeInterpolator interpolator)
//设置ObjectAnimator动画目标控件
//        public void setTarget(Object target)
    }

    /**
     * AnimatorSet的延时是仅针对性的延长AnimatorSet激活时间的，对单个动画的延时设置没有影响。
     * - AnimatorSet真正激活延时 = AnimatorSet.startDelay+第一个动画.startDelay
     - 在AnimatorSet激活之后，第一个动画绝对是会开始运行的，后面的动画则根据自己是否延时自行处理。
     */
    private void doStartDelay() {

    }
}

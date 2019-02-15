package com.yin.yzjcourse.DiyWidget.PropertyAnimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimatorSetActivity extends BaseActivity {

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
     playSequentially表示所有动画依次播放，一个动画完成才能开始下一个。类似赛马，1:00整1号马开门，1号马跑完后2号马的门才能开以此类推，
     如果1号马一直跑不停了(无限循环)则2号马则会一直关在门后面。
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
     playTogether表示所有动画一起开始播放，注意只是在某一个时间点同时开始所有动画，至于开始之后各个动画怎么样AnimatorSet无权干涉，
     由各个动画决定。类似赛马，1:00整准时开门，至于开门后各个马什么时候跑，跑不跑(延时)，跑多久，怎么跑都管不着。
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
     播放tv1BgAnimator的同时，同时播放tv1TranslateY
     play是生成Builder对象的唯一途径，所以要使用Builder你肯定会先调用play方法。
     with:和前面动画一起执行：animatorSet.play(a1).with(a2);
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
     先执行foreAnim后执行latterAnim
     执行前面的动画后才执行该动画：animatorSet.play(a1).before(a2);
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
     先执行foreAnim后执行latterAnim
     执行先执行这个动画再执行前面动画：animatorSet.play(a1).after(a2);
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
     延迟n毫秒之后执行动画
     animatorSet.play(a1).after(1000);
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
     AnimatorSet的延时是仅针对性的延长AnimatorSet激活时间的，对单个动画的延时设置没有影响。就像延时打开赛马跑道的门，跟马跑的动画无关。

     AnimatorSet真正激活延时 = AnimatorSet.startDelay+第一个动画.startDelay
     这里的第一个动画可以是play(a1)里的动画也可以是playTogether(Animator... items)的第一个参数或
     playTogether(Collection<Animator> items)items的第0个元素。

     怎么理解：
     animatorSet1.setStartDelay(1000)
     a1.setStartDelay(2000)
     animatorSet1.play(a1).with(a2);
     则animatorSet1真正的延时=1000+2000
     因此动画集合开始时间是这个真正的延时完成的时间，开始后第一个动画的延时则不再有而是直接执行其动画，
     因为已经被animatorSet1用作自己的延时了，其它的动画则不受影响，动画开始后该怎么办还是怎么办。

     例如加入a2也有延时2000,则animatorSet1的真正的延时结束后，a1直接作动画同时a2等待自己的延时完成后再动画。

     */
    private void doStartDelay() {
//        void setStartDelay(long startDelay)
    }
}

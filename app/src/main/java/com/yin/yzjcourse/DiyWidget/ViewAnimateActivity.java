package com.yin.yzjcourse.DiyWidget;

import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

/**
 * View的animate()其实就用到了ViewPropertyAnimator，ViewPropertyAnimator只是一个包装类并不继承自Animator，其内部通过ValueAnimator实现。
 * ValueAnimator可以通过简洁的链式调用的方式实现属性动画，且默认同时执行多个动画。
 *
 * ViewPropertyAnimator、ObjectAnimator、ValueAnimator 这三种 Animator，
 * 它们其实是一种递进的关系：从左到右依次变得更加难用，也更加灵活。
 *
 * 它们的性能是一样的，因为 ViewPropertyAnimator 和 ObjectAnimator 的内部实现其实都是 ValueAnimator，ObjectAnimator 更是本来就是 ValueAnimator 的子类，它们三个的性能并没有差别。
 *
 * 它们的差别只是使用的便捷性以及功能的灵活性。
 * 所以在实际使用时候的选择，只要遵循一个原则就行：尽量用简单的。
 * 能用 View.animate() 实现就不用 ObjectAnimator，
 * 能用 ObjectAnimator 就不用 ValueAnimator。
 * ————————————————
 * 原文链接：https://blog.csdn.net/sinat_31057219/article/details/77717187
 */
public class ViewAnimateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animate);
        //用法如下
        /*
        View view = new View(this);
        view.animate()//获取ViewPropertyAnimator对象
                //动画持续时间
                .setDuration(5000)

                //透明度
                .alpha(0)
                .alphaBy(0)

                //旋转
                .rotation(360)
                .rotationBy(360)
                .rotationX(360)
                .rotationXBy(360)
                .rotationY(360)
                .rotationYBy(360)

                //缩放
                .scaleX(1)
                .scaleXBy(1)
                .scaleY(1)
                .scaleYBy(1)

                //平移
                .translationX(100)
                .translationXBy(100)
                .translationY(100)
                .translationYBy(100)
                .translationZ(100)
                .translationZBy(100)

                //更改在屏幕上的坐标
                .x(10)
                .xBy(10)
                .y(10)
                .yBy(10)
                .z(10)
                .zBy(10)

                //插值器
                .setInterpolator(new BounceInterpolator())//回弹
                .setInterpolator(new AccelerateDecelerateInterpolator())//加速再减速
                .setInterpolator(new AccelerateInterpolator())//加速
                .setInterpolator(new DecelerateInterpolator())//减速
                .setInterpolator(new LinearInterpolator())//线性

                //动画延迟
                .setStartDelay(1000)

                //是否开启硬件加速
                .withLayer()

                //监听
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Log.i("MainActivity", "run: onAnimationStart");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Log.i("MainActivity", "run: onAnimationEnd");
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        Log.i("MainActivity", "run: onAnimationCancel");
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        Log.i("MainActivity", "run: onAnimationRepeat");
                    }
                })

                .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Log.i("MainActivity", "run: onAnimationUpdate==");
                    }
                })

                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("MainActivity", "run: end");
                    }
                })
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("MainActivity", "run: start");
                    }
                })

                .start();
        */



        //执行顺序如下
        /*
        withStartAction（Runnable runnable）
→
        onAnimationStart(Animator animation)
→
        onAnimationUpdate（ValueAnimator animation）//动画过程中一直调用
→
        onAnimationEnd(Animator animation)
→
        withEndAction（Runnable runnable）
        */

        /*
        有By结尾和没By结尾的区别：
        每个动画都有一个By的后缀的方法。加上By的意思是，继续动画这么多数值。不加By的意思是动画到这个数值。
        有By：变化偏移
        无By：变化到
         */
    }
}

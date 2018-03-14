package com.yin.yzjcourse.DiyWidget;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

/**
  参考百度脑图

 APPEARING —— 元素在容器中出现时所定义的动画。
 DISAPPEARING —— 元素在容器中消失时所定义的动画。
 CHANGE_APPEARING —— 由于容器中要显现一个新的元素，其它需要变化的元素所应用的动画
 CHANGE_DISAPPEARING —— 当容器中某个元素消失，其它需要变化的元素所应用的动画

 如下是死规定：
 1:必须用ObjectAnimator动画
 2：对于CHANGE_APPEARING和CHANGE_DISAPPEARING：
    a:必须使用ObjectAnimator的ofPropertyValuesHolder()方法所构造的动画才会有效果;
    b:在构造PropertyValuesHolder动画时，”left”、”top”属性的变动是必写的。如果不需要变动，则直接写为：
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left",0,0);  //参数是0,0代表left不动
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top",0,0); //参数是0,0代表top不动
    c:在构造PropertyValuesHolder时，所使用的ofInt,ofFloat中的参数值，第一个值和最后一个值必须相同，
      不然此属性所对应的的动画将被放弃，在此属性值上将不会有效果.
        例如：PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left",0,100,0);这里ofInt(“left”,0,100,0)第一个值
        和最后一个值都是0，所以这里会有效果的，如果我们改为ofInt(“left”,0,100);那么由于首尾值不一致，则将被视为无效参数，将不会有效果！
    d:在构造PropertyValuesHolder时，所使用的ofInt,ofFloat中，如果所有参数值都相同，也将不会有动画效果.
        例如：PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left",100,100);在这条语句中，虽然首尾一致，
        但由于全程参数值相同，所以left属性上的这个动画会被放弃，在left属性上也不会应用上任何动画。
 */
public class LayoutTransitionActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout layoutTransitionGroup;
    private LayoutTransition mTransitioner;
    private int i = 0;

    /**
     * 不能点太快,不然会报openGL内存溢出错误
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_transition);

        layoutTransitionGroup = (LinearLayout) findViewById(R.id.layoutTransitionGroup);
        findViewById(R.id.add_btn).setOnClickListener(this);
        findViewById(R.id.remove_btn).setOnClickListener(this);
        //第1步：创建LayoutTransition对象
        mTransitioner = new LayoutTransition();
        //第2步：根据需要创建APPEARING、APPEARING、CHANGE_APPEARING、CHANGE_DISAPPEARING的一个或多个ObjectAnimator动画
        //a：新加入的那个元素的动画
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationY", 0f, 360f, 0f);
        mTransitioner.setAnimator(LayoutTransition.APPEARING, animIn);

        //b：消失的那个元素的动画
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotation", 0f, 90f, 0f);
        mTransitioner.setAnimator(LayoutTransition.DISAPPEARING, animOut);

        //c：新加入一个元素，其它已存在的元素的动画
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 100, 0);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 1, 1);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("ScaleX", 1f, 9f, 1f);
        Animator changeAppearAnimator
                = ObjectAnimator.ofPropertyValuesHolder(layoutTransitionGroup, pvhLeft, pvhTop, pvhScaleX);//第一个参数也可以是this当前activity，为什么？
        mTransitioner.setAnimator(LayoutTransition.CHANGE_APPEARING, changeAppearAnimator);

        //d：消失一个元素，其它剩余的元素的动画
        PropertyValuesHolder outLeft = PropertyValuesHolder.ofInt("left", 0, 0);
        PropertyValuesHolder outTop = PropertyValuesHolder.ofInt("top", 0, 0);
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
        PropertyValuesHolder mPropertyValuesHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10);
        ObjectAnimator mObjectAnimatorChangeDisAppearing = ObjectAnimator.ofPropertyValuesHolder(this, outLeft, outTop, mPropertyValuesHolder);//第一个参数也可以是layoutTransitionGroup
        mTransitioner.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, mObjectAnimatorChangeDisAppearing);

        //设置单个item间的动画间隔：各个item动画的依次时间间隔
        mTransitioner.setStagger(LayoutTransition.CHANGE_APPEARING, 30);


        mTransitioner.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {

                Log.d("qijian", "start:" + "transitionType:" + transitionType + "count:" + container.getChildCount() + "view:" + view.getClass().getName());
            }

            @Override
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                Log.d("qijian", "end:" + "transitionType:" + transitionType + "count:" + container.getChildCount() + "view:" + view.getClass().getName());
            }
        });
        //第3步：给ViewGroup设置LayoutTransition
        layoutTransitionGroup.setLayoutTransition(mTransitioner);
    }


    private void addButtonView() {
        i++;
        Button button = new Button(this);
        button.setText("button" + i);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        layoutTransitionGroup.addView(button, 0);
    }

    private void removeButtonView() {
        if (i > 0) {
            layoutTransitionGroup.removeViewAt(0);
        }
        i--;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_btn) {
            addButtonView();
        }
        if (v.getId() == R.id.remove_btn) {
            removeButtonView();
        }

    }
}
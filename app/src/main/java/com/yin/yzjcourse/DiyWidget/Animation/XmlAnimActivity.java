package com.yin.yzjcourse.DiyWidget.Animation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by think on 2017/5/18.
 */

public class XmlAnimActivity extends AppCompatActivity {
    @BindView(R.id.bt_alpha)
    Button btAlpha;
    @BindView(R.id.bt_scale)
    Button btScale;
    @BindView(R.id.bt_rotate)
    Button btRotate;
    @BindView(R.id.bt_trans)
    Button btTrans;
    @BindView(R.id.view_targent)
    View viewTargent;
    @BindView(R.id.bt_set)
    Button btSet;
    @BindView(R.id.bt_interpolator)
    Button btInterpolator;
    @BindView(R.id.bt_code)
    Button btCode;
    @BindView(R.id.bt_src_frame_anim)
    Button btSrcFrameAnim;
    @BindView(R.id.bt_bg_frame_anim)
    Button btBgFrameAnim;
    @BindView(R.id.bt_code_frame_anim)
    Button btCodeFrameAnim;
    @BindView(R.id.iv_src_frame_anim)
    ImageView ivSrcFrameAnim;
    @BindView(R.id.view_bg_frame_anim)
    View viewBgFrameAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_anim_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_alpha, R.id.bt_scale, R.id.bt_rotate, R.id.bt_trans, R.id.bt_set, R.id.bt_interpolator, R.id.bt_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_scale:
                viewTargent.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scaleanim));//缩放
                break;
            case R.id.bt_alpha:
                viewTargent.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alphaanim));//透明度
                break;
            case R.id.bt_rotate:
                viewTargent.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotateanim));//旋转
                break;
            case R.id.bt_trans:
                viewTargent.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translateanim));//平移
                break;
            case R.id.bt_set:
                viewTargent.startAnimation(AnimationUtils.loadAnimation(this, R.anim.setanim));//动画集合
                break;
            case R.id.bt_interpolator:
                startActivity(new Intent(this, FourAnimInterpolatorActivity.class));
                break;
            case R.id.bt_code:
                Utils.showToast(this, "代码就在这个activity里看看就行了");
                everyAnim();
                break;
        }
    }

    private void everyAnim() {
        /*
        ScaleAnimation有下面几个构造函数：
        ScaleAnimation(Context context, AttributeSet attrs)  从XML文件加载动画，基本用不到
        ScaleAnimation(float fromX, float toX, float fromY, float toY)
        ScaleAnimation(float fromX, float toX, float fromY, float toY, float pivotX, float pivotY)
        ScaleAnimation(float fromX, float toX, float fromY, float toY, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
        在标签属性android:pivotX中有三种取值，数，百分数，百分数p；体现在构造函数中，就是最后一个构造函数
        的pivotXType,它的取值有三个，Animation.ABSOLUTE、Animation.RELATIVE_TO_SELF和Animation.RELATIVE_TO_PARENT；

        举例：
        */
        ScaleAnimation scaleAnim = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setDuration(700);

        /*
        同样alpha标签自有的属性有：
        android:fromAlpha   动画开始的透明度，从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
        android:toAlpha       动画结束时的透明度，也是从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
        所对应的构造函数为：
        AlphaAnimation(Context context, AttributeSet attrs)  同样，从本地XML加载动画，基本不用
        AlphaAnimation(float fromAlpha, float toAlpha)
        这里只剩最后一个构造函数，难度不大，下面举个例子说明下用法。
        */
        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.1f);
        alphaAnim.setDuration(3000);
        alphaAnim.setFillBefore(true);
        /*
        Rotate标签所具有的XML属性有：

android:fromDegrees     开始旋转的角度位置，正值代表顺时针方向度数，负值代码逆时针方向度数
android:toDegrees         结束时旋转到的角度位置，正值代表顺时针方向度数，负值代码逆时针方向度数
android:pivotX               缩放起点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，具体意义已在scale标签中讲述，这里就不再重讲
android:pivotY               缩放起点Y轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p
对应的构造函数有：
RotateAnimation(Context context, AttributeSet attrs)　　从本地XML文档加载动画，同样，基本不用
RotateAnimation(float fromDegrees, float toDegrees)
RotateAnimation(float fromDegrees, float toDegrees, float pivotX, float pivotY)
RotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
RotateAnimation跟ScaleAnimation差不多，关键问题同样是pivotXType和pivotYType的选择，同样有三个取值：Animation.ABSOLUTE、Animation.RELATIVE_TO_SELF和Animation.RELATIVE_TO_PARENT；
         */
        RotateAnimation rotateAnim = new RotateAnimation(0, -650, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(3000);
        rotateAnim.setFillAfter(true);



        /*
        translate标签所具有的属性为：
android:fromXDelta     起始点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，具体意义已在scale标签中讲述，这里就不再重讲
android:fromYDelta    起始点Y轴从标，可以是数值、百分数、百分数p 三种样式；
android:toXDelta         结束点X轴坐标
android:toYDelta        结束点Y轴坐标
这些属性所对应的构造函数为：
TranslateAnimation(Context context, AttributeSet attrs)  同样，基本不用
TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)
TranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue, int fromYType, float fromYValue, int toYType, float toYValue)
由于fromXDelta、fromYDelta、toXDelta、toYDelta这三个属性都具有三种状态，所以在构造函数中，最理想的状态就是第三个构造函数
，能够指定每个值的类型，第二个构造函数：TranslateAnimation (float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)使用是绝对数值。
只有最后一个构造函数可以指定百分数和相对父控件的百分数。
         */
        TranslateAnimation translateAnim = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, -80,
                Animation.ABSOLUTE, 0, Animation.ABSOLUTE, -80);
        translateAnim.setDuration(2000);
        translateAnim.setFillBefore(true);
        /*
        AnimationSet类对应set标签，定义动作类的集合
它自己是没有XML属性的，所以我们直接说它的构造函数：
AnimationSet(Context context, AttributeSet attrs)  同样，基本不用
AnimationSet(boolean shareInterpolator)  shareInterpolator取值true或false，取true时，指在AnimationSet中定义一个插值器（interpolater），它下面的所有动画共同。如果设为false，则表示它下面的动画自己定义各自的插值器。
增加动画的函数为：（更多函数，请参看SDK文档）

public void addAnimation (Animation a)
         */
        alphaAnim = new AlphaAnimation(1.0f, 0.1f);
        scaleAnim = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        AnimationSet setAnim = new AnimationSet(true);
        setAnim.addAnimation(alphaAnim);
        setAnim.addAnimation(scaleAnim);
        setAnim.addAnimation(rotateAnim);

        setAnim.setDuration(3000);
        setAnim.setFillAfter(true);


        /*
        差之器
         */
        ScaleAnimation interpolateScaleAnim = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        interpolateScaleAnim.setInterpolator(new BounceInterpolator());
        interpolateScaleAnim.setDuration(3000);
    }

    /**
     * 逐帧动画（Frame-by-frame Animations）从字面上理解就是一帧挨着一帧的播放图片，就像放电影一样。
     * 逐帧动画的动画资源放在res/drawable目录下
     * 逐帧动画的效果要在onWindowFocusChanged之后才可能产生
     * 逐帧动画只能实现比较小的动画效果，如果复杂而且帧数比较多的动画不太建议使用逐帧动画，一方面是因
     * 为会造成OOM，另一方面会显得很卡
     * @param view
     */
    @OnClick({R.id.bt_src_frame_anim, R.id.bt_bg_frame_anim, R.id.bt_code_frame_anim})
    public void onClickFrame(View view) {
        switch (view.getId()) {
            case R.id.bt_src_frame_anim:
                //逐帧动画可用于imageView的src
                ivSrcFrameAnim.setImageResource(R.drawable.frame_anim_list);
                AnimationDrawable animationDrawable = (AnimationDrawable) ivSrcFrameAnim.getDrawable();
                if(animationDrawable.isRunning()){
                    animationDrawable.stop();
                }else {
                    animationDrawable.start();
                }
                break;
            case R.id.bt_bg_frame_anim:
                //逐帧动画也可用于View的background
                viewBgFrameAnim.setBackgroundResource(R.drawable.frame_anim_list);
                AnimationDrawable animationDrawable1 = (AnimationDrawable) viewBgFrameAnim.getBackground();
                if(animationDrawable1.isRunning()){
                    animationDrawable1.stop();
                }else {
                    animationDrawable1.start();
                }
                break;
            case R.id.bt_code_frame_anim:
                codeShowFrameAnim();
                break;
        }
    }

    private void codeShowFrameAnim() {
        AnimationDrawable anim = new AnimationDrawable();
        for (int i = 1; i <= 6; i++) {
            int id = getResources().getIdentifier("frame_anim_" + i, "mipmap", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            anim.addFrame(drawable, 200);
        }
        // false为循环播放，true为仅播放一次
        anim.setOneShot(false);
        ivSrcFrameAnim.setImageDrawable(anim);
        anim.start();
    }
}

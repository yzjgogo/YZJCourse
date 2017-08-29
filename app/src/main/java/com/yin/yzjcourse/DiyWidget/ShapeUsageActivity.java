package com.yin.yzjcourse.DiyWidget;

import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

/**
 渐变色gradient标签个属性用法
 android:type=["linear" | "radial" | "sweep"]    //共有3中渐变类型，线性渐变（默认）/放射渐变/扫描式渐变
 android:angle="integer"     //渐变角度，必须为45的倍数，且只对linear有效，参考图片：gradient_angle.png
 android:centerX="float"     //渐变中心X的相当位置(控件的宽的位置)，范围为0～1，对linear,radial,sweep都有效，效果各不相同
 android:centerY="float"     //参考centerX
 android:startColor="color"   //渐变开始点的颜色
 android:centerColor="color"  //渐变中间点的颜色，在开始与结束点之间
 android:endColor="color"    //渐变结束点的颜色
 android:gradientRadius="float"  //渐变的半径，只有当渐变类型为radial时才能使用,如果不使用则报错
 android:useLevel=["true" | "false"] />  //useLevel属性通常不使用。该属性用于指定是否将该shape当成一个LevelListDrawable来使用，默认值为false。
 */
public class ShapeUsageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_usage);
    }
}

package com.yin.yzjcourse.MathAbout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

/**
 * 弧度定义：弧长等于半径的弧，其所对的圆心角为1弧度。(即两条射线从圆心向圆周射出，形成一个夹角和夹角正对的一段弧。
 * 当这段弧长正好等于圆的半径时，两条射线的夹角的弧度为1)
 * 根据定义：圆的一周的弧度是2π*r/2 = 2π弧度 = 360度角，所以π弧度 = 180度角
 * 因此1弧度约为57.3°
 */
public class MathAboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_about);
        allMethods();
    }

    private void allMethods() {
        //角度转换为弧度
        DLog.eLog("180角度转换为弧度："+Math.toRadians(180));
        DLog.eLog("90角度转换为弧度："+Math.toRadians(90));
        //弧度转换为角度
        DLog.eLog("π弧度转换为角度:"+Math.toDegrees(Math.PI));
        DLog.eLog("π/2弧度转换为角度:"+Math.toDegrees(Math.PI/2));
        //求tan，单位是弧度
        DLog.eLog("60度的tan值"+Math.tan(Math.toRadians(60)));
        DLog.eLog("π/3的tan值"+Math.tan(Math.PI/3));
        //求sin，单位是弧度
        DLog.eLog("60度的tan值"+Math.sin(Math.toRadians(60)));
        DLog.eLog("π/3的tan值"+Math.sin(Math.PI/3));
        //求cos，单位是弧度
        DLog.eLog("60度的tan值"+Math.cos(Math.toRadians(60)));
        DLog.eLog("π/3的tan值"+Math.cos(Math.PI/3));
        //开根号
        DLog.eLog("根号3的值："+Math.sqrt(3));
        DLog.eLog("Math.sqrt(3)/3的值："+Math.sqrt(3)/3);
        //根据tan值求弧度
        DLog.eLog("求Math.sqrt(3)的弧度："+Math.atan(Math.sqrt(3)));
        DLog.eLog("Math.sqrt(3)/3的弧度："+Math.atan(Math.sqrt(3)/3));
    }
}

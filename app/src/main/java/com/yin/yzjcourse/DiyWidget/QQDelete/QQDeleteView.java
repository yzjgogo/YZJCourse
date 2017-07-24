package com.yin.yzjcourse.DiyWidget.QQDelete;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yin.yzjcourse.R;

/**
 * Created by think on 2017/7/24.
 */

public class QQDeleteView extends FrameLayout {
    private PointF startPoint;
    private PointF curPoint;
    private Paint paint;
    private boolean isPressDown = false;
    private float mRadius = DEF_RADIUS;
    private static final float DEF_RADIUS = 40;
    private Path path;
    private TextView tv;
    private ImageView iv;
    private boolean isAnimStart = false;
    public QQDeleteView(@NonNull Context context) {
        super(context);
        initView();
    }

    public QQDeleteView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public QQDeleteView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public QQDeleteView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        startPoint = new PointF(100.0f,100.f);
        curPoint = new PointF();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        path = new Path();
        //添加显示文字的textview
        tv = new TextView(getContext());
        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(layoutParams);
        tv.setText("87");
        tv.setGravity(Gravity.CENTER);

        //爆破动画
        iv = new ImageView(getContext());
        iv.setLayoutParams(layoutParams);
        iv.setImageResource(R.drawable.tip_anim);
        iv.setVisibility(INVISIBLE);

        addView(tv);
        addView(iv);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
            canvas.drawCircle(startPoint.x,startPoint.y,mRadius,paint);
            if (isPressDown) {
                calculatePath();
                canvas.drawCircle(curPoint.x,curPoint.y,DEF_RADIUS,paint);
                canvas.drawPath(path,paint);
                tv.setX(curPoint.x-tv.getWidth()/2);
                tv.setY(curPoint.y-tv.getHeight()/2);
            }else {
                tv.setX(startPoint.x-tv.getWidth()/2);
                tv.setY(startPoint.y-tv.getHeight()/2);
            }
        super.dispatchDraw(canvas);
    }
    private void calculatePath(){
        //参考图片：qq_delete_pic.jpg
        double rad = Math.atan((curPoint.y-startPoint.y)/(curPoint.x-startPoint.x));
        float x1 = (float) (startPoint.x+Math.sin(rad)*mRadius);
        float y1 = (float) (startPoint.y-Math.cos(rad)*mRadius);

        float x2 = (float) (curPoint.x+Math.sin(rad)*DEF_RADIUS);
        float y2 = (float) (curPoint.y-Math.cos(rad)*DEF_RADIUS);

        float x3 = (float) (curPoint.x-Math.sin(rad)*DEF_RADIUS);
        float y3 = (float) (curPoint.y+Math.cos(rad)*DEF_RADIUS);

        float x4 = (float) (startPoint.x-Math.sin(rad)*mRadius);
        float y4 = (float) (startPoint.y+Math.cos(rad)*mRadius);

        float x5 = (startPoint.x+curPoint.x)/2;
        float y5 = (startPoint.y+curPoint.y)/2;
        //原来的圆的半径逐渐变小
        float distance = (float) Math.sqrt(Math.pow(Math.abs(curPoint.x-startPoint.x),2)+Math.pow(Math.abs(curPoint.y-startPoint.y),2));
        mRadius = (DEF_RADIUS - distance/10);
        path.reset();
        if(mRadius > 1){//原来的圆消失时不画贝赛尔曲线
            path.moveTo(x1,y1);
            path.quadTo(x5,y5,x2,y2);
            path.lineTo(x3,y3);
            path.quadTo(x5,y5,x4,y4);
            path.lineTo(x1,y1);//不用也可以，已经闭合了
        }else {
            //iv在手指抬起的位置显示并爆破，看ACTION_UP
            isAnimStart = true;
            iv.setX(curPoint.x-iv.getWidth()/2);
            iv.setY(curPoint.y-iv.getHeight()/2);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Rect rect = new Rect();
                int[] location = new int[2];
                tv.getLocationOnScreen(location);
                rect.left = location[0];
                rect.top = location[1];
                rect.right = tv.getWidth()+location[0];
                rect.bottom = tv.getHeight()+location[1];
                //在整个屏幕是否包含,用getLocationInWindow方法不可以
                if (rect.contains((int)event.getRawX(),(int)event.getRawY())) {//如果手指点到矩形区域则可拖拽
                    isPressDown = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                isPressDown = false;
                explodeAnim();
                break;
        }
        curPoint.set(event.getX(),event.getY());
        postInvalidate();
        return true;
    }

    private void explodeAnim() {
        if (isAnimStart) {
            iv.setVisibility(VISIBLE);
            tv.setVisibility(GONE);
            ((AnimationDrawable)iv.getDrawable()).start();
        }
    }
}

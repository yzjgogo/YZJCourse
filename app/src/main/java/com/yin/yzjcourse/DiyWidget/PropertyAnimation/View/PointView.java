package com.yin.yzjcourse.DiyWidget.PropertyAnimation.View;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.yin.yzjcourse.DiyWidget.bean.Point;

/**
 * Created by think on 2017/5/25.
 */

public class PointView extends View {
    private Point currentPoint = new Point(100);

    public PointView(Context context) {
        super(context);
    }

    public PointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPoint != null) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            canvas.drawCircle(300, 300, currentPoint.getRadius(), paint);
        }
    }
    public void setPointRadius(int radius){
        currentPoint.setRadius(radius);
        invalidate();
    }
    public int getPointRadius(){
        return currentPoint.getRadius();
    }

    public void doAnimator() {
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), new Point(20), new Point(300));
        animator.setDuration(3000);
        animator.setInterpolator(new BounceInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
//                postInvalidate();//在工作线程中调用
                invalidate();//在UI线程中调用，比如在修改某个view的显示时，调用invalidate()才能看到重新绘制的界面
            }
        });
        animator.start();
    }

    class PointEvaluator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            int startRadius = startValue.getRadius();
            int endRadius = endValue.getRadius();
            return new Point((int) (startRadius + fraction * (endRadius - startRadius)));
        }
    }
}

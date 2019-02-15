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
                //invalidate()必须在主线程中调用，而postInvalidate()内部是由Handler的消息机制实现的，所以在任何线程都可以调用，
                // 但实时性没有invalidate()强。所以一般为了保险起见，一般是使用postInvalidate()来刷新界面。
//                postInvalidate();
                invalidate();
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

package com.yin.yzjcourse.DiyWidget.PieGraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by think on 2017/5/10.
 */

public class PieGraph extends View {

    private ArrayList<PieSlice> slices = new ArrayList<PieSlice>();
    private Paint paint = new Paint();

    private int thickness = 50;


    public PieGraph(Context context) {
        super(context);
    }

    public PieGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void update() {
        postInvalidate();
    }

    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        paint.reset();
        paint.setAntiAlias(true);
        float midX, midY, radius, innerRadius;

        float currentAngle = 270;//起始角度
        float currentSweep;
        int totalValue = 0;

        midX = getWidth() / 2;
        midY = getHeight() / 2;
        if (midX < midY) {
            radius = midX;
        } else {
            radius = midY;
        }
        innerRadius = radius - thickness;
        //用arcTo方法画360度的弧形是画不出来的，因此只有一种类型时考虑用画圆的方式
        if (slices != null && slices.size() <= 1) {
            int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,
                    Canvas.ALL_SAVE_FLAG);
            paint.reset();
            paint.setAntiAlias(true);
            if (slices.size() == 1) {
                paint.setColor(slices.get(0).getColor());
            } else {
                paint.setColor(Color.parseColor("#F0F1F2"));//没有类型时，默认显示一个灰色的饼图
            }
            canvas.drawCircle(midX, midY, radius, paint);
            paint.reset();
            paint.setAntiAlias(true);
            paint.setColor(Color.YELLOW);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            canvas.drawCircle(midX, midY, innerRadius, paint);
            canvas.restoreToCount(saveCount);
            return;
        }
        for (PieSlice slice : slices) {
            totalValue += slice.getValue();
        }
        for (PieSlice slice : slices) {
            Path p = new Path();
            paint.setColor(slice.getColor());
            currentSweep = (slice.getValue() / totalValue) * (360);
            //扇形是根据矩形画出来的
            p.arcTo(new RectF(midX - radius, midY - radius, midX + radius, midY + radius), currentAngle, currentSweep);
            p.arcTo(new RectF(midX - innerRadius, midY - innerRadius, midX + innerRadius, midY + innerRadius), currentAngle + currentSweep, -currentSweep);
            p.close();
            canvas.drawPath(p, paint);
            currentAngle = currentAngle + currentSweep;
        }
    }

    public void addSlice(PieSlice slice) {
        this.slices.add(slice);
        postInvalidate();//在工作线程中调用
//        invalidate();在UI线程中调用，比如在修改某个view的显示时，调用invalidate()才能看到重新绘制的界面
    }
}

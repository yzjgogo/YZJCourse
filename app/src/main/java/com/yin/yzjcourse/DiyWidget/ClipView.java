package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class ClipView extends View {
    private Paint paint1;
    private Paint paint2;
    public ClipView(Context context) {
        super(context);
        initView();
    }

    public ClipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        paint1 = new Paint();
        paint1.setColor(Color.BLUE);
        paint1.setAntiAlias(true);

        paint2 = new Paint();
        paint2.setColor(Color.BLACK);
        paint2.setAntiAlias(true);
    }

    /**
     对于clip,执行clip的结果是截取出一块canvas区域作为最新有效的canvas，这块被截取的区域会遮挡住原来的区域，没被截取的部分原来是什么样现在还是什么样，只是之后就是无效的canvas区域。
     要注意被截取的这部分canvas的坐标系仍然属于原来的canvas的坐标系，例如你截取的是new Rect(100, 100, 200, 200)这块区域，则这块被截取的canvas的左上角
     坐标仍然是(100,100)而不是(0,0),右下角仍然是(200,200)，也就是说你不可能在截取的这部分区域内找到(0,0)(10,10)...这些点，在这块被截取的区域内只能找到
     (100,100,200,200)以内的点。
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        Rect rect = new Rect(140,140,240,240);
        canvas.drawRect(rect, paint1);
        //跟translate,scale,rotate,skew一个道理，都是改变后不可逆，需要save()后restore()后恢复才可逆
        canvas.clipRect(new Rect(100, 100, 200, 200));
        canvas.drawColor(Color.GREEN);
        canvas.drawRect(new Rect(130,130,190,190), paint2);
    }
}

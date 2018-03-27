package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class ClipView extends View {
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.RED);
        //跟translate,scale,rotate,skew一个道理，都是改变后不可逆，需要save()后restore()后恢复才可逆
        canvas.clipRect(new Rect(100, 100, 200, 200));
        canvas.drawColor(Color.GREEN);
    }
}

package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.yin.yzjcourse.R;

public class ThreeDView extends View {
    Bitmap bitmap;
    Camera camera;
    Rect rect;
    Paint paint;
    public ThreeDView(Context context) {
        super(context);
        init(context);
    }

    public ThreeDView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ThreeDView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ThreeDView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_test);
        camera = new Camera();
        rect = new Rect();
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Matrix matrix = canvas.getMatrix();
        camera.save();
        camera.translate(500,0,0);
        camera.applyToCanvas(canvas);
//        camera.getMatrix(matrix);
//        canvas.setMatrix(matrix);
        camera.restore();

        canvas.drawColor(Color.YELLOW);
        canvas.drawBitmap(bitmap,0,0,null);
        rect.left = 0;
        rect.top = 0;
        rect.right = getMeasuredWidth();
        rect.bottom = getMeasuredHeight();
        canvas.drawRect(rect,paint);
    }
}

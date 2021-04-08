package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.yin.yzjcourse.R;

/**
 * Created by think on 2017/8/22.
 */

public class ShaderAvatarView extends View {
    private Paint paint;
    private Bitmap bitmap;
    private float radius;
    private int shape;
    private BitmapShader bitmapShader;

    public ShaderAvatarView(Context context) throws Exception {
        super(context);
        initView(context, null);
    }

    public ShaderAvatarView(Context context, @Nullable AttributeSet attrs) throws Exception {
        super(context, attrs);
        initView(context, attrs);
    }

    public ShaderAvatarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) throws Exception {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShaderAvatarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) throws Exception {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) throws Exception {
//        setLayerType( LAYER_TYPE_SOFTWARE , null);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShaderAvatarView);
            int resId = typedArray.getResourceId(R.styleable.ShaderAvatarView_avatar, -1);
            if (resId == -1) {
                throw new Exception("请指定一个图片");
            }
            bitmap = BitmapFactory.decodeResource(getResources(), resId);
            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            shape = typedArray.getInt(R.styleable.ShaderAvatarView_shape, 0);
            if (shape == 1) {
                radius = typedArray.getFloat(R.styleable.ShaderAvatarView_radius, 5);
            }
            typedArray.recycle();
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int heighMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? measureWidth: width, (heighMode == MeasureSpec.EXACTLY) ? measureHeight: height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Matrix matrix = new Matrix();
        float scale = (float)getWidth() / bitmap.getWidth();
        matrix.setScale(scale, scale);//缩放着色器图片到和控件长宽一样大小
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        float half = getWidth() / 2;
        if (shape == 0) {//圆形头像
            canvas.drawCircle(half, half, getWidth()/2, paint);
        } else {
            canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), radius, radius, paint);
        }
    }
}

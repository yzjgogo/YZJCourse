package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.io.IOException;
import java.io.InputStream;


public class BitmapClipView extends View {
    private Paint paint;
    private Bitmap resultBitmap;
    private Bitmap resultBitmap2;
    public BitmapClipView(Context context) throws Exception{
        super(context);
        initView(context,null);
    }

    public BitmapClipView(Context context, @Nullable AttributeSet attrs) throws Exception{
        super(context, attrs);
        initView(context,attrs);
    }

    public BitmapClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) throws Exception{
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BitmapClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) throws Exception{
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs) throws Exception{
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        try {
            InputStream inputStream = getContext().getAssets().open("fz2.jpg");
            Bitmap bitmap1 = BitmapFactory.decodeStream(inputStream);
            DLog.eLog("图片的宽高："+bitmap1.getWidth()+","+bitmap1.getHeight());//图片的宽高：252,173
            //剪裁bitmap1的部分返回resultBitmap,剪裁方式是，从bitmap1的(20,20)位置开始，横向剪裁200px(即剪裁到20+200=220px)，纵向剪裁100px(即剪裁到20+100=120px)
            resultBitmap = Bitmap.createBitmap(bitmap1,20,20,200,100);
            DLog.eLog("图片的宽高2："+resultBitmap.getWidth()+","+resultBitmap.getHeight());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(resultBitmap,0,0,paint);
    }
}

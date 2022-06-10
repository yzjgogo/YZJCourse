package com.yin.yzjcourse.Optimize;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.yin.yzjcourse.utils.DLog;

import java.io.IOException;
import java.io.InputStream;

/**
 * 参考注释关键第一二三步
 * 实际上就是计算出要显示的原始图片的矩形区域，然后让BitmapRegionDecoder截取出这个矩形区域显示出来
 * 从而保证这个矩形区域的宽高不超过屏幕(或者父view)的宽高，也就避免了OOM
 *
 * 基础知识：
 * 对于一个w*h = 1000*2000的图片
 * 如果按照默认的 ARGB_8888 格式加载到内存中 , 会占⽤ 1000*2000*4=8000000 字节的内存 , ⼤约 7.6 MB
 */
public class HighImageView extends View {
    private int mImageWidth;
    private int mImageHeight;
    private BitmapRegionDecoder mDecoder;
    private static BitmapFactory.Options mDecodeOptions = new BitmapFactory.Options();
    static{
        mDecodeOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
    }
    private Rect mRect = new Rect();
    private GestureDetector customGestureDetector;

    private static final String TAG = "HighImageView";

    public HighImageView(Context context) {
        super(context);
        init();
    }

    public HighImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HighImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HighImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    public void setImage(InputStream is,int width ,int height) {
        try {
            /**关键第一步：创建BitmapRegionDecoder实例
                第一个参数是图片的输入流；
                第二个参数给false即可*/
            mDecoder = BitmapRegionDecoder.newInstance(is, false);
            mImageWidth = width;
            mImageHeight = height;

            requestLayout();
            invalidate();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
            }
        }
    }
    private void init() {
        customGestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            /**关键第二步：计算出要截取的原图片的区域区域范围*/
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx, float dy) {
                if (mImageWidth > getWidth()) {
                    mRect.offset((int) dx, 0);
                    checkWidth();
                    invalidate();
                }
                if (mImageHeight > getHeight()) {
                    mRect.offset(0, (int) dy);
                    checkHeight();
                    invalidate();
                }
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }
    private void checkHeight() {
        if (mRect.bottom > mImageHeight) {
            mRect.bottom = mImageHeight;
            mRect.top = mRect.bottom - getHeight();
        }
        if (mRect.top < 0) {
            mRect.top = 0;
            mRect.bottom = mRect.top + getHeight();
        }
    }

    private void checkWidth() {
        if (mRect.right > mImageWidth) {
            mRect.right = mImageWidth;
            mRect.left = mImageWidth - getWidth();
        }
        if (mRect.left < 0) {
            mRect.left = 0;
            mRect.right = mRect.left + getWidth();
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        customGestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        mRect.left = 0;
        mRect.top = 0;
        mRect.right = mRect.left + width;
        mRect.bottom = mRect.top + height;
        DLog.eLog("执行onMeasure了");
    }

    @Override
    protected void onDraw(Canvas canvas) {

        /**关键第三步：解码原图片指定矩形区域的图片，相当于截图
         第一个参数是矩形区域；
         第二个参数是图片质量选项*/
        Bitmap bitmap = mDecoder.decodeRegion(mRect, mDecodeOptions);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }
}

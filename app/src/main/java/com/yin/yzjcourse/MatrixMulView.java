package com.yin.yzjcourse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.yin.yzjcourse.utils.DLog;

public class MatrixMulView extends View {
    Bitmap bitmap;
    Matrix matrix;
    int bitWidth,bitHeight;
    public MatrixMulView(Context context) {
        super(context);
        initView();
    }

    public MatrixMulView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MatrixMulView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MatrixMulView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.dog_edge);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.just_head);
        bitWidth = bitmap.getWidth();
        bitHeight = bitmap.getHeight();
        DLog.eLog("图片的宽高："+bitWidth+","+bitHeight);
        matrix = new Matrix();
    }

    /**
     * 这个例子是实现围绕图片的中心缩放(旋转)，因为默认是参考图片的左上角(0,0)缩放的，所以事先逻辑就是：先将图片的左上角向右向下移动(w/2,h/2),再进行缩放(旋转)，再将图片的左上角向左向上移动(-w/2,-h/2)
     *
     * pre...和post..系列函数的使用方法
     * pre... --> (前乘)右乘 M*S，只是决定了S在M的右边,乘法永远是从左往右，遇到括号，括号优先
     * post... --> (后乘)左乘 S*M，只是决定了S再M的左边，,乘法永远是从左往右，遇到括号，括号优先
     * 先根据业务逻辑构造出线性代数里的真实的“矩阵乘法公式”，然后再用pre..和post..组合成这个公式即可；
     * pre..和post..不能控制代码的执行顺序，代码永远都是一行一行执行的，执行一行就得一行的结果；pre..和post..没有代码执行顺序上的先后只说，只是用来构造“矩阵乘法公式”,直到真正使用Matrix时才按照构造出的公式
     * 真正发挥平移、缩放、旋转、错切等作用
     *
     * 一个已知的“矩阵乘法公式”，可以通过不同的pre..或post..组合成不同的代码形式
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        matrix.reset();
        //reset后 matrix成为单位矩阵(这个很关键，因为单位矩阵乘以任何矩阵都等于矩阵自己，又因为矩阵不满足交换律，且非单位矩阵在乘法中不能直接舍弃，所以如果这个M不是单位矩阵，则下面这几种方式的结果就会不一样)
        //可见，一个线性代数的矩阵公式T*S*(-T)（(0,0)先位移，再缩放，再移回来），可以通过如下多种pre..或post..组合的方式实现，或许还有别的组合，可以试一下；

        //M*T*S*(-T) --> T*S*(-T)
        matrix.preTranslate(bitWidth/2.0f,bitHeight/2.0f);
        matrix.preScale(0.5f,0.5f);
//        matrix.preRotate(90);
        matrix.preTranslate(-bitWidth/2.0f,-bitHeight/2.0f);

        //T*S*(-T)*M --> T*S*(-T)  效果一样
//        matrix.postTranslate(-bitWidth/2.0f,-bitHeight/2.0f);
//        matrix.postScale(0.5f,0.5f);
//        matrix.postTranslate(bitWidth/2.0f,bitHeight/2.0f);

        //(T*S*M)*(-T) --> (T*S)*(-T) --> T*S*(-T) 效果一样
//        matrix.postScale(0.5f,0.5f);
//        matrix.postTranslate(bitWidth/2.0f,bitHeight/2.0f);
//        matrix.preTranslate(-bitWidth/2.0f,-bitHeight/2.0f);

        //(T*(M*S))*(-T) --> (T*M*S)*(-T) --> (T*S)*(-T) --> T*S*(-T) 效果一样
//        matrix.preScale(0.5f,0.5f);
//        matrix.postTranslate(bitWidth/2.0f,bitHeight/2.0f);
//        matrix.preTranslate(-bitWidth/2.0f,-bitHeight/2.0f);

        canvas.drawBitmap(bitmap, matrix, null);
    }
}

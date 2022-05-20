package com.yin.yzjcourse.DiyWidget

import android.graphics.*
import android.graphics.drawable.Drawable
import com.yin.yzjcourse.utils.DLog

/**
 * 最简单的自定义drawable模板
 * draw(),setAlpha(),setColorFilter(),getOpacity()4个方法必须重写，其中draw()方法是核心
 *
 * https://blog.csdn.net/yanbober/article/details/56844869
 *
 * csdn_drawable.png
 *
 * 我刚学时查看View.java中Drawable的顺序：View.java -> setBackground(Drawable background) -> setBackgroundDrawable(Drawable background)(很重要,给View设置drawable,以及新老drawable的处理)
 * draw(Canvas canvas) -> drawBackground(Canvas canvas)(很重要给drawable设置bounds以及调用drawable.draw(canvas)) -> setBackgroundBounds()(就是在这里调用drawable.setBounds()设置drawable的边界)
 *
 * 我刚学时查看ImageView.java中Drawable的顺序：ImageView.java -> setImageDrawable(Drawable drawable) -> updateDrawable(Drawable d)(类似View.java的setBackgroundDrawable()里的逻辑给ImageView设置drawable
 * ,以及新老drawable的处理) -> configureBounds()(类似View.java里的setBackgroundBounds()调用drawable.setBounds()设置drawable的边界)
 * onDraw(Canvas canvas)调用了drawable.draw()方法
 */
class MyDrawable : Drawable {
    val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(color: Int) {
        mPaint.color = color
    }

    /**
     * 这个canvas来自使用了这个drawable的View的canvas，参考上面的注释，其实绘制drawable就是在View的canvas上画东西
     */
    override fun draw(canvas: Canvas) {
        val r: Rect = bounds
        val cx: Float = r.exactCenterX()
        val cy: Float = r.exactCenterY()
        DLog.eLog("drawable的宽高：" + (r.right - r.left) + " , " + (r.bottom - r.top))
        canvas.drawCircle(cx, cy, Math.min(cx, cy), mPaint)
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
        invalidateSelf()
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.setColorFilter(colorFilter)
        invalidateSelf()
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    /**
     * 边界发生变化时出发，可以类比View的onSizeChanged()
     */
    override fun onBoundsChange(bounds: Rect?) {
        super.onBoundsChange(bounds)
        DLog.eLog("执行onBoundsChange了:$bounds")
    }

    /**
     * 获取drawable的宽高，我们可以按需从drawable.getBounds()中计算，这个bounds()也来自使用它的View里设置了，参考上面的注释
     * 默认返回 -1
     * 一般如果使用这个drawable的view是wrap_content时，我们可能要计算一下，这两个方法的返回值
     */
    override fun getIntrinsicWidth(): Int {
        val width = super.getIntrinsicWidth();
        DLog.eLog("获取drawable的宽：$width")
        return width
    }

    override fun getIntrinsicHeight(): Int {
        val height = super.getIntrinsicHeight()
        DLog.eLog("获取drawable的高：$height")
        return height
    }
}
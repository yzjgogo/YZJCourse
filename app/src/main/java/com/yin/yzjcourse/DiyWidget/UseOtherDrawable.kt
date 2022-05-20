package com.yin.yzjcourse.DiyWidget

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Rect
import android.graphics.drawable.Drawable

/**
 * 自定义drawable,传入一个otherDrawable，一切由这个otherDrawable来处理，本身的UseOtherDrawable更像一个桥梁
 * 同样的道理，主要分两步
 * 第一步：设置边界，这里把当前useOtherDrawable的边界给otherDrawable
 * 第二步：绘制drawable，这里把canvas交给otherDrawable绘制
 */
class UseOtherDrawable : Drawable {
    var otherDrawable: Drawable

    constructor(_otherDrawable: Drawable) {
        otherDrawable = _otherDrawable
    }

    override fun draw(canvas: Canvas) {
        otherDrawable.draw(canvas)
    }

    override fun setAlpha(alpha: Int) {
        otherDrawable.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        otherDrawable.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return otherDrawable.opacity
    }

    /**
     * 把当前useOtherDrawable的边界给otherDrawable,其他方法一个道理
     */
    override fun onBoundsChange(bounds: Rect?) {
        super.onBoundsChange(bounds)
        otherDrawable.bounds = getBounds()
    }

    override fun invalidateSelf() {
        super.invalidateSelf()
        otherDrawable.invalidateSelf()
    }
}
package com.yin.yzjcourse.DiyWidget

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import kotlinx.android.synthetic.main.activity_view_size.*

/**
 * 当view的宽高发生变化时，会调用onSizeChanged()
 * 第一次加载view，在onMeasure之后会调用onSizeChanged()
 * 通过看View的代码发现，当View的left,top,right,bottom发生变化时会调用onSizeChanged()
 *
 * 如下：是几种情况的总结
 */
class ViewSizeActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_size)
        bt_start.setOnClickListener {
            //----------------------------验证情况1-------------------------------

            //requestLayout()和重新setLayoutParams不会出发onSizeChanged()
//            size_view.requestLayout()
//            val params = size_view.layoutParams
//            params.width = 200
////            size_view.requestLayout() //刷新LayoutParams方式1
//            size_view.layoutParams = params //刷新LayoutParams方式2




            //----------------------------验证情况2-------------------------------




//            if(size_view.childCount == 0){
//                val view = View(this@ViewSizeActivity)
//                val params = ViewGroup.LayoutParams(50,50)
//                view.layoutParams = params
//                view.setBackgroundColor(resources.getColor(R.color.capital_other))
//                //size_view时wrap_content时，addView()会触发onSizeChanged()，否则不会，总之要看有没有改变size_view的left,top,right,bottom
//                size_view.addView(view)
//            }else{
//                //size_view时wrap_content时，removeView()会触发onSizeChanged()，否则不会，总之要看有没有改变size_view的left,top,right,bottom
//                size_view.removeViewAt(0)
//            }


            //----------------------------验证情况3-------------------------------

            //size_view时wrap_content时,修改子view的尺寸如果出发了size_view的left,top,right,bottom的变化，就会出发onSizeChanged()
            val childParams = v_child.layoutParams
            childParams.width = 100
            v_child.layoutParams = childParams


            //----------------------------验证情况4-------------------------------


            //直接改变size_view的left,top,right,bottom会触发onSizeChanged()，这也是onSizeChanged()被调用的本质
//            size_view.left = 50
        }
    }
}
package com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L4LayoutParams

import android.os.Bundle
import com.yin.yzjcourse.BaseActivity
import org.jetbrains.anko.*

class ParamsActivity : BaseActivity() {
    val ID_OK = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         *  lparams()用于设置LayoutParams()
         *
         *  各种margin，名字就是含义：
         *  margin
         *  horizontalMargin
         *  leftMargin
         *  rightMargin
         *  verticalMargin
         *  topMargin
         *  bottomMargin
         *
         *  linearLayout是Horizontal的LinearLayout
         *  垂直的LinearLayout参考：[com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L1Direcyly.MyLayoutDirectlyActivity.onCreate]
         */
        linearLayout {
            button("Login") {
                textSize = 26f
            }.lparams(width = wrapContent) {
                //命名参数传递,宽和高有默认值WRAP_CONTENT
                horizontalMargin = dip(5)//左右margin
                verticalMargin = dip(10)
            }

            /**
             * 相对布局的LayoutParams()
             *
             * 通过anko同样可以实现RelativeLayout
             *
             * alignParentTop():见文识意，可知一系列类似的方法。
             * below(id):见文识意，可知一系列类似的方法。
             */
            relativeLayout {
                button("Ok") {
                    id = ID_OK
                }.lparams { alignParentTop() }

                button("Cancel").lparams { below(ID_OK) }
            }
        }
    }
}

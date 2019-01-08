package com.yin.yzjcourse.mykt.MyAnko.A5Layouts.L1Direcyly

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.RelativeLayout
import android.widget.TextView
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MyLayoutDirectlyActivity : BaseActivity() {
    /**
     * 不需要写xml布局文件，直接在onCreate()中写anko布局，切自动加载出来
     * 无需调用setContentView()即可自动加载anko定义的布局。
     * verticalLayout就是Vertical的LinearLayout,linearLayout是Horizontal的LinearLayout
     *
     * editText("直接写") -->  直接给一个text
     * editText(R.string.tv) --> 通过资源文件指定text
     *
     * hintResource = R.string.et_pwd//指定资源文件,类似：hint = "直接写"
     * textResource = R.string.et_pwd//类似：  text = "直接写"
     * imageResource = R.mipmap.ic_launcher
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_my_layout)
        verticalLayout {
            padding = dip(30)//verticalLayout的padding
            backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)

            editText(R.string.et_user) {
                hint = "请输入姓名"
//                hint = getString(R.string.et_pwd)
                textSize = 24f
                backgroundColor = ContextCompat.getColor(context, R.color.bg_page)
            }

            editText {
                hintResource = R.string.et_pwd//指定资源文件,类似：hint = "直接写"
                textResource = R.string.et_pwd//类似：  text = "直接写"
                textSize = 24f
                backgroundColor = ContextCompat.getColor(context, R.color.bg_page)
            }

            imageView {
                imageResource = R.mipmap.ic_launcher
//                image = ContextCompat.getDrawable(context,R.mipmap.ic_launcher)
            }

            //todo mark 还没得出结论
//            themedButton("注册", theme = R.style.AnkoButton)

            button("登录") {
                textSize = 26f
                backgroundColor = ContextCompat.getColor(context, R.color.bg_page)
                onClick {
                    toast("登录成功")
                    with(this@verticalLayout) {
                        editText {
                            hint = "新来的"
                            backgroundColor = ContextCompat.getColor(context, R.color.orange)
                        }
                    }
                }
            }

            /**
             * 递归遍历ViewGroup的各个子View
             */
            verticalLayout {
                textView("第一")
                relativeLayout {

                }.lparams(dip(80), dip(50))
            }.applyRecursively {
                when (it) {
                    is TextView -> it.backgroundColor = Color.GRAY
                    is RelativeLayout -> it.backgroundColor = Color.GREEN
                }
            }
        }
    }
}

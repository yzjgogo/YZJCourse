package com.yin.yzjcourse.mykt.MyAnko.A2AlertAndProcess

import android.os.Bundle
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import kotlinx.android.synthetic.main.activity_alert_and_process.*
import org.jetbrains.anko.*

/**
 * 官网介绍：
 * https://github.com/Kotlin/anko/wiki/Anko-Commons-%E2%80%93-Dialogs
 */
class AlertAndProcessActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_and_process)
        initView()
//Toast的用法
//        toast("Hi there!")
//        toast(R.string.message)
//        longToast("Wow, such duration")

//snackbar用法
//        view.snackbar("Hi there!")
//        view.snackbar(R.string.message)
//        view.longSnackbar("Wow, such duration")
//        view.snackbar("Action, reaction", "Click me!") { doStuff() }

    }

    private fun initView() {
        /**
         * 创建一个带有默认 ‘OK’和‘CANCEL’的按钮的对话框，按钮显示的文字不可修改。
         */
        btDialog1.setOnClickListener {
            alert("我是内容", "我是标题") {
                yesButton { toast("我是OK按钮") }
                noButton { toast("我是CANCEL按钮") }
            }.show()

//            alert(Appcompat, "Some text message").show()
        }


        /**
         * 创建一个可以自定义按钮显示文字，且可以显示三个按钮的对话框
         */
        btDialog2.setOnClickListener {
            alert("我是内容", "我是标题") {
                positiveButton("确定") { toast("确定了") }
                negativeButton("取消") { toast("取消了") }
                neutralPressed("再考虑下") { toast("考虑考虑") }
            }.show()
        }


        /**
         * 创建一个自定义布局的dialog，注意此时alert的第一个参数是其实是title,不是显示的内容。
         */
        btDialog3.setOnClickListener {
            alert("标题") {
                positiveButton("确定") {}
                customView {
                    linearLayout {
                        textView("我是文本")
                        button("我是按钮")
                        padding = dip(20)
                    }
                }
            }.show()
        }

        /**
         * 可单选的dialog，列出多个条目单机选择
         */
        val countries = listOf("Russia", "USA", "Japan", "Australia")
        btSelect.setOnClickListener {
            selector("Where are you from?", countries) { dialogInterface, i ->
                toast("So you're living in ${countries[i]}, right?")
            }
        }


        /**
         *  这个过时了，后续补充各种进度对话框的情况，没有进度的进度对话框
         *  todo mark
         */
        btProcess.setOnClickListener {
            indeterminateProgressDialog("This a progress dialog").show()
        }


        /**
         *  这个过时了，后续补充各种进度对话框的情况，有进度的进度对话框
         *  todo mark
         */
        btProcess2.setOnClickListener {
            val dialog = progressDialog(message = "Please wait a bit…", title = "Fetching data")
            //dialog操作
        }
    }
}

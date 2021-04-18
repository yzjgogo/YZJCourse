package com.yin.yzjcourse.Jetpack.Room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.Jetpack.MyLCService
import com.yin.yzjcourse.R
import com.yin.yzjcourse.utils.DLog
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : BaseActivity() {
    lateinit var myDatabase: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        ButterKnife.bind(this)
        myDatabase = MyDatabase.instance
    }

    @OnClick(R.id.bt_add, R.id.bt_delete, R.id.bt_update, R.id.bt_query)
    fun onViewClicked(view: View) {
        val id = et_id.text.toString().toInt()
        val name = et_name.text.toString()
        val age = et_age.text.toString()
        DLog.eLog("输出：$id , $name , $age")
        when (view.id) {
            R.id.bt_add -> {
                Thread{
                    myDatabase.studentDao().insertStudent(Student(id,name,age))
                }.start()
            }
            R.id.bt_delete -> {
                Thread{
                    myDatabase.studentDao().deleteStudent(Student(id,name,age))
                }.start()
            }
            R.id.bt_update -> {
                Thread{
                    myDatabase.studentDao().updateStudent(Student(id,name,age))
                }.start()
            }
            R.id.bt_query -> {
                Thread{
                    val students = myDatabase.studentDao().getStudentList()
                    DLog.eLog("所有数据:${students}")
                }.start()
            }
        }
    }
}
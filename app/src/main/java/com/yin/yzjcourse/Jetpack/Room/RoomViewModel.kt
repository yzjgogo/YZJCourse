package com.yin.yzjcourse.Jetpack.Room

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class RoomViewModel:AndroidViewModel {
    /**
     * 用LiveData包装，使其可以被观察者观察
     */
    val studentLiveData: LiveData<List<Student>>
    val myDatabase: MyDatabase = MyDatabase.instance

    constructor(context: Application):super(context){
        studentLiveData = myDatabase.studentDao().getStudentList()
    }

    override fun onCleared() {
        super.onCleared()
    }
}
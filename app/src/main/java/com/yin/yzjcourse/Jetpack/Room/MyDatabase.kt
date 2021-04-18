package com.yin.yzjcourse.Jetpack.Room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yin.yzjcourse.MyApplication

const val DATABASE_NAME = "my_db"
@Database(entities = [Student::class],version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun studentDao():StudentDao
    companion object{
        val instance = Single.sin
    }
    private object Single{
        val sin:MyDatabase = Room.databaseBuilder(MyApplication.appContext,MyDatabase::class.java, DATABASE_NAME).build()
    }
}
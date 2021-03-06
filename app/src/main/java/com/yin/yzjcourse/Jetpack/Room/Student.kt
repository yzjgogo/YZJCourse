package com.yin.yzjcourse.Jetpack.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
        var id:Int,

        @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
        var name:String,

        @ColumnInfo(name = "age", typeAffinity = ColumnInfo.TEXT)
        var age:String,

        /**
         * salary属性，是在学数据库升级时，[MyDatabase.MIGRATION_3_4]用到的升级后新增一列
         */
        @ColumnInfo(name = "salary", typeAffinity = ColumnInfo.TEXT)
        var salary:String = "100"
)
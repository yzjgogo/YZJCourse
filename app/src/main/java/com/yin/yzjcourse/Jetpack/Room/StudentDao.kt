package com.yin.yzjcourse.Jetpack.Room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
    @Insert
    fun insertStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    /**
     * 用LiveData包装，使其可以被观察者观察
     */
    @Query("SELECT * FROM student")
    fun getStudentList():LiveData<List<Student>>

    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudentById(id: Int):Student
}
package com.example.roomdemo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface studentDAO {

    @Insert
    suspend fun addStudent(student:Student)

    @Query(value = "Select * From student_table")
    suspend fun getAllStudent(): Array<Student>


}
package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface UserDao {

    @Query("select *from USER_TABLE order by id asc")
    fun getAllUser():LiveData<List<User>>

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)


}
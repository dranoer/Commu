package com.nightmareinc.communere.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDatabaseDao {

    @Insert
    suspend fun insert(user: User): Long

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM user_table WHERE userId = :key")
    suspend fun get(key: Long): User

    @Query("SELECT * FROM user_table WHERE email = :email and password = :password ")
    suspend fun checkCredential(email: String, password: String): User?

    @Query("DELETE FROM user_table")
    suspend fun clear()

    @Query("SELECT * FROM user_table ORDER BY userId DESC")
    suspend fun getAllUsers(): LiveData<List<User>>

}
package com.nightmareinc.communere.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (

    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,

    @ColumnInfo(name = "fullname")
    val fullname: String = "user",

    @ColumnInfo(name = "email")
    val email: String = "email",

    @ColumnInfo(name = "password")
    val password: String = "password"

)
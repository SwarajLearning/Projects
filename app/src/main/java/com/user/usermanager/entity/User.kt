package com.user.usermanager.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbluser")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("uid")
    val id: Int = 0,

    @ColumnInfo("uname")
    val name: String,

    @ColumnInfo("uemail")
    val email: String
)

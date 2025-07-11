package com.user.usermanager.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * User is a data class representing a user entity in the database.
 */
@Entity(tableName = "tbluser")
data class User(

    /**
     * Unique identifier for the user.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("uid")
    val id: Int = 0,

    /**
     * Name of the user.
     */
    @ColumnInfo("uname")
    val name: String,

    /**
     * Email address of the user.
     */
    @ColumnInfo("uemail")
    val email: String
)

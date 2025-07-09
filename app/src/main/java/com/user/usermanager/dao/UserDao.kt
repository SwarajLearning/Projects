package com.user.usermanager.dao

import androidx.room.*
import com.user.usermanager.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User?)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM tbluser")
    fun fetchAll() : Flow<List<User>>

}
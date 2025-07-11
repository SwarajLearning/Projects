package com.user.usermanager.dao

import androidx.room.*
import com.user.usermanager.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * UserDao is the Data Access Object (DAO) interface for managing User entities in the database.
 */
@Dao
interface UserDao {

    /**
     * Inserts a new User into the database.
     */
    @Insert
    suspend fun insert(user: User)

    /**
     * Deletes a User from the database.
     */
    @Delete
    suspend fun delete(user: User?)

    /**
     * Updates an existing User in the database.
     */
    @Update
    suspend fun update(user: User)

    /**
     * Fetches a User list from the database.
     */
    @Query("SELECT * FROM tbluser")
    fun fetchAll() : Flow<List<User>>

}
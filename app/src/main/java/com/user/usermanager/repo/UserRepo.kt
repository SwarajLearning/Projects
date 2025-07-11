package com.user.usermanager.repo

import android.app.Application
import com.user.usermanager.dao.UserDao
import com.user.usermanager.database.UserDatabase
import com.user.usermanager.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * UserRepo is a repository class that provides methods to interact with the UserDao.
 */
class UserRepo(application: Application) {

    /**
     * UserDao instance for accessing user data in the database.
     */
    private val userDao: UserDao = UserDatabase.createDatabase(application).userDao()

    /**
     * Inserts a new User into the database.
     */
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    /**
     * Updates an existing User in the database.
     */
    suspend fun update(user: User) {
        userDao.update(user)
    }

    /**
     * Deletes a User from the database.
     */
    suspend fun delete(user: User?) {
        userDao.delete(user)
    }

    /**
     * Fetches a list of all Users from the database.
     */
    fun fetchAll() : Flow<List<User>> {
        return userDao.fetchAll()
    }
}
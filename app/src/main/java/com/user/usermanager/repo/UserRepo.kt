package com.user.usermanager.repo

import android.app.Application
import com.user.usermanager.dao.UserDao
import com.user.usermanager.database.UserDatabase
import com.user.usermanager.entity.User
import kotlinx.coroutines.flow.Flow

class UserRepo(application: Application) {

    private val userDao: UserDao = UserDatabase.createDatabase(application).userDao()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun update(user: User) {
        userDao.update(user)
    }

    suspend fun delete(user: User?) {
        userDao.delete(user)
    }

    fun fetchAll() : Flow<List<User>> {
        return userDao.fetchAll()
    }
}
package com.user.usermanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.user.usermanager.dao.UserDao
import com.user.usermanager.entity.User

/**
 * UserDatabase is the Room database class that holds the User entity and provides access to the UserDao.
 */
@Database(entities = [User::class], version = 2)
abstract class UserDatabase : RoomDatabase() {

    /**
     * Returns the UserDao instance for accessing user data in the database.
     */
    abstract fun userDao(): UserDao

    /**
     * Companion object to create a singleton instance of UserDatabase.
     */
    companion object {

        /**
         * Singleton instance of UserDatabase.
         */
        private var INSTANCE: UserDatabase? = null

        /**
         * Creates or retrieves the singleton instance of UserDatabase.
         */
        fun createDatabase(context: Context): UserDatabase {
            // Check if the instance is already created
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "dbuser"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}
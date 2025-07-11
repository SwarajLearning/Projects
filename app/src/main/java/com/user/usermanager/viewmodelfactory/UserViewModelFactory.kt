package com.user.usermanager.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.user.usermanager.repo.UserRepo
import com.user.usermanager.viewmodel.UserViewModel

/**
 * UserViewModelFactory is a ViewModelProvider.Factory that creates instances of UserViewModel.
 */
@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(private val userRepo: UserRepo) : ViewModelProvider.Factory {

    /**
     * Creates a ViewModel of the specified class type.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepo) as T
        }

        throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
    }
}
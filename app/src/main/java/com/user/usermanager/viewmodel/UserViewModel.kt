package com.user.usermanager.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.user.usermanager.entity.User
import com.user.usermanager.repo.UserRepo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Thread.State

/**
 * UserViewModel is a ViewModel class that manages user-related data and operations.
 */
class UserViewModel(private val userRepo: UserRepo) : ViewModel() {

    /**
     * MutableStateFlow for holding the username input.
     */
    private var _uname: MutableStateFlow<String> = MutableStateFlow("")

    /**
     * StateFlow for exposing the username input to the UI.
     */
    val uname: MutableStateFlow<String> = _uname



    /**
     * MutableStateFlow for holding the user email input.
     */
    private var _uemail: MutableStateFlow<String> = MutableStateFlow("")

    /**
     * StateFlow for exposing the user email input to the UI.
     */
    val uemail: MutableStateFlow<String> = _uemail


    /**
     * MutableStateFlow for holding the result message after user operations.
     */
    private var _result: MutableStateFlow<String> = MutableStateFlow("")

    /**
     * StateFlow for exposing the result message to the UI.
     */
    val result: StateFlow<String> = _result


    /**
     * MutableStateFlow for holding the list of users.
     */
    private var _userList: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())

    /**
     * StateFlow for exposing the list of users to the UI.
     */
    val userList: StateFlow<List<User>> = fetchAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = _userList.value
    )

    /**
     * MutableStateFlow for holding the selected user for editing.
     */
    private var _selectedUser: MutableStateFlow<User> = MutableStateFlow(User(0, "", ""))

    /**
     * Submits the user data for insertion or update based on the selected user.
     */
    fun submit(view: View) {
        val name: String = _uname.value
        val email: String = _uemail.value

        viewModelScope.launch {

            if (_selectedUser.value.id == 0) {
                userRepo.insert(
                    User(
                        name = name,
                        email = email
                    )
                )

                _result.value = "$name and other details is added successfully"
            } else {
                userRepo.update(
                    User(
                        id = _selectedUser.value.id,
                        name = name,
                        email = email
                    )
                )

                _result.value = "$name and other details is updated successfully"
            }

            _uname.value = ""
            _uemail.value = ""
        }
    }

    /**
     * Fetches all users from the repository and returns a Flow of the user list.
     */
    private fun fetchAll(): Flow<List<User>> {
        return userRepo.fetchAll()
    }

    /**
     * Edits the selected user by populating the input fields with the user's data.
     */
    fun edit(user: User) {
        _selectedUser.value = user

        _uname.value = user.name
        _uemail.value = user.email
    }

    /**
     * Deletes the selected user from the repository.
     */
    fun delete(user: User?) {
        viewModelScope.launch {
            userRepo.delete(user)

            _result.value = "${user?.name} and other details is deleted successfully"
        }
    }
}
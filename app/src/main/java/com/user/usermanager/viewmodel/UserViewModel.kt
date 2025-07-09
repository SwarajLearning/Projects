package com.user.usermanager.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.user.usermanager.entity.User
import com.user.usermanager.repo.UserRepo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Thread.State

class UserViewModel(private val userRepo: UserRepo) : ViewModel() {

    private var _uname: MutableStateFlow<String> = MutableStateFlow("")
    val uname: MutableStateFlow<String> = _uname

    private var _uemail: MutableStateFlow<String> = MutableStateFlow("")
    val uemail: MutableStateFlow<String> = _uemail

    private var _result: MutableStateFlow<String> = MutableStateFlow("")
    val result: StateFlow<String> = _result

    private var _userList: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val userList: StateFlow<List<User>> = fetchAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = _userList.value
    )


    private var _selectedUser: MutableStateFlow<User> = MutableStateFlow(User(0, "", ""))
    val selectedUser: StateFlow<User> = _selectedUser


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

    private fun fetchAll(): Flow<List<User>> {
        return userRepo.fetchAll()
    }


    fun edit(user: User) {
        _selectedUser.value = user

        _uname.value = user.name
        _uemail.value = user.email
    }

    fun delete(user: User?) {
        viewModelScope.launch {
            userRepo.delete(user)

            _result.value = "${user?.name} and other details is deleted successfully"
        }
    }
}
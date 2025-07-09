package com.user.usermanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.user.usermanager.adapter.UserAdapter
import com.user.usermanager.databinding.ActivityMainBinding
import com.user.usermanager.repo.UserRepo
import com.user.usermanager.viewmodel.UserViewModel
import com.user.usermanager.viewmodelfactory.UserViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var ktUserViewModel: UserViewModel
    private lateinit var userRepo: UserRepo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        userRepo = UserRepo(application)

        val factory = UserViewModelFactory(userRepo)
        ktUserViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        binding.apply {
            lifecycleOwner = this@MainActivity
            xmlUserModel = ktUserViewModel

            rvList.layoutManager = LinearLayoutManager(this@MainActivity)

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {

                    launch {
                        ktUserViewModel.userList.collect {
                            rvList.adapter = UserAdapter(it, ktUserViewModel)
                        }
                    }
                }
            }
        }
    }

}
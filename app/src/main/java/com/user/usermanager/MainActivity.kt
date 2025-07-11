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

/**
 * MainActivity is the entry point of the User Manager application.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Binding object for the ActivityMain layout.
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * ViewModel instance for managing user-related data and operations.
     */
    private lateinit var ktUserViewModel: UserViewModel

    /**
     * Repository instance for accessing user data.
     */
    private lateinit var userRepo: UserRepo


    /**
     * Called when the activity is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up data binding for the activity layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Initialize the UserRepo with the application context
        userRepo = UserRepo(application)

        // Create a ViewModelFactory with the UserRepo
        val factory = UserViewModelFactory(userRepo)

        // Create the UserViewModel using the ViewModelProvider with the factory
        ktUserViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        // Set up the binding with the ViewModel and lifecycle owner
        binding.apply {

            // Set the ViewModel and lifecycle owner for data binding
            lifecycleOwner = this@MainActivity

            // Set the ViewModel for data binding
            xmlUserModel = ktUserViewModel

            // Set up the RecyclerView with the userList from the ViewModel
            rvList.layoutManager = LinearLayoutManager(this@MainActivity)

            // Observe the lifecycle of the activity and collect the userList flow
            lifecycleScope.launch {

                // Repeat the block when the lifecycle is in STARTED state
                repeatOnLifecycle(Lifecycle.State.STARTED) {

                    // Collect the userList flow from the ViewModel
                    launch {
                        // Collect the user list and set the adapter for the RecyclerView
                        ktUserViewModel.userList.collect {
                            rvList.adapter = UserAdapter(it, ktUserViewModel)
                        }
                    }
                }
            }
        }
    }

}
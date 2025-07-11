package com.user.usermanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.user.usermanager.databinding.UserAdapterBinding
import com.user.usermanager.entity.User
import com.user.usermanager.viewmodel.UserViewModel

/**
 * UserAdapter is a RecyclerView adapter that binds User data to the UserAdapterBinding layout.
 */
class UserAdapter(private val userList: List<User?>, private val ktUserViewModel: UserViewModel) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    /**
     * Creates a new ViewHolder for the UserAdapterBinding layout.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    /**
     * Returns the number of items in the userList.
     */
    override fun getItemCount(): Int {
        return userList.size
    }

    /**
     * Binds the User data to the UserAdapterBinding layout for the specified position.
     */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.user = userList[position]
        holder.binding.xmlUserViewModel = ktUserViewModel
    }

    /**
     * ViewHolder class that holds the UserAdapterBinding layout.
     */
    inner class UserViewHolder(val binding: UserAdapterBinding) : RecyclerView.ViewHolder(binding.root)
}
package com.user.usermanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.user.usermanager.databinding.UserAdapterBinding
import com.user.usermanager.entity.User
import com.user.usermanager.viewmodel.UserViewModel


class UserAdapter(private val userList: List<User?>, private val ktUserViewModel: UserViewModel) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.user = userList[position]
        holder.binding.xmlUserViewModel = ktUserViewModel
    }


    inner class UserViewHolder(val binding: UserAdapterBinding) : RecyclerView.ViewHolder(binding.root)
}
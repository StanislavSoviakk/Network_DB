package com.example.task3_network_db.screens.users_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task3_network_db.R
import com.example.task3_network_db.databinding.FragmentUsersListItemBinding
import com.example.task3_network_db.domain.model.User

class MyUserListAdapter(
    private val onItemClick: (user: User) -> Unit
) : ListAdapter<User, MyUserListAdapter.ViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentUsersListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldUser: User, newUser: User): Boolean {
            return oldUser.uuid == newUser.uuid
        }

        override fun areContentsTheSame(oldUser: User, newUser: User): Boolean {
            return oldUser == newUser
        }
    }

    inner class ViewHolder(binding: FragmentUsersListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val nameView: TextView = binding.name

        fun bind(user: User) {
            nameView.text =
                itemView.context.getString(R.string.user_full_name, user.firstName, user.lastName)
            nameView.setOnClickListener {
                onItemClick(user)
            }
        }
    }
}
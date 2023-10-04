package com.example.task3_network_db.screens.users_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task3_network_db.data.local.UserDao

class UsersListViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") if (modelClass.isAssignableFrom(UsersListViewModel::class.java)) {
            return UsersListViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
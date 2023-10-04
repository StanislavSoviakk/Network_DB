package com.example.task3_network_db.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task3_network_db.data.local.UserDao

class UserDetailsViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            return UserDetailsViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
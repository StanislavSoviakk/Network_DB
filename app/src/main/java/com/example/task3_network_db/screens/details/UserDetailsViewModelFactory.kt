package com.example.task3_network_db.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task3_network_db.domain.use_case.GetUserByIdUseCase

class UserDetailsViewModelFactory(private val getUserByIdUseCase: GetUserByIdUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            return UserDetailsViewModel(getUserByIdUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
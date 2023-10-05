package com.example.task3_network_db.screens.users_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task3_network_db.domain.use_case.GetUsersListUseCase

class UsersListViewModelFactory(private val getUsersListUseCase: GetUsersListUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") if (modelClass.isAssignableFrom(UsersListViewModel::class.java)) {
            return UsersListViewModel(getUsersListUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
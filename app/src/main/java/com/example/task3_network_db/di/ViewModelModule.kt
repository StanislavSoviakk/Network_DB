package com.example.task3_network_db.di

import com.example.task3_network_db.screens.details.UserDetailsViewModel
import com.example.task3_network_db.screens.users_list.UsersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { UsersListViewModel(getUsersListUseCase = get()) }

    viewModel { UserDetailsViewModel(getUserByIdUseCase = get()) }
}
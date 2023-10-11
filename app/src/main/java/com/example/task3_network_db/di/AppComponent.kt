package com.example.task3_network_db.di

import com.example.task3_network_db.screens.details.UserDetailsViewModelFactory
import com.example.task3_network_db.screens.users_list.UsersListViewModelFactory
import dagger.Component

@Component(
    modules = [DatabaseModule::class, NetworkModule::class, RepositoryModule::class, AppModule::class, UseCasesModule::class]
)
interface AppComponent {

    fun usersListViewModelFactory(): UsersListViewModelFactory

    fun userDetailsViewModelFactory(): UserDetailsViewModelFactory
}
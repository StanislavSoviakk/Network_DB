package com.example.task3_network_db.di

import com.example.task3_network_db.screens.details.UserDetailsFragment
import com.example.task3_network_db.screens.users_list.UsersListFragment
import dagger.Component

@Component(
    modules = [DatabaseModule::class, NetworkModule::class, RepositoryModule::class, AppModule::class, UseCasesModule::class]
)
interface AppComponent {

    fun injectUsersListFragment(usersListFragment: UsersListFragment)
    fun injectUserDetailFragment(userDetailFragment: UserDetailsFragment)
}
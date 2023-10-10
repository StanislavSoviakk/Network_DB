package com.example.task3_network_db.di

import com.example.task3_network_db.domain.use_case.GetUserByIdUseCase
import com.example.task3_network_db.domain.use_case.GetUsersListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetUsersListUseCase(randomUsersRepository = get(), randomUsersApi = get())
    }

    single {
        GetUserByIdUseCase(randomUsersRepository = get())
    }
}
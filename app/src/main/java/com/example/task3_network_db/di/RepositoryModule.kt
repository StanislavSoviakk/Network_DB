package com.example.task3_network_db.di

import com.example.task3_network_db.data.repository.RandomUsersRepositoryImpl
import com.example.task3_network_db.domain.repository.RandomUsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<RandomUsersRepository> {
        RandomUsersRepositoryImpl(dao = get())
    }
}
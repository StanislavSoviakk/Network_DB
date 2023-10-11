package com.example.task3_network_db.di

import com.example.task3_network_db.data.remote.RandomUsersApi
import com.example.task3_network_db.domain.repository.RandomUsersRepository
import com.example.task3_network_db.domain.use_case.GetUserByIdUseCase
import com.example.task3_network_db.domain.use_case.GetUsersListUseCase
import dagger.Module
import dagger.Provides

@Module
object UseCasesModule {

    @Provides
    fun provideGetUserByIdUseCase(randomUsersRepository: RandomUsersRepository): GetUserByIdUseCase {
        return GetUserByIdUseCase(randomUsersRepository)
    }

    @Provides
    fun provideUsersListUseCase(
        randomUsersRepository: RandomUsersRepository,
        randomUsersApi: RandomUsersApi
    ): GetUsersListUseCase {
        return GetUsersListUseCase(randomUsersRepository, randomUsersApi)
    }
}
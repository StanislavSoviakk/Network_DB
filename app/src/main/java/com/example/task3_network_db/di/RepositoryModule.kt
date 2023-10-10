package com.example.task3_network_db.di

import com.example.task3_network_db.data.repository.RandomUsersRepositoryImpl
import com.example.task3_network_db.domain.repository.RandomUsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun providesRandomUserRepository(repository: RandomUsersRepositoryImpl): RandomUsersRepository

}
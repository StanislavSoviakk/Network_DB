package com.example.task3_network_db.di

import com.example.task3_network_db.data.repository.RandomUsersRepositoryImpl
import com.example.task3_network_db.domain.repository.RandomUsersRepository
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {

    @Provides
    fun provideRandomUserRepository(repository: RandomUsersRepositoryImpl): RandomUsersRepository {
        return repository
    }

}
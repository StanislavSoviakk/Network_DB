package com.example.task3_network_db.di

import android.content.Context
import androidx.room.Room
import com.example.task3_network_db.data.local.UserDao
import com.example.task3_network_db.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDao(@ApplicationContext appContext: Context): UserDao {
        return Room.databaseBuilder(
            appContext, UserDatabase::class.java, "UsersDB"
        ).build().dao
    }
}
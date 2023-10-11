package com.example.task3_network_db.di

import android.content.Context
import androidx.room.Room
import com.example.task3_network_db.data.local.UserDao
import com.example.task3_network_db.data.local.UserDatabase
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @Provides
    fun providesDao(appContext: Context): UserDao {
        return Room.databaseBuilder(
            appContext, UserDatabase::class.java, "UsersDB"
        ).build().dao
    }
}
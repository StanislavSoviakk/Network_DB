package com.example.task3_network_db.di

import androidx.room.Room
import com.example.task3_network_db.data.local.UserDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(), UserDatabase::class.java, "UsersDB"
        ).build().dao
    }
}
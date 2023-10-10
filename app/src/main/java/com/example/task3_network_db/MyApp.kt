package com.example.task3_network_db

import android.app.Application
import com.example.task3_network_db.di.databaseModule
import com.example.task3_network_db.di.networkModule
import com.example.task3_network_db.di.repositoryModule
import com.example.task3_network_db.di.useCaseModule
import com.example.task3_network_db.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MyApp)
            modules(viewModelModule, useCaseModule, repositoryModule, networkModule, databaseModule)
        }

    }
}
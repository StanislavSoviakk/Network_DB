package com.example.task3_network_db

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.task3_network_db.di.AppComponent
import com.example.task3_network_db.di.AppModule
import com.example.task3_network_db.di.DaggerAppComponent

class MyApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(applicationContext)).build()
    }

}

fun Fragment.getAppComponent(): AppComponent =
    (requireContext().applicationContext as MyApp).appComponent

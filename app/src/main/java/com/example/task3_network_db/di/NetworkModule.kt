package com.example.task3_network_db.di

import com.example.task3_network_db.data.remote.RandomUsersApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://randomuser.me/"


@Module
object NetworkModule {

    @Provides
    fun providesApi(): RandomUsersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomUsersApi::class.java)
    }
}
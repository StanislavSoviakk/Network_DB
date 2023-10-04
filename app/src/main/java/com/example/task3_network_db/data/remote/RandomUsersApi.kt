package com.example.task3_network_db.data.remote

import com.example.task3_network_db.data.remote.dto.RandomUsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUsersApi {

    @GET("api/")
    suspend fun getRandomUsers(
        @Query("results") results: String
    ): Response<RandomUsersResponse>
}
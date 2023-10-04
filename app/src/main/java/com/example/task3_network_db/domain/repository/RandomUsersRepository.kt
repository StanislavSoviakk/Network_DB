package com.example.task3_network_db.domain.repository

import com.example.task3_network_db.data.remote.RandomUsersApi
import com.example.task3_network_db.domain.model.User

interface RandomUsersRepository {
    suspend fun getRandomUsers(api: RandomUsersApi, results: Int): Result<List<User>>
}
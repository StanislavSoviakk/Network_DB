package com.example.task3_network_db.domain.repository

import com.example.task3_network_db.data.remote.RandomUsersApi
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.utils.Resource

interface RandomUsersRepository {
    suspend fun getRandomUsers(api: RandomUsersApi, results: Int): Resource<List<User>>
}
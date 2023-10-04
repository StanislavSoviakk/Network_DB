package com.example.task3_network_db.domain.repository

import com.example.task3_network_db.data.local.UserDao
import com.example.task3_network_db.data.remote.RandomUsersApi
import com.example.task3_network_db.domain.model.User

interface RandomUsersRepository {
    suspend fun getRandomUsers(api: RandomUsersApi, results: Int, dao: UserDao): Result<List<User>>

    suspend fun getUserById(userId: String, dao: UserDao): User
}
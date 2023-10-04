package com.example.task3_network_db.data.repository

import com.example.task3_network_db.data.remote.RandomUsersApi
import com.example.task3_network_db.data.remote.dto.toUser
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.domain.repository.RandomUsersRepository
import java.io.IOException

class RandomUsersRepositoryImpl : RandomUsersRepository {
    override suspend fun getRandomUsers(api: RandomUsersApi, results: Int): Result<List<User>> {
        return try {
            val response = api.getRandomUsers(results.toString())
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Result.success(result.results.map { it.toUser() })
            } else {
                Result.failure(IOException(response.message()))
            }
        } catch (e: IOException) {
            Result.failure(e)
        }
    }

}
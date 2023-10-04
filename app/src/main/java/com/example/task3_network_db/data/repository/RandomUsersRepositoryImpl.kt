package com.example.task3_network_db.data.repository

import com.example.task3_network_db.data.remote.RandomUsersApi
import com.example.task3_network_db.data.remote.dto.toUser
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.domain.repository.RandomUsersRepository
import com.example.task3_network_db.utils.Resource

class RandomUsersRepositoryImpl : RandomUsersRepository {
    override suspend fun getRandomUsers(api: RandomUsersApi, results: Int): Resource<List<User>> {
        return try {
            val response = api.getRandomUsers(results.toString())
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result.results.map { it.toUser() })
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

}
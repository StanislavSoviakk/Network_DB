package com.example.task3_network_db.data.repository

import com.example.task3_network_db.data.local.UserDao
import com.example.task3_network_db.data.local.entity.toUser
import com.example.task3_network_db.data.remote.RandomUsersApi
import com.example.task3_network_db.data.remote.dto.toUser
import com.example.task3_network_db.data.remote.dto.toUserEntity
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.domain.repository.RandomUsersRepository
import java.io.IOException
import javax.inject.Inject

class RandomUsersRepositoryImpl @Inject constructor(private val dao: UserDao) :
    RandomUsersRepository {

    private var firstRequest = true
    override suspend fun getRandomUsers(
        api: RandomUsersApi, results: Int
    ): Result<List<User>> {
        return try {
            val response = api.getRandomUsers(results.toString())

            val result = response.body()?.results

            if (!result.isNullOrEmpty()) {
                if (firstRequest) {
                    dao.clearDB()
                    firstRequest = false
                }
                dao.insertUsers(result.map { it.toUserEntity() })
                Result.success(result.map { it.toUser() })
            } else {
                loadUsersFromLocalDB()
            }
        } catch (e: IOException) {
            loadUsersFromLocalDB()
        }
    }

    override suspend fun getUserById(userId: String): User {
        return dao.getUserById(userId).toUser()
    }

    private suspend fun loadUsersFromLocalDB(): Result<List<User>> {
        return runCatching {
            var users = listOf<User>()
            if (firstRequest) {
                firstRequest = false
                users = dao.getAllUsers().map { it.toUser() }
                if (users.isEmpty()) {
                    throw RuntimeException("Remote request failure")
                }
            }

            users
        }
    }
}
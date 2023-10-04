package com.example.task3_network_db.data.repository

import com.example.task3_network_db.data.local.UserDao
import com.example.task3_network_db.data.local.entity.toUser
import com.example.task3_network_db.data.remote.RandomUsersApi
import com.example.task3_network_db.data.remote.dto.toUser
import com.example.task3_network_db.data.remote.dto.toUserEntity
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.domain.repository.RandomUsersRepository
import java.io.IOException

class RandomUsersRepositoryImpl : RandomUsersRepository {
    override suspend fun getRandomUsers(
        api: RandomUsersApi, results: Int, dao: UserDao
    ): Result<List<User>> {
        return try {
            val response = api.getRandomUsers(results.toString())

            val result = response.body()?.results

            if (!result.isNullOrEmpty()) {
                dao.clearDB()
                dao.insertUsers(result.map { it.toUserEntity() })
                Result.success(result.map { it.toUser() })
            } else {
                loadUsersFromLocalDB(dao)
            }
        } catch (e: IOException) {
            loadUsersFromLocalDB(dao)
        }
    }

    private suspend fun loadUsersFromLocalDB(dao: UserDao): Result<List<User>> {
        return runCatching {
            val users = dao.getAllUsers().map { it.toUser() }
            if (users.isEmpty()) {
                throw RuntimeException("Remote request failure")
            }

            users
        }
    }
}
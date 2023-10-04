package com.example.task3_network_db.domain.use_case

import com.example.task3_network_db.data.local.UserDao
import com.example.task3_network_db.data.remote.RetrofitClient
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.domain.repository.RandomUsersRepository


class GetUsersListUseCase(
    private val randomUsersRepository: RandomUsersRepository,
    private val results: Int,
    private val dao: UserDao
) {
    suspend operator fun invoke(): Result<List<User>> {
        return randomUsersRepository.getRandomUsers(RetrofitClient.randomUsersApi, results, dao)
    }
}
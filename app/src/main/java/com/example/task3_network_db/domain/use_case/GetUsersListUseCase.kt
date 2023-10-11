package com.example.task3_network_db.domain.use_case

import com.example.task3_network_db.data.remote.RandomUsersApi
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.domain.repository.RandomUsersRepository


class GetUsersListUseCase(
    private val randomUsersRepository: RandomUsersRepository,
    private val randomUsersApi: RandomUsersApi
) {
    suspend operator fun invoke(results: Int): Result<List<User>> {
        return randomUsersRepository.getRandomUsers(randomUsersApi, results)
    }
}
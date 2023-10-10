package com.example.task3_network_db.domain.use_case

import com.example.task3_network_db.data.remote.RetrofitClient
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.domain.repository.RandomUsersRepository
import javax.inject.Inject

class GetUsersListUseCase @Inject constructor(
    private val randomUsersRepository: RandomUsersRepository
) {
    suspend operator fun invoke(results: Int): Result<List<User>> {
        return randomUsersRepository.getRandomUsers(RetrofitClient.randomUsersApi, results)
    }
}
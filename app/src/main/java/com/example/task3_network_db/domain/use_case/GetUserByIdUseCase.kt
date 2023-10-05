package com.example.task3_network_db.domain.use_case

import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.domain.repository.RandomUsersRepository

class GetUserByIdUseCase(
    private val randomUsersRepository: RandomUsersRepository
) {
    suspend operator fun invoke(userId: String): User {
        return randomUsersRepository.getUserById(userId)
    }
}
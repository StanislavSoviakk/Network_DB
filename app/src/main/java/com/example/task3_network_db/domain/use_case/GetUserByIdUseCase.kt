package com.example.task3_network_db.domain.use_case

import com.example.task3_network_db.data.local.UserDao
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.domain.repository.RandomUsersRepository

class GetUserByIdUseCase(
    private val userId: String,
    private val randomUsersRepository: RandomUsersRepository,
    private val dao: UserDao
) {
    suspend operator fun invoke(): User {
        return randomUsersRepository.getUserById(userId, dao)
    }
}
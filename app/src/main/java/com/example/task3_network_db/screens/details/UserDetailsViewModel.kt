package com.example.task3_network_db.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task3_network_db.data.local.UserDao
import com.example.task3_network_db.data.repository.RandomUsersRepositoryImpl
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.domain.use_case.GetUserByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailsViewModel(private val userDao: UserDao) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun loadUserById(userId: String) {
        val getUserByIdUseCase = GetUserByIdUseCase(userId, RandomUsersRepositoryImpl(), userDao)
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserByIdUseCase()
            _user.postValue(user)
        }
    }

}
package com.example.task3_network_db.screens.users_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task3_network_db.data.repository.RandomUsersRepositoryImpl
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.domain.use_case.GetUsersListUseCase
import com.example.task3_network_db.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersListViewModel : ViewModel() {

    private val getUsersListUseCase =
        GetUsersListUseCase(RandomUsersRepositoryImpl(), Constants.USERS_RESULT_NUMBER)

    private val _usersList = MutableLiveData<List<User>>()
    val usersList: LiveData<List<User>> = _usersList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val usersResponse = getUsersListUseCase()
            usersResponse.fold(
                onFailure = {
                    _isLoading.postValue(false)
                    _error.postValue(it.message)
                },
                onSuccess = {
                    _isLoading.postValue(false)
                    _usersList.postValue(it)

                }
            )
        }
    }
}
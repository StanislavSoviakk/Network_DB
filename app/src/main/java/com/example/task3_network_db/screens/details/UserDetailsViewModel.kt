package com.example.task3_network_db.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.task3_network_db.domain.model.User
import com.example.task3_network_db.domain.use_case.GetUserByIdUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailsViewModel @AssistedInject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase, @Assisted userId: String?
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init {
        loadUserById(userId)
    }

    private fun loadUserById(userId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (userId != null) {
                val user = getUserByIdUseCase(userId)
                _user.postValue(user)
            }
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(userId: String?): UserDetailsViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory, userId: String?
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(userId) as T
                }
            }
        }
    }

}
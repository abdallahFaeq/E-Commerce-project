package com.training.ecommercetrainingproject.ui.common

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.ecommercetrainingproject.data.repos.user.UserPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private var _userIdState = MutableStateFlow("")
    val userIdState = _userIdState.asStateFlow()

    val isUserLoggedInState = userPreferencesRepository
        .isUserLoggedIn
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false
        )

    init {
         viewModelScope.launch {
            Log.d("viewmodel","current thread: ${Thread.currentThread().name}")
             withContext(Dispatchers.IO){
                userPreferencesRepository.isUserLoggedIn
                Log.d("viewmodel","current thread: ${Thread.currentThread().name}")
            }
            Log.d("viewmodel","current thread: ${Thread.currentThread().name}")
        }
    }

    fun saveLoginState(
        isLoggedIn: Boolean
    ) {
        viewModelScope
            .launch {
                userPreferencesRepository
                    .saveLoginState(isLoggedIn)
            }
    }

    fun saveUserId(userId: String) {
        viewModelScope.launch {
            userPreferencesRepository.saveUserId(userId)
        }
    }

    fun getUserId() {
        viewModelScope.launch {
            userPreferencesRepository.userId.collect {
                _userIdState.value = it ?: ""
            }
        }
    }
}
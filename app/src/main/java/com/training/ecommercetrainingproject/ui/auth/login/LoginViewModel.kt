package com.training.ecommercetrainingproject.ui.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.training.ecommercetrainingproject.data.models.Resource
import com.training.ecommercetrainingproject.data.repos.auth.FirebaseAuthRepo
import com.training.ecommercetrainingproject.data.repos.user.UserPreferencesRepository
import com.training.ecommercetrainingproject.utils.isEmailValid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepo: FirebaseAuthRepo,
    private val userPreference: UserPreferencesRepository
) : ViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    private var _loginState = MutableSharedFlow<Resource<String>>()
    val loginState = _loginState.asSharedFlow()

    private val isLoginIsValid: Flow<Boolean> = combine(email, password) { email, password ->
        email.isEmailValid() && password.length >= 6
    }

    fun login() {
        viewModelScope.launch {
            if (isLoginIsValid.first()) {
                Log.e(TAG, "Login is valid")
                _loginState.emit(Resource.Loading())
                authRepo.loginWithEmailAndPassword(email.value, password.value)
                    .onEach { resource ->
                        // set login state is true
                        // save user id
                        when (resource) {
                            is Resource.Success -> {
                                Log.e(TAG, "Login Success")
                                _loginState.emit(Resource.Success(resource.data ?: "Empty User Id"))
                            }

                            else -> {
                                _loginState.emit(resource)
                            }
                        }
                    }.launchIn(viewModelScope)
            } else {
                _loginState.emit(Resource.Error(Exception("Invalid email or password")))
            }
        }
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }
}


class LoginViewModelFactory(
    private val authRepo: FirebaseAuthRepo,
    private val userPreference: UserPreferencesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(authRepo, userPreference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
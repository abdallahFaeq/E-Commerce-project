package com.training.ecommercetrainingproject.data.repos.user

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val isUserLoggedIn: Flow<Boolean>
    val userId: Flow<String?>
    suspend fun saveLoginState(isLoggedIn: Boolean)
    suspend fun saveUserId(userId: String)
}

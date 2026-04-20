package com.training.ecommercetrainingproject.data.repos.user

import com.training.ecommercetrainingproject.data.sources.datastore.AppPreferencesDataSource
import kotlinx.coroutines.flow.Flow

class UserPreferencesRepositoryImpl(
    private val appPreferencesDataSource: AppPreferencesDataSource
) : UserPreferencesRepository{
    override val isUserLoggedIn: Flow<Boolean>
        get() = appPreferencesDataSource.isUserLoggedIn
    override val userId: Flow<String?>
        get() = appPreferencesDataSource.userId

    override suspend fun saveLoginState(isLoggedIn: Boolean) {
            appPreferencesDataSource
                .saveLoginState(isLoggedIn)
    }

    override suspend fun saveUserId(userId: String) {
        appPreferencesDataSource
            .saveUserId(userId)
    }
}
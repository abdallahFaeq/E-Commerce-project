package com.training.ecommercetrainingproject.data.repos.auth

import com.training.ecommercetrainingproject.data.models.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepo {
    suspend fun loginWithEmailAndPassword(
        email: String,
        password: String): Flow<Resource<String>>
}
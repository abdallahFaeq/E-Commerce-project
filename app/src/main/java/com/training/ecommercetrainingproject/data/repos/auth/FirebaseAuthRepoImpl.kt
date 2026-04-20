package com.training.ecommercetrainingproject.data.repos.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.training.ecommercetrainingproject.data.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepoImpl(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) : FirebaseAuthRepo {
    override suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<String>> {
        return flow {
            try {
                emit(Resource.Loading())
                val authResult = auth.signInWithEmailAndPassword(email, password).await()
                authResult.user?.let { user ->
                    Log.d(TAG,"user found with id: ${user.uid}")
                    emit(Resource.Success(user.uid))
                } ?: run {
                    emit(Resource.Error(Exception("User not found")))
                }
            } catch (exception: Exception) {
                emit(Resource.Error(exception))
            }
        }
    }

    companion object {
        private const val TAG = "FirebaseAuthRepoImpl"
    }
}
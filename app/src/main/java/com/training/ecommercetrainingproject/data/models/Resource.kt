package com.training.ecommercetrainingproject.data.models

sealed class Resource<T>(
    var data:T?=null,
    var exception: Exception?=null
) {
    class Loading<T>(): Resource<T>()
    class Success<T>(data:T): Resource<T>(data)
    class Error<T>(exceptionMessage: Exception?=null): Resource<T>(exception = exceptionMessage)
}
package com.longtq.data.api.mapper

import com.longtq.data.api.model.UseResponse
import com.longtq.domain.entity.ApiError
import com.longtq.domain.entity.NetworkError
import com.longtq.domain.entity.User
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toGeneralError(): NetworkError {
    val error = when (this) {
        is IOException -> ApiError.NetworkError
        is HttpException -> ApiError.UnknownResponse
        else -> ApiError.UnknownError
    }
    return NetworkError(
        error = error,
        t = this
    )
}

fun UseResponse.toDomainModel(): User {
    return User(id, userName, email, password, avatar, displayName)
}
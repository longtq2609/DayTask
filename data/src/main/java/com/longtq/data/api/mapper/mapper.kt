package com.longtq.data.api.mapper

import com.longtq.data.api.model.UserResponse
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

fun UserResponse.toDomainModel(): User {
    return User(
        id = id,
        userName = userName,
        email = email,
        password = password,
        avatar = avatar,
        displayName = displayName,
        isChecked = false
    )
}


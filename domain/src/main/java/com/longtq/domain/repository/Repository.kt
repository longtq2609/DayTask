package com.longtq.domain.repository

import arrow.core.Either
import com.longtq.domain.entity.NetworkError
import com.longtq.domain.entity.User

interface Repository {
    suspend fun setIsFirstLaunch(isFirstLaunch: Boolean)
    suspend fun isFirstLaunch(): Boolean
    suspend fun register(user: User): Either<NetworkError, User>
    suspend fun getAllUsers(): Either<NetworkError, List<User>>
}
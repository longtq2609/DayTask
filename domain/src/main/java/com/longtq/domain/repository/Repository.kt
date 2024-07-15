package com.longtq.domain.repository

import arrow.core.Either
import com.longtq.domain.entity.NetworkError
import com.longtq.domain.entity.User

interface Repository {
    fun setIsFirstLaunch(isFirstLaunch: Boolean)
    fun isFirstLaunch(): Boolean
    suspend fun register(user: User): Either<NetworkError, User>

}
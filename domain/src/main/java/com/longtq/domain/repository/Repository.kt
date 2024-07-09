package com.longtq.domain.repository

import arrow.core.Either
import com.longtq.domain.entity.NetworkError
import com.longtq.domain.entity.User

interface Repository {
    suspend fun register(user: User): Either<NetworkError, User>
}
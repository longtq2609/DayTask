package com.longtq.data

import arrow.core.Either
import com.longtq.data.api.ApiService
import com.longtq.data.api.mapper.toDomainModel
import com.longtq.data.api.mapper.toGeneralError
import com.longtq.domain.entity.NetworkError
import com.longtq.domain.entity.User
import com.longtq.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {
    override suspend fun register(user: User): Either<NetworkError, User> {
        return Either.catch {
            apiService.register(user).toDomainModel()
        }.mapLeft { it.toGeneralError() }
    }
}
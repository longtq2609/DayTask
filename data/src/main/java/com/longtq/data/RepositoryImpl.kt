package com.longtq.data

import arrow.core.Either
import com.longtq.data.api.ApiService
import com.longtq.data.api.mapper.toDomainModel
import com.longtq.data.api.mapper.toGeneralError
import com.longtq.data.preference.AppPreferences
import com.longtq.domain.entity.NetworkError
import com.longtq.domain.entity.User
import com.longtq.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val sharedPreferences: AppPreferences
) : Repository {
    override suspend fun setIsFirstLaunch(isFirstLaunch: Boolean) {
        return sharedPreferences.setIsFirstLaunch(isFirstLaunch)
    }

    override suspend fun isFirstLaunch(): Boolean {
       return sharedPreferences.isFirstLaunch()
    }

    override suspend fun register(user: User): Either<NetworkError, User> {
        return Either.catch {
            apiService.register(user).toDomainModel()
        }.mapLeft { it.toGeneralError() }
    }

    override suspend fun getAllUsers(): Either<NetworkError, List<User>> {
        return Either.catch {
            apiService.getAllUsers().map {
                it.toDomainModel()
            }
        }.mapLeft { it.toGeneralError() }
    }
}
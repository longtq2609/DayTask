package com.longtq.domain.usecase

import arrow.core.Either
import com.longtq.domain.entity.NetworkError
import com.longtq.domain.entity.User
import com.longtq.domain.repository.Repository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(user: User): Either<NetworkError, User> {
        return repository.register(user)
    }
}
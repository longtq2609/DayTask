package com.longtq.domain.usecase

import com.longtq.domain.repository.Repository
import javax.inject.Inject

class CheckFirstLaunchUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Boolean {
        val isFirstLaunch = repository.isFirstLaunch()
        if (isFirstLaunch) {
            repository.setIsFirstLaunch(false)
        }
        return isFirstLaunch
    }

}
package com.longtq.data.di

import com.longtq.data.RepositoryImpl
import com.longtq.data.api.ApiService
import com.longtq.domain.repository.Repository
import com.longtq.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://day-task-server.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository {
        return RepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(repository: Repository): RegisterUseCase {
        return RegisterUseCase(repository)
    }
}
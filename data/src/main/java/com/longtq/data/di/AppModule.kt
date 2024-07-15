package com.longtq.data.di

import android.content.Context
import android.content.SharedPreferences
import com.longtq.data.RepositoryImpl
import com.longtq.data.api.ApiService
import com.longtq.data.preference.AppPreferences
import com.longtq.domain.repository.Repository
import com.longtq.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://day-task-server.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAppPreferences(sharedPreferences: SharedPreferences): AppPreferences {
        return AppPreferences(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService, appPreferences: AppPreferences): Repository {
        return RepositoryImpl(apiService, appPreferences)
    }


//    @Provides
//    @Singleton
//    fun provideRegisterUseCase(repository: Repository): RegisterUseCase {
//        return RegisterUseCase(repository)
//    }
}
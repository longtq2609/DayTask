package com.longtq.data.api

import com.longtq.data.api.model.UserResponse
import com.longtq.domain.entity.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("api/user/register")
    suspend fun register(@Body user: User): UserResponse

    @GET("api/user/get-all-user")
    suspend fun getAllUsers(): List<UserResponse>
}
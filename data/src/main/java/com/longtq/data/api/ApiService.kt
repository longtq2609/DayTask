package com.longtq.data.api

import com.longtq.data.api.model.UseResponse
import com.longtq.domain.entity.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("api/user/register")
    suspend fun register(@Body user: User): UseResponse

    @GET("api/user/get-all-user")
    suspend fun getAllUsers(): List<UseResponse>
}
package com.longtq.data.api

import com.longtq.data.api.model.UseResponse
import com.longtq.domain.entity.User
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("v1/user")
    suspend fun register(@Body user: User): UseResponse
}
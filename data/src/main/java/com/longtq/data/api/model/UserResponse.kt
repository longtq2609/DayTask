package com.longtq.data.api.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("user_name") val userName: String? = "",
    @SerializedName("email") val email: String? = "",
    @SerializedName("password") val password: String? = "",
    @SerializedName("display_name") val displayName: String? = "",
    @SerializedName("avatar") val avatar: String? = ""
)
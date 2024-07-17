package com.longtq.domain.entity


data class User(
    val id: String? = "",
    val userName: String? = "",
    var email: String? = "",
    val password: String? = "",
    val displayName: String? = "",
    val avatar: String? = "",
    var isChecked: Boolean? = false
)



package com.longtq.daytask.screen.register

data class RegisterViewState(
    var isLoading: Boolean = false,
    var fullName: String? = "",
    var email: String? = "",
    var password: String? = "",
)
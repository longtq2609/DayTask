package com.longtq.daytask.screen.login

data class LoginViewState(
    val isLoading: Boolean = false,
    val isLoginSuccess: Boolean = false,
    val email: String? = "",
    val password: String? = "",
)
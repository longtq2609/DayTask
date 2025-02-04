package com.longtq.daytask.screen.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val loginNavigation = "loginNavigation"

fun NavController.navigationToLogin() {
    navigate(loginNavigation)
}


fun NavGraphBuilder.loginScreen(
    onClickRegister: () -> Unit,
    onNavigationToMain: () -> Unit
) {
    composable(route = loginNavigation) {
        LoginScreen(onClickRegister, onNavigationToMain)
    }
}
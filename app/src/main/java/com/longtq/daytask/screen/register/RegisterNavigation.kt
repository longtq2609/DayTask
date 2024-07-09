package com.longtq.daytask.screen.register

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val registerNavigation = "registerNavigation"

fun NavController.navigationToRegister() {
    navigate(registerNavigation)
}

fun NavGraphBuilder.registerScreen(
    onBackPress: () -> Unit
) {
    composable(route = registerNavigation) {
        RegisterScreen(onBackPress = onBackPress)
    }
}
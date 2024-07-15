package com.longtq.daytask.screen.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val splashNavigation = "splashNavigation"

fun NavController.navigationToSplash() {
    navigate(splashNavigation)
}

fun NavGraphBuilder.splashScreen(
    onNavigationToLogin: () -> Unit
) {
    composable(route = splashNavigation) {
        SplashScreen(onNavigationToLogin)
    }
}
package com.longtq.daytask.screen.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val mainNavigation = "mainNavigation"

fun NavController.navigationToMain() {
    navigate(mainNavigation)
}

fun NavGraphBuilder.mainScreen(
) {
    composable(route = mainNavigation) {
        MainScreen()
    }
}


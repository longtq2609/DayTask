package com.longtq.daytask.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val homeNavigation = "homeNavigation"

fun NavController.navigationToHome(
    navOptions: NavOptions? = null

) {
    navigate(homeNavigation, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onNavigationToProfile: () -> Unit,
) {
    composable(route = homeNavigation) {
        HomeScreen(onNavigationToProfile)
    }
}
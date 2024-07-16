package com.longtq.daytask.screen.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val profileNavigation = "profileNavigation"

fun NavController.navigateToProfile() {
    navigate(profileNavigation)
}

fun NavGraphBuilder.profileScreen(
    onBackPress: () -> Unit
) {
    composable(route = profileNavigation) {
        ProfileScreen(onBackPress)
    }
}
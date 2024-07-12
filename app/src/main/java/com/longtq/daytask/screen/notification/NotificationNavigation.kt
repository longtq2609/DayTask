package com.longtq.daytask.screen.notification

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val notificationNavigation = "notificationNavigation"

fun NavController.navigationToNotification(
    navOptions: NavOptions? = null

) {
    navigate(notificationNavigation, navOptions)
}

fun NavGraphBuilder.notificationScreen(

) {
    composable(route = notificationNavigation) {
        NotificationScreen()
    }
}
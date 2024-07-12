package com.longtq.daytask.screen.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val chatNavigation = "chatNavigation"

fun NavController.navigationToChat(
    navOptions: NavOptions? = null

) {
    navigate(chatNavigation, navOptions)
}

fun NavGraphBuilder.chatScreen(
    navOptions: NavOptions? = null

) {
    composable(route = chatNavigation) {
        ChatScreen()
    }
}
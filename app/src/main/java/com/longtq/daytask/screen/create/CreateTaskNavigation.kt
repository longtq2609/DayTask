package com.longtq.daytask.screen.create

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val createTaskNavigation = "createTaskNavigation"

fun NavController.navigationToCreateTask(
    navOptions: NavOptions? = null

) {
    navigate(createTaskNavigation, navOptions)
}

fun NavGraphBuilder.createTaskScreen(

) {
    composable(route = createTaskNavigation) {
        CreateTaskScreen()
    }
}
package com.longtq.daytask.screen.create

import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.createTaskScreen(
    onBackClick: () -> Unit,

) {
    composable(route = createTaskNavigation) {
        CreateTaskScreen(onBackClick = onBackClick)
    }
}
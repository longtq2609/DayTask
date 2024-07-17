package com.longtq.daytask.screen.addmember

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.longtq.daytask.activity.MainViewModel

const val addMemberNavigation = "addMemberNavigation"

fun NavController.navigationToAddMember() {
    navigate(addMemberNavigation)
}

fun NavGraphBuilder.addMemberScreen(
    onBackClick: () -> Unit,
    mainViewModel: MainViewModel
) {
    composable(addMemberNavigation) {
        AddMemberScreen(onBackClick, mainViewModel)
    }
}
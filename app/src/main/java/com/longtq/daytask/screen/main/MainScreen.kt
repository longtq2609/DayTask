package com.longtq.daytask.screen.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.longtq.daytask.screen.calendar.calendarScreen
import com.longtq.daytask.screen.chat.chatScreen
import com.longtq.daytask.screen.create.createTaskScreen
import com.longtq.daytask.screen.home.homeNavigation
import com.longtq.daytask.screen.home.homeScreen
import com.longtq.daytask.screen.notification.notificationScreen
import com.longtq.daytask.screen.profile.navigateToProfile
import com.longtq.daytask.util.components.AppBottomBar
import com.longtq.daytask.util.components.LoadingDialog

@Composable
fun MainScreen(
    navController: NavController
) {
    val viewModel: MainViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    MainView(
        state = state,
        navController = navController
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainView(
    state: State<MainViewState>,
    navController: NavController
) {
    LoadingDialog(isLoading = state.value.isLoading)

    val navAnimatedController = rememberAnimatedNavController()
    Scaffold(
        bottomBar = {
            AppBottomBar(navController = navAnimatedController)

        },

        ) { innerPadding ->
        AnimatedNavHost(
            navController = navAnimatedController,
            startDestination = homeNavigation,
            Modifier.padding(innerPadding)
        ) {
            homeScreen { navController.navigateToProfile() }
            chatScreen()
            createTaskScreen()
            calendarScreen()
            notificationScreen()
        }

    }
}


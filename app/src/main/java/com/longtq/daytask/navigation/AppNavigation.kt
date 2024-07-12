package com.longtq.daytask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.longtq.daytask.screen.calendar.calendarScreen
import com.longtq.daytask.screen.chat.chatScreen
import com.longtq.daytask.screen.home.homeScreen
import com.longtq.daytask.screen.login.loginNavigation
import com.longtq.daytask.screen.login.loginScreen
import com.longtq.daytask.screen.main.mainScreen
import com.longtq.daytask.screen.notification.navigationToNotification
import com.longtq.daytask.screen.notification.notificationScreen
import com.longtq.daytask.screen.register.navigationToRegister
import com.longtq.daytask.screen.register.registerScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = loginNavigation
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        loginScreen(
            onClickRegister = { navController.navigationToRegister() },
            onNavigationToMain = { navController.navigationToNotification() }
        )
        registerScreen { navController.popBackStack() }
        mainScreen()
        homeScreen()
        calendarScreen()
        chatScreen()
        notificationScreen()
    }
}
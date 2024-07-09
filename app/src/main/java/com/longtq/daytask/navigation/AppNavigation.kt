package com.longtq.daytask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.longtq.daytask.screen.login.loginNavigation
import com.longtq.daytask.screen.login.loginScreen
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
        loginScreen { navController.navigationToRegister() }
        registerScreen { navController.popBackStack() }
    }
}
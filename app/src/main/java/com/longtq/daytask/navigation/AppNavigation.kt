package com.longtq.daytask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.longtq.daytask.screen.login.loginNavigation
import com.longtq.daytask.screen.login.loginScreen
import com.longtq.daytask.screen.login.navigationToLogin
import com.longtq.daytask.screen.main.mainScreen
import com.longtq.daytask.screen.main.navigationToMain
import com.longtq.daytask.screen.profile.profileScreen
import com.longtq.daytask.screen.register.navigationToRegister
import com.longtq.daytask.screen.register.registerScreen
import com.longtq.daytask.screen.splash.splashScreen

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
        splashScreen { navController.navigationToLogin() }
        loginScreen(
            onClickRegister = { navController.navigationToRegister() },
            onNavigationToMain = { navController.navigationToMain() }
        )
        registerScreen { navController.popBackStack() }
        mainScreen(navController)
        profileScreen { navController.popBackStack() }
    }
}
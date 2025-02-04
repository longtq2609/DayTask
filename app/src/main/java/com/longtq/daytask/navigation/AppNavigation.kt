package com.longtq.daytask.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.longtq.daytask.activity.MainViewModel
import com.longtq.daytask.screen.addmember.addMemberScreen
import com.longtq.daytask.screen.addmember.navigationToAddMember
import com.longtq.daytask.screen.create.createTaskScreen
import com.longtq.daytask.screen.login.loginNavigation
import com.longtq.daytask.screen.login.loginScreen
import com.longtq.daytask.screen.login.navigationToLogin
import com.longtq.daytask.screen.main.mainScreen
import com.longtq.daytask.screen.main.navigationToMain
import com.longtq.daytask.screen.profile.profileScreen
import com.longtq.daytask.screen.register.navigationToRegister
import com.longtq.daytask.screen.register.registerScreen
import com.longtq.daytask.screen.splash.splashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = hiltViewModel(),
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
        createTaskScreen(mainViewModel = mainViewModel, onBackClick = {
            navController.popBackStack()
        },
            onNavigationToAddMember = {
                navController.navigationToAddMember()
            })
        addMemberScreen(
            mainViewModel = mainViewModel,
            onBackClick = { navController.popBackStack() })
    }
}
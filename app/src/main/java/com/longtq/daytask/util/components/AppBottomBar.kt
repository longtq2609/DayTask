package com.longtq.daytask.util.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.trace
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.longtq.daytask.R
import com.longtq.daytask.navigation.MainNavigation
import com.longtq.daytask.screen.calendar.navigationToCalendar
import com.longtq.daytask.screen.chat.navigationToChat
import com.longtq.daytask.screen.create.navigationToCreateTask
import com.longtq.daytask.screen.home.navigationToHome
import com.longtq.daytask.screen.notification.navigationToNotification
import com.longtq.daytask.ui.theme.darkBlue
import com.longtq.daytask.ui.theme.orangeYellow
import com.longtq.daytask.ui.theme.slateBlue

@Composable
fun AppBottomBar(
    navController: NavController
) {
    val item = listOf(
        MainNavigation.HOME,
        MainNavigation.CHAT,
        MainNavigation.CREATE_TASK,
        MainNavigation.CALENDAR,
        MainNavigation.NOTIFICATION
    )

    BottomNavigation(
        backgroundColor = darkBlue,
    ) {
        val vanBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = vanBackStackEntry?.destination?.route

        item.map {
            if (it.route == MainNavigation.CREATE_TASK.route) {

                BottomNavigationItem(
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.dimen_8),
                        bottom = dimensionResource(id = R.dimen.dimen_8)
                    ),
                    selected = currentRoute == it.route,
                    onClick = {
                        navController.navigate(it.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen.dimen_48))
                                .background(orangeYellow)

                        ) {
                            Icon(
                                painter = painterResource(id = it.icon),
                                contentDescription = stringResource(id = it.title)
                            )
                        }
                    })
            } else {
                BottomNavigationItem(
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.dimen_8),
                        bottom = dimensionResource(id = R.dimen.dimen_8)
                    ),
                    selectedContentColor = orangeYellow,
                    unselectedContentColor = slateBlue,
                    label = {
                        AppText(
                            text = stringResource(id = it.title),
                            fontWeight = FontWeight.Normal,
                            fontSize = dimensionResource(
                                id = R.dimen.dimen_10
                            ).value.sp,
                            color = if (currentRoute == it.route) orangeYellow else slateBlue
                        )
                    },
                    selected = currentRoute == it.route,
                    onClick = {
                        navigateToBottomNavDestination(it, navController)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = stringResource(id = it.title)
                        )
                    })
            }

        }
    }


}

fun navigateToBottomNavDestination(bottomNav: MainNavigation, navController: NavController) {
    trace("Navigation: ${bottomNav.name}") {
        val bottomNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (bottomNav) {
            MainNavigation.HOME -> navController.navigationToHome(bottomNavOptions)
            MainNavigation.CHAT -> navController.navigationToChat(bottomNavOptions)
            MainNavigation.CREATE_TASK -> navController.navigationToCreateTask(bottomNavOptions)
            MainNavigation.CALENDAR -> navController.navigationToCalendar(bottomNavOptions)
            MainNavigation.NOTIFICATION -> navController.navigationToNotification(bottomNavOptions)
        }
    }
}

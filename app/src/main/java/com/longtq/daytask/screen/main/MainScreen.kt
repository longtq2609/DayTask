package com.longtq.daytask.screen.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.longtq.daytask.screen.calendar.calendarNavigation
import com.longtq.daytask.screen.calendar.calendarScreen
import com.longtq.daytask.screen.chat.chatNavigation
import com.longtq.daytask.screen.chat.chatScreen
import com.longtq.daytask.screen.home.HomeScreen
import com.longtq.daytask.screen.home.homeNavigation
import com.longtq.daytask.screen.notification.notificationNavigation
import com.longtq.daytask.screen.notification.notificationScreen
import com.longtq.daytask.ui.theme.black
import com.longtq.daytask.ui.theme.orangeYellow

@Composable
fun MainScreen(
) {
    val viewModel: MainViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    MainView(state = state)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainView(
    state: State<MainViewState>,
) {
    val navController = rememberAnimatedNavController()

    Scaffold(
        bottomBar = {

            Box {
//                AppBottomBar(modifier = Modifier.align(Alignment.BottomCenter), navController)
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(bottom = 35.dp)
                        .clip(CircleShape)
                        .background(orangeYellow)
                        .align(Alignment.BottomCenter)
                        .padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "add",
                        tint = black,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        },

        ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = homeNavigation,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(homeNavigation) { HomeScreen() } // Corrected
            composable(chatNavigation) { chatScreen() } // Corrected
            composable(calendarNavigation) { calendarScreen() } // Corrected
            composable(notificationNavigation) { notificationScreen() } // Corrected
        }

    }
}


package com.longtq.daytask.screen.calendar

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val calendarNavigation = "calendarNavigation"

fun NavController.navigationToCalendar(
    navOptions: NavOptions? = null

) {
    navigate(calendarNavigation, navOptions)
}

fun NavGraphBuilder.calendarScreen(

) {
    composable(route = calendarNavigation) {
        CalendarScreen()
    }
}
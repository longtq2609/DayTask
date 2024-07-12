package com.longtq.daytask.navigation

import androidx.annotation.StringRes
import com.longtq.daytask.R
import com.longtq.daytask.screen.calendar.calendarNavigation
import com.longtq.daytask.screen.chat.chatNavigation
import com.longtq.daytask.screen.home.homeNavigation
import com.longtq.daytask.screen.notification.notificationNavigation

enum class MainNavigation(
    val route: String,
    val icon: Int,
    @StringRes val title: Int
) {
    HOME(
        homeNavigation,
        R.drawable.ic_home,
        R.string.title_home
    ),
    CHAT(
        chatNavigation,
        R.drawable.ic_message,
        R.string.title_chat
    ),
    CALENDAR(
        calendarNavigation,
        R.drawable.ic_calendar,
        R.string.title_calendar
    ),
    NOTIFICATION(
        notificationNavigation,
        R.drawable.ic_notification,
        R.string.title_notification
    )
}
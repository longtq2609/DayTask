package com.longtq.daytask.screen.create

import com.longtq.domain.entity.User

data class CreateTaskViewState(
    val isLoading: Boolean = false,
    val listUsers: List<User> = emptyList(),
    val titleTask: String = "",
    val taskDetail: String = "",
    val timeNow: String = "",
    val dateNow: String = "",
)
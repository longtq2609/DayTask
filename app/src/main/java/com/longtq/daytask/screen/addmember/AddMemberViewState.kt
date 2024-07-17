package com.longtq.daytask.screen.addmember

import com.longtq.domain.entity.User

data class AddMemberViewState(
    val isLoading: Boolean = false,
    val searchUser: String = "",
    val listUser: List<User> = emptyList(),
    val listUserSelected: List<User> = emptyList()
)
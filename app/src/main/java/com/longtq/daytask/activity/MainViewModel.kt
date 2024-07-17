package com.longtq.daytask.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longtq.domain.entity.User
import com.longtq.domain.usecase.CheckFirstLaunchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkFirstLaunchUseCase: CheckFirstLaunchUseCase
) : ViewModel() {

    private val _isFirstLaunch = MutableStateFlow(true)
    val isFirstLaunch: StateFlow<Boolean> = _isFirstLaunch.asStateFlow()

    private var _listMemberState = MutableStateFlow<List<User>>(emptyList())
    val listMember = _listMemberState.asStateFlow()


    init {
        viewModelScope.launch {
            _isFirstLaunch.value = checkFirstLaunchUseCase()
        }
    }

    fun setListMemberSelected(member: List<User>) {
        _listMemberState.value = member
    }

    fun removeMemberSelected(member: User) {
        val newList = _listMemberState.value.filter { it.id != member.id }
        _listMemberState.value = newList
    }
}
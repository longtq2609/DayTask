package com.longtq.daytask.screen.create

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(CreateTaskViewState())
    val state = _state.asStateFlow()

}
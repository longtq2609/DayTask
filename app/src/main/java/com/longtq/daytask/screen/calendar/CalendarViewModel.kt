package com.longtq.daytask.screen.calendar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(CalendarViewState())
    val state = _state.asStateFlow()

}
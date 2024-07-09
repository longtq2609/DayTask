package com.longtq.daytask.until

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longtq.daytask.util.EventBus
import kotlinx.coroutines.launch

fun ViewModel.sendEvent(event: Any) {
    viewModelScope.launch {
        EventBus.sendEvent(event)
    }
}
package com.longtq.daytask.screen.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longtq.daytask.util.Event
import com.longtq.daytask.util.sendEvent
import com.longtq.domain.usecase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CreateTaskViewState())
    val state = _state.asStateFlow()

    init {
        getAllUsers()
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            updateIsLoading(true)
            getAllUsersUseCase.invoke()
                .map {
                    updateIsLoading(false)

                }.mapLeft { error ->
                    updateIsLoading(false)
                    sendEvent(Event.Toast(error.error.message))
                }
        }
    }

    private fun updateIsLoading(isLoading: Boolean) {
        _state.update {
            it.copy(isLoading = isLoading)
        }
    }
}

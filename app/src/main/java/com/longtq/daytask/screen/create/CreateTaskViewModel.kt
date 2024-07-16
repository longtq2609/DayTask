package com.longtq.daytask.screen.create

import android.util.Log
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
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CreateTaskViewState())
    val state = _state.asStateFlow()

    init {
        getTimeAndDataNow()
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

    fun onChangeTitleText(titleTask: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(titleTask = titleTask)
            }
        }
    }

    fun onChangeTaskDetail(taskDetail: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(taskDetail = taskDetail)
            }
        }
    }

    fun onCreateTask() {

    }

    private fun getTimeAndDataNow() {
        val timeFormat: DateFormat = SimpleDateFormat("hh:mm a")
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = Date()
        val timeFormatted = timeFormat.format(date)
        val dateFormatted = dateFormat.format(date)
        viewModelScope.launch {
            _state.update {
                it.copy(
                    timeNow = timeFormatted,
                    dateNow = dateFormatted
                )
            }
        }
    }

    fun removeMember(id: String) {
        Log.e("longtq", "removeMember: $id")
    }

    private fun updateIsLoading(isLoading: Boolean) {
        _state.update {
            it.copy(isLoading = isLoading)
        }
    }
}

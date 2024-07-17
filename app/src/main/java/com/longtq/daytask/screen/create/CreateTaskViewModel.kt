package com.longtq.daytask.screen.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longtq.domain.entity.User
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
) : ViewModel() {
    private val _state = MutableStateFlow(CreateTaskViewState())
    val state = _state.asStateFlow()

    init {
        getTimeAndDataNow()
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

    fun onChangeDate(date: Date) {
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateSelected = dateFormat.format(date)
        viewModelScope.launch {
            _state.update {
                it.copy(
                    dateNow = dateSelected
                )
            }
        }
    }

    fun onChangeTime(time: Date) {
        val timeFormat: DateFormat = SimpleDateFormat("hh:mm a")
        val timeSelected = timeFormat.format(time)
        viewModelScope.launch {
            _state.update {
                it.copy(
                    timeNow = timeSelected
                )
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

    fun updateMember(user: List<User>) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    listUsers = user
                )
            }
        }
    }

    private fun updateIsLoading(isLoading: Boolean) {
        _state.update {
            it.copy(isLoading = isLoading)
        }
    }
}

package com.longtq.daytask.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longtq.daytask.until.sendEvent
import com.longtq.daytask.util.Event
import com.longtq.domain.entity.User
import com.longtq.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterViewState())
    val state = _state.asStateFlow()

    fun onChangeEmailAddress(email: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(email = email)
        }
    }

    fun onChangePassword(password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(password = password)
        }
    }

    fun onChangeFullName(fullName: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(fullName = fullName)
        }
    }

    fun onClickLoginGoogle() {

    }

    fun onClickRegister() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val user = User(
                email = _state.value.email,
                password = _state.value.password,
                userName = _state.value.fullName,
            )
            registerUseCase.invoke(user)
                .map {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    sendEvent(Event.ShowDialog("Register success", "Register success"))
                }
                .mapLeft { error ->
                    sendEvent(Event.Toast(error.error.message))

                }
        }
    }
}
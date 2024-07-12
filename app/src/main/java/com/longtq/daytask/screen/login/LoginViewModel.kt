package com.longtq.daytask.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longtq.daytask.until.sendEvent
import com.longtq.daytask.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : ViewModel() {
    private val _state = MutableStateFlow(LoginViewState())
    val state = _state.asStateFlow()

    fun login() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            if (!isValidEmail(_state.value.email ?: "")) {
                sendEvent(
                    Event.ShowDialog(
                        "Email Error",
                        message = "This email address is not in the correct format"
                    )
                )
                _state.update {
                    it.copy(isLoading = false)
                }
                return@launch
            }
            _state.update {
                it.copy(isLoading = false)
            }
            sendEvent(Event.ShowDialog("Login Success", message = "Password is correct"))

        }
    }

    fun loginGoogle() {
        viewModelScope.launch {
            sendEvent(
                Event.ShowDialog(
                    "Login Google Success",
                    message = "Password Google is correct"
                )
            )

        }
    }

    fun onChangeEmailAddress(email: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(email = email)
            }
        }
    }

    fun onChangePassword(password: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(email = password)
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        return emailPattern.matches(email)
    }
}
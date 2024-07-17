package com.longtq.daytask.screen.addmember

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longtq.daytask.util.Event
import com.longtq.daytask.util.sendEvent
import com.longtq.domain.entity.User
import com.longtq.domain.usecase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMemberViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,

    ) : ViewModel() {
    private val _state = MutableStateFlow(AddMemberViewState())
    val state = _state.asStateFlow()


    fun getAllUsers(user: List<User>) {
        viewModelScope.launch {
            updateIsLoading(true)
            getAllUsersUseCase.invoke()
                .map { listUser ->
                    updateIsLoading(false)
//                        _state.update {
//                            it.copy(listUser = listUser)
//                        }
                    val updatedListUser = listUser.map { listUserItem ->
                        user.find { userItem -> userItem.id == listUserItem.id }
                            ?.let { matchingUser ->
                                listUserItem.copy(isChecked = matchingUser.isChecked)
                            } ?: listUserItem
                    }

                    _state.update { it.copy(listUser = updatedListUser) }
                }.mapLeft { error ->
                    updateIsLoading(false)
                    sendEvent(Event.Toast(error.error.message))
                }
        }
    }

    fun onSearchUserChanged(query: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    searchUser = query
                )
            }
        }
    }

    fun updateMember(updateUser: User) {
        viewModelScope.launch {
            val updatedList = _state.value.listUser.map {
                if (it.id == updateUser.id) {
                    updateUser
                } else {
                    it
                }
            }
            _state.update {
                it.copy(
                    listUser = updatedList
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
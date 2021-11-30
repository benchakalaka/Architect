package com.datascope.architect.bc.ui.viewmodel

import com.datascope.architect.bc.domain.User
import com.datascope.architect.vmcore.UiEvent
import com.datascope.architect.vmcore.UiState

sealed class UserUiState : UiState() {
    object Initial : UserUiState()
    data class TextChanged(val newValue: String) : UserUiState()
    data class UsersFetched(val users: List<User>) : UserUiState()
}

sealed class UserUiEvent : UiEvent() {
    object ShowToast : UserUiEvent()
}
package com.datascope.architect.bc.viewmodel

import com.datascope.architect.vmcore.UiEvent
import com.datascope.architect.vmcore.UiState

sealed class UserUiState : UiState() {
    object Initial : UserUiState()
    data class TextChanged(val newValue: String) : UserUiState()
}

sealed class UserUiEvent : UiEvent() {
    object ShowToast : UserUiEvent()
}
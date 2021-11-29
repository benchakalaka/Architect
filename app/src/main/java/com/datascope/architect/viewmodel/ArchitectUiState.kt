package com.datascope.architect.viewmodel

import com.datascope.architect.vmcore.UiState

sealed class ArchitectUiState : UiState() {
    object Initial : ArchitectUiState()
}
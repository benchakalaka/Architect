package com.datascope.architect.viewmodel

import com.datascope.architect.vmcore.UiAction

sealed class ArchitectUiAction : UiAction() {
    object ShowToast : ArchitectUiAction()
}
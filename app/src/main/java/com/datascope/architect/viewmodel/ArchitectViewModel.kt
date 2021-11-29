package com.datascope.architect.viewmodel


import com.datascope.architect.vmcore.BetterViewModel

class ArchitectViewModel : BetterViewModel<ArchitectUiState, ArchitectUiAction>() {

    override fun initialState() = ArchitectUiState.Initial

    fun doTheThing() = call {
        actions.emit(ArchitectUiAction.ShowToast)
    }
}
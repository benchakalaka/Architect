package com.datascope.architect.vmcore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BetterViewModel<TState : UiState, TAction : UiAction> : ViewModel() {
    protected val states by lazy { MutableStateFlow(initialState()) }
    val uiState = states.asStateFlow()
    abstract fun initialState(): TState

    protected val actions = MutableSharedFlow<TAction>(replay = 0)
    val uiAction: SharedFlow<TAction>
        get() = actions

    protected fun call(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }
}
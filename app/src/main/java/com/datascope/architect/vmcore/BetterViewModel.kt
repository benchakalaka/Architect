package com.datascope.architect.vmcore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

open class BetterViewModel<TUiState : UiState, TUiEvent : UiEvent>(initialState: TUiState) :
    ViewModel() {

    protected val _states by lazy { MutableStateFlow(initialState) }
    val uiStates = _states.asStateFlow()

    protected val _events by lazy { MutableSharedFlow<TUiEvent>(replay = 0) }
    val uiEvents = _events.asSharedFlow()

    protected fun call(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    protected fun event(block: suspend CoroutineScope.() -> TUiEvent) {
        viewModelScope.launch { _events.emit(block()) }
    }

    protected fun state(block: suspend CoroutineScope.() -> TUiState) {
        viewModelScope.launch { _states.emit(block()) }
    }
}
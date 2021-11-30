package com.datascope.architect.bc.viewmodel


import com.datascope.architect.vmcore.BetterViewModel
import kotlin.random.Random

class UserViewModel :
    BetterViewModel<UserUiState, UserUiEvent>(UserUiState.Initial) {

    init {
        changeTextExample1()
    }

    fun showToastExample1() = call {
        _events.emit(UserUiEvent.ShowToast)
    }

    fun showToastExample2() = event {
        UserUiEvent.ShowToast
    }

    fun changeTextExample1() = call {
        _states.emit(UserUiState.TextChanged(Random.nextInt().toString()))
    }

    fun changeTextExample2() = state {
        UserUiState.TextChanged(Random.nextInt().toString())
    }
}
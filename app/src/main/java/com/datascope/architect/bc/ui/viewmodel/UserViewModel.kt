package com.datascope.architect.bc.ui.viewmodel


import com.datascope.architect.bc.storage.repository.IUserRepository
import com.datascope.architect.vmcore.BetterViewModel
import kotlin.random.Random

class UserViewModel(private val repo: IUserRepository) :
    BetterViewModel<UserUiState, UserUiEvent>(UserUiState.Initial) {

    init {
        changeTextExample1()
    }

    fun showToastExample1() = call {
        _events.emit(UserUiEvent.ShowToast)
    }

    fun showToastExample2() = emitEvent {
        UserUiEvent.ShowToast
    }

    fun changeTextExample1() = call {
        //_states.emit(State.Loading)
        // call service suspend

        _states.emit(UserUiState.TextChanged(Random.nextInt().toString()))
    }

    fun changeTextExample2() = emitState {
        UserUiState.TextChanged(Random.nextInt().toString())
    }

    fun getUsers() = emitState {
        val users = repo.getUsers()
        UserUiState.TextChanged("AABBCC")
    }
}
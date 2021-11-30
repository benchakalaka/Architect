package com.datascope.architect

import android.widget.Toast
import com.datascope.architect.databinding.ActivityMainBinding
import com.datascope.architect.bc.viewmodel.UserUiEvent
import com.datascope.architect.bc.viewmodel.UserUiState
import com.datascope.architect.bc.viewmodel.UserViewModel

// TODO: move ViewModelClass to reflection to BetterActivity
// TODO: add real service to fetch data

class MainActivity : BetterActivity<
        UserUiEvent,
        UserUiState,
        UserViewModel,
        ActivityMainBinding>(UserViewModel::class) {

    override fun ActivityMainBinding.renderUi() {
        btn.setOnClickListener { viewModel.showToastExample2() }
        btnText.setOnClickListener { viewModel.changeTextExample2() }
    }

    override fun onUiState(state: UserUiState) = when (state) {
        is UserUiState.Initial -> {}
        is UserUiState.TextChanged -> updateText(state)
    }

    override fun onUiEvent(event: UserUiEvent) = when (event) {
        is UserUiEvent.ShowToast -> showToast()
    }

    private fun updateText(state: UserUiState.TextChanged) {
        ui.btnText.text = state.newValue
    }

    private fun showToast() {
        Toast.makeText(this, "New event ", Toast.LENGTH_SHORT)
            .show()
    }
}
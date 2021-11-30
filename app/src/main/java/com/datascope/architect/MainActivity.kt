package com.datascope.architect

import android.widget.Toast
import com.datascope.architect.bc.ui.viewmodel.UserUiEvent
import com.datascope.architect.bc.ui.viewmodel.UserUiState
import com.datascope.architect.bc.ui.viewmodel.UserViewModel
import com.datascope.architect.databinding.ActivityMainBinding

// TODO: move ViewModelClass to reflection to BetterActivity
// TODO: add real service to fetch data
// TODO: investigate UI binding

class MainActivity : BetterActivity<
        UserUiEvent,
        UserUiState,
        UserViewModel,
        ActivityMainBinding>(UserViewModel::class) {

    override fun ActivityMainBinding.renderUi() {
        btn.setOnClickListener { viewModel.showToastExample2() }
        //btn.setOnClickListener { viewModel.getUsers() }
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



//    private val firstRunRequestPermissions = arrayOf(
//        permission.INTERNET,
//        permission.ACCESS_NETWORK_STATE,
//
//    )
//
//    private fun requestAppPermissions() {
//        ContextCompat.checkSelfPermission(
//            this,
//            permission.INTERNET
//        )
//    }
package com.datascope.architect

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.datascope.architect.bc.storage.service.api.IUserApi
import com.datascope.architect.bc.ui.viewmodel.UserUiEvent
import com.datascope.architect.bc.ui.viewmodel.UserUiState
import com.datascope.architect.bc.ui.viewmodel.UserUiState.*
import com.datascope.architect.bc.ui.viewmodel.UserViewModel
import com.datascope.architect.databinding.ActivityMainBinding
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel


// TODO: investigate UI binding

class MainActivity : BetterActivity<
        UserUiEvent,
        UserUiState,
        UserViewModel,
        ActivityMainBinding>() {

    override fun ActivityMainBinding.renderUi() {
        //btn.setOnClickListener { viewModel.showToastExample2() }
        btn.setOnClickListener { viewModel.getUsers() }
        btnText.setOnClickListener { viewModel.changeTextExample2() }

    }

    override fun onUiState(state: UserUiState) = when (state) {
        is Initial -> {}
        is TextChanged -> updateText(state)
        is UsersFetched -> userFetched(state)
    }

    private fun userFetched(state: UsersFetched) {
        ui.txt.text = state.users.first().fullName
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

    override fun provideViewModel(): UserViewModel = getViewModel()
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
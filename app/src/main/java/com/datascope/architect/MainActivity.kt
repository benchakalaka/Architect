package com.datascope.architect

import android.os.Bundle
import android.widget.Toast
import com.datascope.architect.viewmodel.ArchitectUiAction
import com.datascope.architect.viewmodel.ArchitectUiState
import com.datascope.architect.viewmodel.ArchitectViewModel

// TODO: move it to reflection at BetterActivity
// TODO: add real service to fetch data
// TODO: inject it to viewModel
// TODO: develop and understand initial and loading state, toasts etc
class MainActivity : BetterActivity<
        ArchitectUiAction,
        ArchitectUiState,
        ArchitectViewModel>(ArchitectViewModel::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.doTheThing()
    }

    override fun onUiState(state: ArchitectUiState) {
        when (state) {
            ArchitectUiState.Initial -> Toast.makeText(this, "Initial ", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onUiAction(action: ArchitectUiAction) {
        when (action) {
            is ArchitectUiAction.ShowToast -> Toast.makeText(this, "Toast ", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
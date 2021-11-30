package com.datascope.architect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.datascope.architect.vmcore.BetterViewModel
import com.datascope.architect.vmcore.UiEvent
import com.datascope.architect.vmcore.UiState
import com.datascope.architect.vmcore.ViewBindingUtil
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class BetterActivity<
        TUiEvent : UiEvent,
        TUiState : UiState,
        TViewModel : BetterViewModel<TUiState, TUiEvent>,
        TViewBinding : ViewBinding> : AppCompatActivity() {

    abstract fun onUiState(state: TUiState)
    abstract fun onUiEvent(event: TUiEvent)
    abstract fun TViewBinding.renderUi()
    abstract fun provideViewModel(): TViewModel

    protected val viewModel: TViewModel by lazy {
        provideViewModel()
    }

    protected var ui: TViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ViewBindingUtil.inflate(
            layoutInflater, ViewBindingUtil.getViewBindingClass(viewClass = javaClass)
        )

        ui?.let {  setContentView(it.root) }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiStates.onEach { newState -> onUiState(newState) }.launchIn(this)
                viewModel.uiEvents.onEach { newEvent -> onUiEvent(newEvent) }.launchIn(this)
            }
        }

        ui?.renderUi()
    }

    override fun onDestroy() {
        ui = null
        super.onDestroy()
    }
}
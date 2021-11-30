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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.KClass

abstract class BetterActivity<
        TUiEvent : UiEvent,
        TUiState : UiState,
        TViewModel : BetterViewModel<TUiState, TUiEvent>,
        TViewBinding : ViewBinding>(vmClass: KClass<TViewModel>) : AppCompatActivity() {

    protected val viewModel by lazy {
        val viewModelDynamicClass: Class<TViewModel> = ViewBindingUtil.getClassWithIndex(javaClass, 2)
        // TODO: Cast viewModelDynamicClass to KClass<TViewModel>
        getViewModel(clazz = vmClass)
    }

    protected lateinit var ui: TViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ViewBindingUtil.inflate(
            layoutInflater, ViewBindingUtil.getViewBindingClass(viewClass = javaClass)
        )

        setContentView(ui.root)

        // Start a coroutine in the lifecycle scope
        lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // Note that this happens when lifecycle is STARTED
                // and stops collecting when the lifecycle is STOPPED
                viewModel.uiStates.onEach { newState -> onUiState(newState) }.launchIn(this)
                viewModel.uiEvents.onEach { newEvent -> onUiEvent(newEvent) }.launchIn(this)
            }
        }

        ui.renderUi()
    }

    abstract fun onUiState(state: TUiState)
    abstract fun onUiEvent(event: TUiEvent)
    abstract fun TViewBinding.renderUi()
}
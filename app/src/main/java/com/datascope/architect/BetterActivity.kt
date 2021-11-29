package com.datascope.architect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.datascope.architect.vmcore.BetterViewModel
import com.datascope.architect.vmcore.UiAction
import com.datascope.architect.vmcore.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.KClass

abstract class BetterActivity<
        TUiAction: UiAction,
        TUiState : UiState,
        TViewModel : BetterViewModel<TUiState, TUiAction>
        >(vmClass: KClass<TViewModel>) : AppCompatActivity() {

    protected val viewModel by lazy {
        //val kk   = runtimeViewModelType!!::class as KClass<ViewModel>
        getViewModel(clazz = vmClass)
    }

    private val runtimeViewModelType: Type?
        get() = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] //as KClass<TViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Start a coroutine in the lifecycle scope
        lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // Note that this happens when lifecycle is STARTED
                // and stops collecting when the lifecycle is STOPPED
                viewModel.uiState.collect { newState -> onUiState(newState) }
                viewModel.uiAction.collect { newAction -> onUiAction(newAction) }
            }
        }
    }

    abstract fun onUiState(state: TUiState)
    abstract fun onUiAction(action: TUiAction)
}
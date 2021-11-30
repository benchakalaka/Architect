package com.datascope.architect.ioc

import com.datascope.architect.bc.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val architectModule = module {
    viewModel { UserViewModel() }
}
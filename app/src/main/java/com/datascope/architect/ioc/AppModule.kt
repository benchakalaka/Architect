package com.datascope.architect.ioc

import com.datascope.architect.viewmodel.ArchitectViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val architectModule = module {
    viewModel { ArchitectViewModel() }
}
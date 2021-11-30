package com.datascope.architect.bc.ioc

import com.datascope.architect.bc.storage.repository.IUserRepository
import com.datascope.architect.bc.storage.repository.UserRepository
import com.datascope.architect.bc.storage.repository.mapper.UserRepositoryMapper
import com.datascope.architect.bc.storage.service.IUserService
import com.datascope.architect.bc.storage.service.UserService
import com.datascope.architect.bc.storage.service.api.IUserApi
import com.datascope.architect.bc.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val userModuleIoc = module {
    single<IUserApi> { get<Retrofit>().create(IUserApi::class.java) }

    single<UserRepositoryMapper> { UserRepositoryMapper() }
    factory<IUserService> { UserService(get()) }
    factory<IUserRepository> { UserRepository(get(), get()) }

    viewModel { UserViewModel(get()) }
}
package com.digitaldesigns.kmm_sharedviewmodel

import com.digitaldesigns.kmm_sharedviewmodel.repository.AuthRepository
import com.digitaldesigns.kmm_sharedviewmodel.viewmodel.login.LoginViewModel
import com.digitaldesigns.kmm_sharedviewmodel.viewmodel.main.MainViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication = startKoin {
    appDeclaration()
    modules(sharedModule, platformModule())
}

internal val sharedModule = module {
    single { AuthRepository(get()) }

    single { MainViewModel(get()) }
    single { LoginViewModel(get()) }
}

internal expect fun platformModule(): Module

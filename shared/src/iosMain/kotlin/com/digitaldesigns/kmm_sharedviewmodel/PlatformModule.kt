package com.digitaldesigns.kmm_sharedviewmodel

import com.digitaldesigns.kmm_sharedviewmodel.viewmodel.login.LoginViewModel
import com.digitaldesigns.kmm_sharedviewmodel.viewmodel.main.MainViewModel
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.dsl.module

@Suppress("unused") // Called from Swift
fun initializeKoin(): KoinApplication = initKoin(appDeclaration = {})

@Suppress("unused") // Called from Swift
object KotlinDependencies : KoinComponent {
    fun getMainViewModel() = getKoin().get<MainViewModel>()
    fun getLoginViewModel() = getKoin().get<LoginViewModel>()
}

internal actual fun platformModule() = module {
}

package com.digitaldesigns.kmm_sharedviewmodel.viewmodel

import kotlinx.coroutines.CoroutineScope

expect abstract class BaseViewModel() {
    val viewModelScope: CoroutineScope

    protected open fun onCleared()
}

package com.digitaldesigns.kmm_sharedviewmodel.viewmodel.main

import com.digitaldesigns.kmm_sharedviewmodel.repository.AuthRepository
import com.digitaldesigns.kmm_sharedviewmodel.viewmodel.BaseViewModel
import com.digitaldesigns.kmmsharedviewmodel.cache.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainState(
    var userState: UserState? = null,
)

class MainViewModel(private val authRepository: AuthRepository) : BaseViewModel() {

    private val _mainState = MutableStateFlow(MainState())
    val mainState: StateFlow<MainState> = _mainState

    init {
        monitorUserState()
    }

    private fun monitorUserState() {
        viewModelScope.launch {
            authRepository.checkUserState().collect { userState ->
                _mainState.update { it.copy(userState = userState) }
            }
        }
    }

    fun signOut() = authRepository.signOut()
}

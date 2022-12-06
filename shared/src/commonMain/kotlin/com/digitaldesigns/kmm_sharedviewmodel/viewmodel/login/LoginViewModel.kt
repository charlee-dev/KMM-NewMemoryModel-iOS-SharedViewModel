package com.digitaldesigns.kmm_sharedviewmodel.viewmodel.login

import com.digitaldesigns.kmm_sharedviewmodel.repository.AuthRepository
import com.digitaldesigns.kmm_sharedviewmodel.viewmodel.BaseViewModel
import com.digitaldesigns.kmmsharedviewmodel.cache.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface CommonState {
    var isLoading: Boolean
    var error: String?
}

data class LoginUiState(
    val mode: ScreenMode = ScreenMode.LOGIN,
    var email: String = "",
    var password: String = "",
    var userState: UserState? = null,
    var isButtonDisabled: Boolean = true,
    override var isLoading: Boolean = false,
    override var error: String? = null,
) : CommonState

enum class ScreenMode { LOGIN, REGISTRATION, FORGOT_PASSWORD }

class LoginViewModel(
    private val authRepository: AuthRepository,
) : BaseViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun signIn() = startFlow {
        authRepository.signIn(uiState.value.email, uiState.value.password)
    }

    fun signUp() = startFlow {
        authRepository.signIn(uiState.value.email, uiState.value.password)
    }

    private fun startFlow(block: suspend () -> UserState?) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                _uiState.update { it.copy(userState = block()) }
                _uiState.update { it.copy(isLoading = false) }
            } catch (ex: Exception) {
                _uiState.update { it.copy(isLoading = false, error = ex.toString()) }
            }
        }
    }

    fun onEmailChanged(value: String) {
        _uiState.update { it.copy(email = value) }
        shouldButtonBeDisabled()
    }

    fun onPasswordChanged(value: String) {
        _uiState.update { it.copy(password = value) }
        shouldButtonBeDisabled()
    }

    fun setMode(mode: ScreenMode) {
        _uiState.update { it.copy(mode = mode) }
    }

    private fun shouldButtonBeDisabled() {
        val shouldDisable = _uiState.value.email.isEmpty() || _uiState.value.password.isEmpty()
        _uiState.update { it.copy(isButtonDisabled = shouldDisable) }
    }
}


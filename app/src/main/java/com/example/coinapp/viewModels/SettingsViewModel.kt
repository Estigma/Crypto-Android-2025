package com.example.coinapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapp.models.AuthState
import com.example.coinapp.services.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(private val repository: AuthRepository = AuthRepository()) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    init {
        checkAuthState()
    }

    fun login(email: String, password: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            val success = repository.login(email, password)
            if (success) {
                checkAuthState()
            } else {
                _authState.value = AuthState.Error("Login failed")
            }
        }
    }

    fun logout() {
        repository.logout()
        _authState.value = AuthState.Idle
    }

    private fun checkAuthState() {
        val user = repository.getCurrentUser()
        _authState.value = if (user != null) {
            AuthState.Authenticated(user.email, user.uid)
        } else {
            AuthState.Idle
        }
    }
}
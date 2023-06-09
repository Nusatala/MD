package com.dicoding.nusatalaapp.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.nusatalaapp.domain.use_case.register.RegisterUseCase
import com.dicoding.nusatalaapp.presentation.auth.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.dicoding.nusatalaapp.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    fun register(
        name: String,
        username: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            registerUseCase(name, username, email, password).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _state.value = AuthState(isLoading = true)
                    }
                    is Result.Success -> {
                        _state.value = AuthState(isLoading = false, user = result.data)
                    }
                    is Result.Error -> {
                        _state.value = AuthState(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }
}
package com.dicoding.nusatalaapp.presentation.auth.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.domain.use_case.login.LoginUseCase
import com.dicoding.nusatalaapp.domain.use_case.save_on_boarding_state.SaveOnBoardingStateUseCase
import com.dicoding.nusatalaapp.domain.use_case.save_user_session.SaveUserSessionUseCase
import com.dicoding.nusatalaapp.presentation.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveUserSessionUseCase: SaveUserSessionUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginUseCase(username, password).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _state.value = AuthState(isLoading = true)
                    }
                    is Result.Success -> {
                        _state.value = AuthState(isLoading = false, user = result.data)
                        viewModelScope.launch {
                            saveUserSessionUseCase(userModel = result.data)
                        }
                    }
                    is Result.Error -> {
                        _state.value = AuthState(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }
}
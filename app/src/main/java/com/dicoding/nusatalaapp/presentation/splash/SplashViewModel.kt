package com.dicoding.nusatalaapp.presentation.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.nusatalaapp.domain.use_case.read_on_boarding_state.ReadOnBoardingStateUseCase
import com.dicoding.nusatalaapp.domain.use_case.read_user_session.ReadUserSessionUseCase
import com.dicoding.nusatalaapp.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val readOnBoardingStateUseCase: ReadOnBoardingStateUseCase,
    private val readUserSessionUseCase: ReadUserSessionUseCase,
) : ViewModel() {
    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.Welcome.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            readOnBoardingStateUseCase().collect { state ->
                if (state) {
                    viewModelScope.launch {
                        readUserSessionUseCase().collect { user ->
                            if (user.token != null) {
                                _startDestination.value = Screen.Home.route
                            } else {
                                _startDestination.value = Screen.Login.route
                            }
                        }
                    }
                } else {
                    _startDestination.value = Screen.Welcome.route
                }

                _isLoading.value = false
            }
        }
    }
}
package com.dicoding.nusatalaapp.presentation.splash.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.nusatalaapp.domain.use_case.save_on_boarding_state.SaveOnBoardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val saveOnBoardingStateUseCase: SaveOnBoardingStateUseCase
) : ViewModel() {
    fun saveOnBoardingState(state: Boolean) {
        viewModelScope.launch {
            saveOnBoardingStateUseCase(state)
        }
    }
}
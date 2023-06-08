package com.dicoding.nusatalaapp.domain.use_case.save_on_boarding_state

import com.dicoding.nusatalaapp.data.repository.UserPrefRepositoryImpl
import com.dicoding.nusatalaapp.domain.repository.UserPrefRepository
import javax.inject.Inject

class SaveOnBoardingStateUseCase @Inject constructor(
    private val userPrefRepository: UserPrefRepositoryImpl
) {
    suspend operator fun invoke(state: Boolean) {
        userPrefRepository.saveOnBoardingState(state)
    }
}
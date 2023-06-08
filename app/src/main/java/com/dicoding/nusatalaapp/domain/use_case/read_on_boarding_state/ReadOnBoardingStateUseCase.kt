package com.dicoding.nusatalaapp.domain.use_case.read_on_boarding_state

import com.dicoding.nusatalaapp.data.repository.UserPrefRepositoryImpl
import com.dicoding.nusatalaapp.domain.repository.UserPrefRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReadOnBoardingStateUseCase @Inject constructor(
    private val userPrefRepository: UserPrefRepositoryImpl,
){
    operator fun invoke(): Flow<Boolean>  {
        return userPrefRepository.readOnBoardingState()
    }
}
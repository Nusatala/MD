package com.dicoding.nusatalaapp.domain.use_case.save_user_session

import android.util.Log
import com.dicoding.nusatalaapp.data.repository.UserPrefRepositoryImpl
import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.domain.repository.UserPrefRepository
import javax.inject.Inject

class SaveUserSessionUseCase @Inject constructor(
    private val userPrefRepository: UserPrefRepositoryImpl
){
    suspend operator fun invoke(userModel: User) {
        userPrefRepository.saveUserSession(userModel)
    }
}
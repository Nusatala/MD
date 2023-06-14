package com.dicoding.nusatalaapp.domain.use_case.destroy_user_session

import com.dicoding.nusatalaapp.data.repository.UserPrefRepositoryImpl
import javax.inject.Inject

class DestroyUserSessionUseCase @Inject constructor(
    private val repository: UserPrefRepositoryImpl
) {
    suspend operator fun invoke(): Boolean {
        return repository.destroyUserSession()
    }
}
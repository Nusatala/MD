package com.dicoding.nusatalaapp.domain.use_case.login

import android.util.Log
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Flow<Result<User>> {
        Log.d("userLogin", "use case scope exec")
        return authRepository.login(username, password)
    }
}
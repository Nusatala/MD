package com.dicoding.nusatalaapp.domain.use_case.register

import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.dicoding.nusatalaapp.common.Result

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        name: String,
        username: String,
        email: String,
        password: String
    ): Flow<Result<User>> {
        return authRepository.register(name, username, email, password)
    }
}
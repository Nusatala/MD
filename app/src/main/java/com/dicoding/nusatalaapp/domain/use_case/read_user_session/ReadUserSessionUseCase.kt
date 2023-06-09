package com.dicoding.nusatalaapp.domain.use_case.read_user_session

import com.dicoding.nusatalaapp.data.repository.UserPrefRepositoryImpl
import com.dicoding.nusatalaapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadUserSessionUseCase @Inject constructor(
    private val userPrefRepository: UserPrefRepositoryImpl
) {
    operator fun invoke(): Flow<User> {
        return userPrefRepository.readUserSession()
    }
}
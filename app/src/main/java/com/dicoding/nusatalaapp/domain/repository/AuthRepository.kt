package com.dicoding.nusatalaapp.domain.repository

import com.dicoding.nusatalaapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import com.dicoding.nusatalaapp.common.Result

interface AuthRepository {
    suspend fun login(username: String, password: String): Flow<Result<User>>

    suspend fun register(name: String, username: String, email: String, password: String): Flow<Result<User>>
}
package com.dicoding.nusatalaapp.data.repository

import com.dicoding.nusatalaapp.data.remote.ApiService
import com.dicoding.nusatalaapp.data.remote.dto.UserDTO
import com.dicoding.nusatalaapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {
    override suspend fun login(): UserDTO {
        return UserDTO()
    }
}
package com.dicoding.nusatalaapp.domain.repository

import com.dicoding.nusatalaapp.data.remote.dto.UserDTO

interface AuthRepository {
    suspend fun login(): UserDTO
}
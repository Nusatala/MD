package com.dicoding.nusatalaapp.presentation.auth

import com.dicoding.nusatalaapp.domain.model.User

data class AuthState(
    val isLoading: Boolean = false,
    val user: User = User(),
    val error: String = ""
)
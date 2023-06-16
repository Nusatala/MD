package com.dicoding.nusatalaapp.data.repository

import android.util.Log
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.data.remote.ApiService
import com.dicoding.nusatalaapp.data.remote.dto.toModel
import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {
    override suspend fun login(username: String, password: String): Flow<Result<User>> = flow {
        try {
            emit(Result.Loading)
            val response = apiService.login(username, password).toModel()
            emit(Result.Success(data = response))
        } catch (exception: HttpException) {
            emit(Result.Error("Invalid Credentilas"))
        }
    }

    override suspend fun register(
        name: String,
        username: String,
        email: String,
        password: String
    ): Flow<Result<User>> = flow {
        try {
            emit(Result.Loading)
            val response = apiService.register(name, username, email, password).toModel()
            emit(Result.Success(response))
        } catch (exception: Exception) {
            emit(Result.Error("The email or username you use already exist"))
        }
    }
}
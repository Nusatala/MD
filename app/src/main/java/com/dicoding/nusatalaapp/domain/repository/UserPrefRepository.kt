package com.dicoding.nusatalaapp.domain.repository

import com.dicoding.nusatalaapp.domain.model.User
import kotlinx.coroutines.flow.Flow


interface UserPrefRepository {

    suspend fun saveUserSession(userModel: User)
    suspend fun saveOnBoardingState(state: Boolean)

    fun readOnBoardingState(): Flow<Boolean>
    fun readUserSession(): Flow<User>

    suspend fun destroyUserSession(): Boolean
}
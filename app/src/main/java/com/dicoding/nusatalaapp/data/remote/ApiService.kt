package com.dicoding.nusatalaapp.data.remote

import com.dicoding.nusatalaapp.data.remote.dto.UserDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): UserDTO
}
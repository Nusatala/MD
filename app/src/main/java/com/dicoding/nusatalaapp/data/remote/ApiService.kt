package com.dicoding.nusatalaapp.data.remote

import com.dicoding.nusatalaapp.data.remote.dto.UserDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("users/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): UserDTO

    @POST("users/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("photo") photo: String
    ): UserDTO
}
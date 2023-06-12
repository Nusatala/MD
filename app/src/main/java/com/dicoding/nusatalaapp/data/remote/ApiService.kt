package com.dicoding.nusatalaapp.data.remote

import com.dicoding.nusatalaapp.data.remote.dto.*
import retrofit2.http.*

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
    ): UserDTO

    @GET("articles")
    suspend fun getArticles(
        @Header("Authorization") token: String,
    ): List<ArticleDTO>

    @GET("articles/{id}")
    suspend fun getArticleById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ArticleDTO

    @GET("images")
    suspend fun getImages(
        @Header("Authorization") token: String,
    ): List<ImageDTO>

    @GET("images/{id}")
    suspend fun getImageById(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
    ): ImageDTO
}
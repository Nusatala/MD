package com.dicoding.nusatalaapp.data.remote

import com.dicoding.nusatalaapp.data.remote.dto.*
import okhttp3.MultipartBody
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

    @GET("articles/popularity")
    suspend fun getArticlesByViews(
        @Header("Authorization") token: String,
    ): List<ArticleDTO>

    @GET("articles/new")
    suspend fun getLatestArticles(
        @Header("Authorization") token: String,
    ): List<ArticleDTO>

    @GET("articles/label/{labelId}")
    suspend fun getArticleByLabelId(
        @Header("Authorization") token: String,
        @Path("labelId") labelId: Int
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

    @GET("products")
    suspend fun getProducts(
        @Header("Authorization") token: String,
    ): List<ProductDTO>

    @GET("products/{id}")
    suspend fun getProductById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ProductDTO

    @GET("products/label/{id}")
    suspend fun getProductsByLabel(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): List<ProductDTO>

    @Multipart
    @POST("images/scan")
    suspend fun scan(
        @Header("Authorization") token: String,
        @Part file:MultipartBody.Part
    ): ScanDTO

    @GET("tutorials/label/{id}")
    suspend fun getTutorialByLabel(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): TutorialDTO
}
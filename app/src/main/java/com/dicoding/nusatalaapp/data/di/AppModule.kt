package com.dicoding.nusatalaapp.data.di

import android.content.Context
import com.dicoding.nusatalaapp.data.remote.ApiConfig
import com.dicoding.nusatalaapp.data.remote.ApiService
import com.dicoding.nusatalaapp.data.repository.ArticleRepositoryImpl
import com.dicoding.nusatalaapp.data.repository.AuthRepositoryImpl
import com.dicoding.nusatalaapp.data.repository.ProductRepositoryImpl
import com.dicoding.nusatalaapp.data.repository.UserPrefRepositoryImpl
import com.dicoding.nusatalaapp.domain.repository.ArticleRepository
import com.dicoding.nusatalaapp.domain.repository.AuthRepository
import com.dicoding.nusatalaapp.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserPrefRepository(
        @ApplicationContext context: Context
    ) = UserPrefRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideApiService(): ApiService = ApiConfig.getApiService()

    @Provides
    @Singleton
    fun provideAuthRepository(
        apiService: ApiService
    ): AuthRepository {
        return AuthRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideArticleRepository(
        apiService: ApiService
    ): ArticleRepository {
        return ArticleRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        apiService: ApiService
    ): ProductRepository {
        return ProductRepositoryImpl(apiService)
    }
}
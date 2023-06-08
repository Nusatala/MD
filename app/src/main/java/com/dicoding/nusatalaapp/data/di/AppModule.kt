package com.dicoding.nusatalaapp.data.di

import android.content.Context
import com.dicoding.nusatalaapp.data.repository.UserPrefRepositoryImpl
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
}
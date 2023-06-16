package com.dicoding.nusatalaapp.data.di

import android.app.Application
import android.content.Context
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.dicoding.nusatalaapp.data.remote.ApiConfig
import com.dicoding.nusatalaapp.data.remote.ApiService
import com.dicoding.nusatalaapp.data.repository.*
import com.dicoding.nusatalaapp.domain.repository.*
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
        @ApplicationContext context: Context,
    ) = UserPrefRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideApiService(): ApiService = ApiConfig.getApiService()

    @Provides
    @Singleton
    fun provideAuthRepository(
        apiService: ApiService,
    ): AuthRepository {
        return AuthRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideArticleRepository(
        apiService: ApiService,
    ): ArticleRepository {
        return ArticleRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        apiService: ApiService,
    ): ProductRepository {
        return ProductRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCameraSelector(): CameraSelector {
        return CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
    }

    @Provides
    @Singleton
    fun provideCameraProvider(application: Application): ProcessCameraProvider {
        return ProcessCameraProvider.getInstance(application).get()
    }

    @Provides
    @Singleton
    fun provideCameraPreview(): Preview {
        return Preview.Builder().build()
    }

    @Provides
    @Singleton
    fun provideImageAnalysis(): ImageAnalysis {
        return ImageAnalysis.Builder()
            .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
            .build()
    }

    @Provides
    @Singleton
    fun provideImageCapture(): ImageCapture {
        return ImageCapture.Builder()
            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
            .build()
    }

    @Provides
    @Singleton
    fun provideCameraRepository(
        cameraProvider: ProcessCameraProvider,
        selector: CameraSelector,
        preview: Preview,
        imageAnalysis: ImageAnalysis,
        imageCapture: ImageCapture,
    ): CameraRepository {
        return CameraRepositoryImpl(cameraProvider, selector, preview, imageAnalysis, imageCapture)
    }

    @Provides
    @Singleton
    fun provideScanRepository(
        apiService: ApiService
    ): ScanRepository {
        return ScanRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideTutorialRepository(
        apiService: ApiService
    ): TutorialRepository {
        return TutorialRepositoryImpl(apiService)
    }
}
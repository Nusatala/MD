package com.dicoding.nusatalaapp.data.repository

import android.util.Log
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.data.remote.ApiService
import com.dicoding.nusatalaapp.data.remote.dto.toModel
import com.dicoding.nusatalaapp.data.remote.dto.toScanModel
import com.dicoding.nusatalaapp.domain.model.Article
import com.dicoding.nusatalaapp.domain.model.ScanResult
import com.dicoding.nusatalaapp.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ArticleRepository {
    override suspend fun getArticles(token: String): Flow<Result<List<Article>>> = flow {
        try {
            emit(Result.Loading)
            val response = apiService.getArticles(token).map { it.toModel() }
            emit(Result.Success(response))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.toString()))
        }
    }

    override suspend fun getArticleById(token: String, id: Int): Flow<Result<Article>> = flow{
        try {
            emit(Result.Loading)
            val response = apiService.getArticleById(token, id).toModel()
            emit(Result.Success(response))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.toString()))
        }
    }

    override suspend fun getArticlesByViews(token: String): Flow<Result<List<Article>>> = flow {
        try {
            emit(Result.Loading)
            val response = apiService.getArticlesByViews(token).map { it.toModel() }
            emit(Result.Success(response))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.toString()))
        }
    }

    override suspend fun getLatestArticles(token: String): Flow<Result<List<Article>>> = flow {
        try {
            emit(Result.Loading)
            val response = apiService.getLatestArticles(token).map { it.toModel() }
            emit(Result.Success(response))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.toString()))
        }
    }

    override suspend fun getArticleByLabelId(token: String, labelId: Int): Flow<Result<ScanResult>> = flow {
        try {
            emit(Result.Loading)
            val response = apiService.getArticleByLabelId(token, labelId).toScanModel()
            emit(Result.Success(response))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.toString()))
        }
    }
}
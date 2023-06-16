package com.dicoding.nusatalaapp.domain.repository

import com.dicoding.nusatalaapp.domain.model.Article

import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.domain.model.ScanResult
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getArticles(token: String): Flow<Result<List<Article>>>
    suspend fun getArticleById(token: String, id: Int): Flow<Result<Article>>
    suspend fun getArticlesByViews(token: String): Flow<Result<List<Article>>>
    suspend fun getLatestArticles(token: String): Flow<Result<List<Article>>>
    suspend fun getArticleByLabelId(token: String, labelId: Int): Flow<Result<ScanResult>>
}
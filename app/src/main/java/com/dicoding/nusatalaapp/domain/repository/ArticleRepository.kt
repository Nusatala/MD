package com.dicoding.nusatalaapp.domain.repository

import com.dicoding.nusatalaapp.domain.model.Article

import com.dicoding.nusatalaapp.common.Result
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    // get articles
    suspend fun getArticles(token: String): Flow<Result<List<Article>>>
    // get article by id
    suspend fun getArticleById(token: String, id: Int): Flow<Result<Article>>
    // get article by views
    suspend fun getArticlesByViews(token: String): Flow<Result<List<Article>>>
    // get latest article
    suspend fun getLatestArticles(token: String): Flow<Result<List<Article>>>
}
package com.dicoding.nusatalaapp.domain.repository

import com.dicoding.nusatalaapp.domain.model.Product
import kotlinx.coroutines.flow.Flow
import com.dicoding.nusatalaapp.common.Result

interface ProductRepository {
    suspend fun getArticles(token: String): Flow<Result<List<Product>>>

    suspend fun getArticleById(token: String, id: Int): Flow<Result<Product>>
}
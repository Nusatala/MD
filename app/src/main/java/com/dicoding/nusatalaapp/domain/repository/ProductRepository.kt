package com.dicoding.nusatalaapp.domain.repository

import com.dicoding.nusatalaapp.domain.model.Product
import kotlinx.coroutines.flow.Flow
import com.dicoding.nusatalaapp.common.Result

interface ProductRepository {
    suspend fun getProducts(token: String): Flow<Result<List<Product>>>
    suspend fun getProductById(token: String, id: Int): Flow<Result<Product>>
    suspend fun getProductsByLabel(token: String, labelId: Int): Flow<Result<List<Product>>>
}
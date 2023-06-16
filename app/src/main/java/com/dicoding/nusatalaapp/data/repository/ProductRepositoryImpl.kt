package com.dicoding.nusatalaapp.data.repository

import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.data.remote.ApiService
import com.dicoding.nusatalaapp.data.remote.dto.toModel
import com.dicoding.nusatalaapp.domain.model.Product
import com.dicoding.nusatalaapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ProductRepository {
    override suspend fun getProducts(token: String): Flow<Result<List<Product>>> = flow {
        try {
            emit(Result.Loading)
            val response = apiService.getProducts(token).map { it.toModel() }
            emit(Result.Success(response))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.toString()))
        }
    }

    override suspend fun getProductById(token: String, id: Int): Flow<Result<Product>> = flow {
        try {
            emit(Result.Loading)
            val response = apiService.getProductById(token, id).toModel()
            emit(Result.Success(response))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.toString()))
        }
    }

    override suspend fun getProductsByLabel(
        token: String,
        labelId: Int,
    ): Flow<Result<List<Product>>> = flow {
        try {
            emit(Result.Loading)
            val response = apiService.getProductsByLabel(token, labelId).map { it.toModel() }
            emit(Result.Success(response))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.toString()))
        }
    }
}
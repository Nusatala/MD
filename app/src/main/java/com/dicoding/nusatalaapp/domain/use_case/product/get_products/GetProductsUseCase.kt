package com.dicoding.nusatalaapp.domain.use_case.product.get_products

import javax.inject.Inject
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.domain.model.Product
import com.dicoding.nusatalaapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(token: String): Flow<Result<List<Product>>> {
        return productRepository.getArticles(token)
    }
}
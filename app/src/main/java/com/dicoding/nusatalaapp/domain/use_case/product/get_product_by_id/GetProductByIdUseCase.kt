package com.dicoding.nusatalaapp.domain.use_case.product.get_product_by_id

import com.dicoding.nusatalaapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.domain.model.Product

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
){
    suspend operator fun invoke(token: String, id: Int): Flow<Result<Product>> {
        return productRepository.getProductById(token, id)
    }
}
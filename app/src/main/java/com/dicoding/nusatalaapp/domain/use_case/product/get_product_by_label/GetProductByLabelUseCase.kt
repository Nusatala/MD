package com.dicoding.nusatalaapp.domain.use_case.product.get_product_by_label

import com.dicoding.nusatalaapp.domain.model.Product
import com.dicoding.nusatalaapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.dicoding.nusatalaapp.common.Result

class GetProductByLabelUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(token: String, labelId: Int): Flow<Result<List<Product>>> {
        return productRepository.getProductsByLabel(token, labelId)
    }
}
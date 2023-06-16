package com.dicoding.nusatalaapp.presentation.product

import com.dicoding.nusatalaapp.domain.model.Product

data class ProductState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String = ""
)

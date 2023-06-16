package com.dicoding.nusatalaapp.presentation.product

import com.dicoding.nusatalaapp.domain.model.Product

data class ProductDetailState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String = ""
)

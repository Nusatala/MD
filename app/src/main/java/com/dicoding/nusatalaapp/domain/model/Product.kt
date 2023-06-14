package com.dicoding.nusatalaapp.domain.model

data class Product(
    val id: Int,
    val thumbnail: String,
    val name: String,
    val description: String,
    val price: String,
    val rating: Float,
    val link: String,
)

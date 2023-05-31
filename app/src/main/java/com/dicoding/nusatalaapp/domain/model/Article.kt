package com.dicoding.nusatalaapp.domain.model

data class Article(
    val id: Long,
    val title: String,
    val body: String,
    val imageUrl: String,
    val views: Int,
)

package com.dicoding.nusatalaapp.domain.model

data class ScanResult(
    val id: Int,
    val name: String,
    val body: String,
    val image: Image,
    val views: Int,
    val materials: String,
    val regionalOrigin: String,
)

package com.dicoding.nusatalaapp.presentation.scan.detail

import com.dicoding.nusatalaapp.domain.model.Product
import com.dicoding.nusatalaapp.domain.model.ScanResult
import com.dicoding.nusatalaapp.domain.model.Tutorial

data class DetailResultScanState(
    val isLoading: Boolean = false,
    val article: ScanResult? = null,
    val products: List<Product>? = emptyList(),
    val tutorial: Tutorial? = null,
    val error: String = ""
)

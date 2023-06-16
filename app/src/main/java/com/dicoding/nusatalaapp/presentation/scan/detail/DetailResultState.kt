package com.dicoding.nusatalaapp.presentation.scan.detail

import com.dicoding.nusatalaapp.domain.model.ScanResult

data class DetailResultState(
    val isLoading: Boolean = false,
    val data: ScanResult? = null,
    val error: String = "",
)

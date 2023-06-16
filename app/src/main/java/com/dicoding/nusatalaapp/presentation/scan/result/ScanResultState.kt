package com.dicoding.nusatalaapp.presentation.scan.result

data class ScanResultState(
    val isLoading: Boolean = false,
    val labelId: Int = -1,
    val error: String = "",
)
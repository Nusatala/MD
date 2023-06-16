package com.dicoding.nusatalaapp.domain.repository

import java.io.File
import com.dicoding.nusatalaapp.common.Result
import kotlinx.coroutines.flow.Flow

interface ScanRepository {
    suspend fun scan(token: String, file: File): Flow<Result<Int>>
}
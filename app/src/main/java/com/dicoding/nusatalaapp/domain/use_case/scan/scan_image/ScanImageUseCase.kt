package com.dicoding.nusatalaapp.domain.use_case.scan.scan_image

import com.dicoding.nusatalaapp.domain.repository.ScanRepository
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject
import com.dicoding.nusatalaapp.common.Result

class ScanImageUseCase @Inject constructor(
    private val scanRepository: ScanRepository
) {
    suspend operator fun invoke(token: String, file: File): Flow<Result<Int>> {
        return scanRepository.scan(token, file)
    }
}
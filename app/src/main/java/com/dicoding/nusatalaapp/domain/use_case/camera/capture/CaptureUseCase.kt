package com.dicoding.nusatalaapp.domain.use_case.camera.capture

import android.content.Context
import android.net.Uri
import com.dicoding.nusatalaapp.domain.repository.CameraRepository
import java.io.File
import javax.inject.Inject

class CaptureUseCase @Inject constructor(
    private val cameraRepository: CameraRepository
) {
    suspend operator fun invoke(context: Context): Uri {
        return cameraRepository.capture(context)
    }
}
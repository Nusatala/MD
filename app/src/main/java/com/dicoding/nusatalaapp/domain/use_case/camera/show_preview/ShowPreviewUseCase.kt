package com.dicoding.nusatalaapp.domain.use_case.camera.show_preview

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.dicoding.nusatalaapp.domain.repository.CameraRepository
import javax.inject.Inject

class ShowPreviewUseCase @Inject constructor(
    private val cameraRepository: CameraRepository,
) {
    suspend operator fun invoke(previewView: PreviewView, lifecycleOwner: LifecycleOwner) {
        cameraRepository.showPreview(previewView, lifecycleOwner)
    }
}
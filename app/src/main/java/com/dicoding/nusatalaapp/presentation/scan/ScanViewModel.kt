package com.dicoding.nusatalaapp.presentation.scan

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.nusatalaapp.domain.use_case.camera.capture.CaptureUseCase
import com.dicoding.nusatalaapp.domain.use_case.camera.show_preview.ShowPreviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val captureUseCase: CaptureUseCase,
    private val showPreviewUseCase: ShowPreviewUseCase,
) : ViewModel() {
    private val _capturedImageUri = MutableStateFlow<Uri?>(null)
    val capturedImageUri: StateFlow<Uri?> = _capturedImageUri

    fun showPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ) {
        viewModelScope.launch {
            showPreviewUseCase(previewView, lifecycleOwner)
        }
    }

    fun captureAndSave(context: Context) {
        viewModelScope.launch {
            val capturedUri = captureUseCase(context)
            Log.d("scanCam", capturedUri.toString())
            _capturedImageUri.value = capturedUri
        }
    }
}
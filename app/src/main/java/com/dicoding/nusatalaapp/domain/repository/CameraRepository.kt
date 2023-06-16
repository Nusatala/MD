package com.dicoding.nusatalaapp.domain.repository

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CameraRepository {
    suspend fun capture(context: Context): Uri
    suspend fun showPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    )
}
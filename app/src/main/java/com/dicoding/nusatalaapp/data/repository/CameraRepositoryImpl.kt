package com.dicoding.nusatalaapp.data.repository

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.dicoding.nusatalaapp.domain.repository.CameraRepository
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CameraRepositoryImpl @Inject constructor(
    private val cameraProvider: ProcessCameraProvider,
    private val selector: CameraSelector,
    private val preview: Preview,
    private val imageAnalysis: ImageAnalysis,
    private val imageCapture: ImageCapture
): CameraRepository {
    override suspend fun capture(context: Context): Uri {
        val name = SimpleDateFormat(
            "yyyy-MM-dd-HH-mm-ss-SSS",
            Locale.US
        ).format(System.currentTimeMillis())

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME,name)
            put(MediaStore.MediaColumns.MIME_TYPE,"image/jpeg")
            if (Build.VERSION.SDK_INT > 28){
                put(MediaStore.Images.Media.RELATIVE_PATH,"Pictures/Nusatala")
            }
        }

        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                context.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        var capturedUri: Uri? = null

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    capturedUri = outputFileResults.savedUri
                }

                override fun onError(exception: ImageCaptureException) {
                    exception.printStackTrace()
                }
            }
        )

        while (capturedUri == null) {
            delay(100)
        }

        return capturedUri!!
    }

    override suspend fun showPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner) {
        preview.setSurfaceProvider(previewView.surfaceProvider)
        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                selector,
                preview,
                imageAnalysis,
                imageCapture
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
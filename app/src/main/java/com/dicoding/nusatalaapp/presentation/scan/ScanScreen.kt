package com.dicoding.nusatalaapp.presentation.scan

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanScreen(
    modifier: Modifier = Modifier,
    viewModel: ScanViewModel = hiltViewModel(),
    navigateToResult: (Uri) -> Unit,
) {
    val permissions = if (Build.VERSION.SDK_INT <= 28) {
        listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    } else {
        listOf(
            Manifest.permission.CAMERA
        )
    }

    val permissionState = rememberMultiplePermissionsState(permissions = permissions)
    if (!permissionState.allPermissionsGranted) {
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
    }

    val context = LocalContext.current
    val lifeCycleOwner = LocalLifecycleOwner.current
    var previewView: PreviewView

    val capturedImageUri = viewModel.capturedImageUri.collectAsState()

    LaunchedEffect(capturedImageUri.value) {
        if (capturedImageUri.value != null) {
            capturedImageUri.value.let { navigateToResult(it!!) }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (permissionState.allPermissionsGranted) {
            AndroidView(
                factory = {
                    previewView = PreviewView(it)
                    viewModel.showPreview(previewView, lifeCycleOwner)
                    previewView
                },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        ) {
            IconButton(
                onClick = {
                    viewModel.captureAndSave(context)
                },
                modifier = Modifier
                    .size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Camera,
                    contentDescription = "shoot",
                    tint = Color.White,
                    modifier = Modifier.size(56.dp)
                )
            }
        }
    }
}
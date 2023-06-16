package com.dicoding.nusatalaapp.presentation.scan.result

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ScanResultScreen(
    uri: Uri,
    viewModel: ScanResultViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    cancelAction: () -> Unit,
) {
    val context = LocalContext.current
    val file: File = uriToFile(uri, context)
    val state = viewModel.state.collectAsState()

    LaunchedEffect(state.value.labelId) {
        if (state.value.labelId > -1)
            navigateToDetail(state.value.labelId)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = rememberImagePainter(
                data = Uri.parse(uri.toString())  // or ht
            ),
            contentDescription = "scan result",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = cancelAction,
                    modifier = Modifier
                        .size(64.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "cancel",
                        modifier = Modifier.size(56.dp),
                        tint = Color.White
                    )
                }
                IconButton(
                    onClick = {
                        viewModel.scan(file)
                    },
                    modifier = Modifier
                        .size(64.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "process",
                        modifier = Modifier.size(56.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}

fun uriToFile(selectedImg: Uri, context: Context): File {
    val contentResolver: ContentResolver = context.contentResolver
    val myFile = createTempFile(context)

    val inputStream = contentResolver.openInputStream(selectedImg) as InputStream
    val outputStream: OutputStream = FileOutputStream(myFile)
    val buf = ByteArray(1024)
    var len: Int
    while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
    outputStream.close()
    inputStream.close()

    return myFile
}

fun createTempFile(context: Context): File {
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(timeStamp, ".jpeg", storageDir)
}

val timeStamp: String = SimpleDateFormat(
    "dd-MMM-yyyy",
    Locale.US
).format(System.currentTimeMillis())
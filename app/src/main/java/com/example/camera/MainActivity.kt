package com.example.camera

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import com.example.camera.ui.theme.CameraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CameraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CameraButton(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CameraButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            openCamera(context)
        } else {
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { cameraLauncher.launch(Manifest.permission.CAMERA) }
        ) {
            Text(text = "Open Camera")
        }
    }
}

private fun openCamera(context: Context) {
    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    context.startActivity(cameraIntent)
}

@Preview(showBackground = true)
@Composable
fun CameraButtonPreview() {
    CameraTheme {
        CameraButton()
    }
}

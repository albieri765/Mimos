package com.example.mimos.screens.components
// usa tu paquete

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import com.example.mimos.R

@Composable
fun FondoHuellas(alpha: Float = 0.25f, content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de huellitas como background
        Image(
            painter = painterResource(R.drawable.ffff),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop, // ajusta según la imagen
            alpha = alpha                    // 0.05–0.12 para que no distraiga
        )
        // Capa de contenido principal
        content()
    }
}
package com.example.mimos.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.mimos.R
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale


@Composable
fun FeatureButtons(onClick: (String) -> Unit) {
    val boxSize = 150.dp  // ⬅️ Aumentamos el tamaño del Box

    val buttons = listOf(
        "veterinario" to R.drawable.vet,
        "comida"      to R.drawable.comidita,
        "accesorios"  to R.drawable.acce
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttons.forEach { (route, icon) ->
            Box(
                modifier = Modifier
                    .size(boxSize)
                    .clip(RoundedCornerShape(20.dp)) // esquinas más suaves
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable { onClick(route) },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = route,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),  // ⬅️ menos padding para que crezca más la imagen
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

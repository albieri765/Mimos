package com.example.mimos.screens.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mimos.R

@Composable

fun FoodImageGrid(navController: NavController) {
    val images = listOf(
        R.drawable.dog0, R.drawable.dog1, R.drawable.dog2,
        R.drawable.dog3, R.drawable.dog4, R.drawable.dog5
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally // ✅ CENTRAR COLUMNAS
    ) {
        images.chunked(2).forEachIndexed { rowIndex, row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                // ⇢ un solo horizontalArrangement que ya incluye spacing + centrado
                horizontalArrangement = Arrangement.spacedBy(
                    20.dp,
                    Alignment.CenterHorizontally   // centra la fila
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach { imageRes ->
                    val index = images.indexOf(imageRes) // ✅ ÍNDICE CORRECTO

                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn() + scaleIn()
                    ) {
                        Card(
                            shape = CircleShape,
                            modifier = Modifier
                                .size(130.dp)
                                .clickable {
                                    navController.navigate("dog/$index")
                                    // ✅ INDEX CORRECTO
                                },
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        ) {
                            Image(
                                painter = painterResource(id = imageRes),
                                contentDescription = "Perrito $index",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
    }
}


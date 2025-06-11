package com.example.mimos.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mimos.R

@Composable
fun FoodImageGrid(onItemClick: (String) -> Unit) {
    val images = listOf(
        R.drawable.logo, R.drawable.logo, R.drawable.logo,
        R.drawable.logo, R.drawable.logo, R.drawable.logo
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (row in images.chunked(2)) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                for ((index, img) in row.withIndex()) {
                    Card(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .clickable { onItemClick("producto$index") }
                    ) {
                        Image(
                            painter = painterResource(id = img),
                            contentDescription = "Producto $index",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

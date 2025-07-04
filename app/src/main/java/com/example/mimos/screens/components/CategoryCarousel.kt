package com.example.mimos.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CategoryCarousel(navController: NavController) {
    val categories = listOf("Juguetes", "Comida", "Ropa", "Camas", "Accesorios")
    val bgColor    = MaterialTheme.colorScheme.surfaceVariant
    val fgColor    = MaterialTheme.colorScheme.onSurfaceVariant

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(categories) { category ->
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(100.dp)
                    .clickable {
                        val route = when (category.lowercase()) {
                            "juguetes"    -> "categoria/juguetes"
                            "comida"      -> "categoria/comida"
                            "ropa"        -> "categoria/ropa"
                            "camas"       -> "categoria/camas"
                            "accesorios"  -> "categoria/accesorios"
                            else          -> "home"
                        }
                        navController.navigate(route)
                    }
                    .background(
                        color = bgColor,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = bgColor.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text  = category,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                    color = fgColor
                )
            }
        }
    }
}

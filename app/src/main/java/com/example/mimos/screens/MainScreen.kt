package com.example.mimos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mimos.screens.components.TopHeader
import com.example.mimos.screens.components.SearchBar
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import com.example.mimos.screens.components.ProductPager
import com.example.mimos.screens.components.CategoryCarousel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        TopHeader()
        Spacer(modifier = Modifier.height(8.dp))
        SearchBar()
        Text(
            text = "¡Encuentra todo lo que tu mascota necesita!",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(24.dp))
        ProductPager()
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Lo que necesita tu perrito:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        CategoryCarousel { category ->
            // Por ahora, solo mostramos el nombre en consola o con un Toast
            println("Categoría seleccionada: $category")

            // 🔜 Más adelante aquí irás a otra pantalla
            // navController.navigate("ruta_a_${category}")
        }
    }
}



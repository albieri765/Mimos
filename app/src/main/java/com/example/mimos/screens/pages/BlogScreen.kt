package com.example.mimos.screens.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BlogScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Bienvenidos al Blog de Mimos",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )

            Text(
                text = "En este espacio encontrarás consejos, historias inspiradoras y novedades sobre el cuidado de tus mascotas. Nuestro objetivo es ayudarte a brindar la mejor calidad de vida a tu compañero peludo.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Autores",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "• Albieri Vilcape — Desarrollador y amante de los perros.\n• Hugo Moreno — Redactor y asesor en salud canina.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Próximamente",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Estamos preparando artículos sobre nutrición natural, ejercicios para interiores y guías de primeros auxilios. ¡Vuelve pronto para descubrir más!",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

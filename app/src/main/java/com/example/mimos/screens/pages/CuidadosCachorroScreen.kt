package com.example.mimos.screens.pages   // cambia si tu paquete es distinto

/* ───────── IMPORTS ───────── */
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mimos.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuidadosCachorroScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cuidados para Cachorros") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            SectionTitle("Socialización y Juego")

            SectionImage(R.drawable.cachorro1, "Cachorro jugando")

            SectionText(
                """
                    Los primeros meses son cruciales para que tu cachorro desarrolle confianza.
                    Preséntalo a diferentes sonidos, personas y superficies de forma gradual.
                    Utiliza juguetes suaves y sesiones de juego cortas para evitar sobreestimulación.
                """.trimIndent()
            )

            SectionTitle("Vacunación y Desparasitación")

            SectionImage(R.drawable.cachorro2, "Visita al veterinario")

            SectionText(
                """
                    • Primera vacuna: 6‑8 semanas.  
                    • Refuerzos: cada 3‑4 semanas hasta los 4 meses.  
                    • Desparasitación interna: cada 15 días hasta los 3 meses; luego mensual.

                    Sigue el calendario de tu veterinario y evita paseos en lugares públicos
                    hasta completar el esquema de vacunas.
                """.trimIndent()
            )
        }
    }
}

/* ───────── HELPERS ───────── */
@Composable private fun SectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleLarge)
}

@Composable private fun SectionImage(resId: Int, desc: String) {
    Image(
        painter = painterResource(resId),
        contentDescription = desc,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable private fun SectionText(text: String) {
    Text(text, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Justify)
}

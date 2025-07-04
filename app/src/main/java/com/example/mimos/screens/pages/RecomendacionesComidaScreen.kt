package com.example.mimos.screens.pages

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
fun RecomendacionesComidaScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recomendaciones de Comida") },
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

            /* ───────── 1. PORCIONES ───────── */
            SectionTitle("Porciones según Peso")

            SectionImage(R.drawable.croq, "Croquetas balanceadas")

            SectionText(
                """
                    • Menos de 5 kg → 50 – 90 g diarios, dividido en 3 tomas.  
                    • 5 – 15 kg      → 90 – 210 g diarios, 2‑3 tomas.  
                    • 15 – 30 kg     → 210 – 380 g diarios, 2 tomas.  
                    • Más de 30 kg  → 380 g en adelante según actividad.
                    
                    ⚖️  Usa siempre una balanza de cocina o taza medidora para evitar sobrealimentación.
                """.trimIndent()
            )

            /* ───────── 2. DIETAS ───────── */
            SectionTitle("Tipos de Dieta")

            SectionImage(R.drawable.barf, "Comida BARF casera")

            SectionText(
                """
                    • **Pienso premium** — completo y equilibrado; busca proteínas ≥ 28 %.  
                    • **Dieta BARF** — mezcla cruda de carne, huesos carnosos y verduras (consulta con veterinario).  
                    • **Cocción casera** — pollo o pavo hervido + arroz integral + verduras al vapor.  
                    • **Dieta veterinaria** — fórmulas especiales para alergias, riñón, articulaciones, etc.

                    Recuerda introducir cualquier cambio de dieta en 7‑10 días para evitar problemas digestivos.
                """.trimIndent()
            )
        }
    }
}

/* ────────────── HELPERS ────────────── */

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun SectionImage(resId: Int, desc: String) {
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

@Composable
private fun SectionText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Justify
    )
}
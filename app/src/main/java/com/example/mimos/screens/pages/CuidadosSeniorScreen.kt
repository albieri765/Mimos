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
fun CuidadosSeniorScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cuidados para Perros Senior") },
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

            SectionTitle("Confort y Descanso")

            SectionImage(R.drawable.v1, "Cama ortopédica")

            SectionText(
                """
                    Con la edad, las articulaciones pueden volverse sensibles. Proporciona una cama 
                    ortopédica que distribuya el peso y alivie puntos de presión. Asegura un ambiente 
                    tranquilo y cálido, lejos de corrientes de aire.
                """.trimIndent()
            )

            SectionTitle("Chequeos Veterinarios")

            SectionImage(R.drawable.v2, "Chequeo médico")

            SectionText(
                """
                    • Examen completo cada 6 meses para detectar enfermedades tempranas.  
                    • Perfil sanguíneo y control de tiroides anualmente.  
                    • Supervisar peso y apetito para ajustar la dieta.

                    La detección precoz de problemas cardíacos, renales o articulares mejora
                    significativamente la calidad de vida en la etapa senior.
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

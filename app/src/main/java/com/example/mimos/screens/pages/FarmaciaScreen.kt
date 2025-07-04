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
import androidx.compose.ui.Alignment
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
fun FarmaciaScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Farmacia para Perritos") },
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

            SectionTitle("Antiparasitarios y Suplementos")

            SectionImage(R.drawable.farm1, "Antiparasitario oral")

            SectionText(
                """
                    El control de parásitos es fundamental para la salud de tu perro.  
                    Se recomienda aplicar antipulgas y garrapaticidas cada mes, además de desparasitación interna cada 3 meses.

                    También puedes complementar con suplementos como omega 3 para el pelaje o probióticos para la digestión.
                """.trimIndent()
            )

            SectionTitle("Botiquín Básico y Cuidado Diario")

            SectionImage(R.drawable.farm2, "Botiquín veterinario")

            SectionText(
                """
                    🩹 Tu botiquín para mascotas debe incluir:  
                    • Gasas estériles  
                    • Antiséptico (clorhexidina o yodo diluido)  
                    • Termómetro digital  
                    • Tijeras pequeñas  
                    • Pomada cicatrizante para uso veterinario

                    Además, asegúrate de tener siempre su carnet de vacunas actualizado y el número de su veterinario a la mano.
                """.trimIndent()
            )

            Text(
                text = "Encuentra todos estos productos en nuestra sección de farmacia online. ¡La salud de tu perrito es primero!",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
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

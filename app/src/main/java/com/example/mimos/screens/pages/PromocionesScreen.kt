package com.example.mimos.screens.pages      // ← cambia si tu paquete es distinto

/* ───────────── IMPORTS ───────────── */
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
import androidx.navigation.NavHostController
import com.example.mimos.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromocionesScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ofertas del Mes") },
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

            /* ───────── 1. SNACKS PREMIUM ───────── */
            PromotionCard(
                title    = "Snacks Premium 2 × 1",
                imageRes = R.drawable.dias2,
                description = """
                    Durante todo julio, llévate dos paquetes de nuestros snacks premium «Crunchy Bites» al precio de uno. 
                    Altos en proteínas y sin colorantes artificiales, perfectos para entrenamiento y premios.
                    
                    ⚡ Válido hasta el 31 de julio o agotar stock. ¡Aprovecha y consiente a tu engreído!
                """.trimIndent()
            )

            /* ───────── 2. CAMAS CONFORT ───────── */
            PromotionCard(
                title    = "30 % de Descuento en Camas Confort",
                imageRes = R.drawable.dias,
                description = """
                    Dale a tu perrito el descanso que merece con nuestras camas viscoelásticas «Sueño Mimoso».
                    Disponible en tallas S, M y L. Material hipoalergénico y funda desmontable lavable.
                    
                    ✨ Promoción válida solo online. Entrega gratis en Tacna por compras superiores a S/ 150.
                """.trimIndent()
            )
        }
    }
}

/* ───────── CARD REUTILIZABLE ───────── */
@Composable
private fun PromotionCard(
    title: String,
    imageRes: Int,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)

            Image(
                painter = painterResource(imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Text(
                description,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )
        }
    }
}

package com.example.mimos.screens.pages    // ← ajusta si tu paquete es distinto

/* ───────────── IMPORTS ───────────── */
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfertasScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Promociones y Descuentos") },
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

            Text(
                "¡Ahorra en tus compras de julio!",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                """
                    Durante todo el mes de julio disfruta de nuestras promociones exclusivas.
                    Usa el siguiente código al finalizar tu compra y obtén un descuento especial
                    inmediato en todos los productos seleccionados.
                """.trimIndent(),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )

            /* ───────── TARJETA DEL CÓDIGO ───────── */
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        "CÓDIGO DE DESCUENTO",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        "JULIO15",                  // ← aquí puedes cambiar el código
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        "15 % OFF",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            /* ───────── CONDICIONES ───────── */
            Text(
                """
                    • Válido del 1 al 31 de julio de 2025.  
                    • Un solo uso por cliente.  
                    • No acumulable con otras ofertas ni cupones.  
                    • Aplica únicamente a productos con stock en almacén.
                """.trimIndent(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )

            Text(
                "¡Aprovecha y llena de mimos a tu engreído con los mejores productos!",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

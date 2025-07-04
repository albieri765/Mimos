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
fun VeterinariosScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Veterinarios a la orden") },
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

            /* ───────── 1. SERVICIOS ───────── */
            SectionTitle("Servicios de la Clínica")

            SectionImage(R.drawable.vet1, "Clínica veterinaria")

            SectionText(
                """
                    Nuestra clínica ofrece consulta general, vacunación, cirugía menor y rayos X digital 
                    las 24 horas del día. Contamos con un equipo de veterinarios colegiados y
                    especialistas en medicina interna, dermatología y odontología canina.
                    
                    El servicio de urgencias está disponible sin cita previa y el tiempo promedio de
                    espera es de 15 minutos.
                """.trimIndent()
            )

            /* ───────── 2. CONSEJOS ───────── */
            SectionTitle("Revisiones Preventivas")

            SectionImage(R.drawable.vet2, "Revisión preventiva")

            SectionText(
                """
                    • **Chequeo anual** — Examen físico completo, desparasitación y control dental.  
                    • **Vacunas** — Refuerzo de vacuna múltiple y antirrábica cada 12 meses.  
                    • **Análisis de sangre** — Recomendado a partir de los 6 años para detectar enfermedades
                      renales o hepáticas en etapas tempranas.
                    
                    Programa recordatorios en tu app para no olvidar las próximas citas y mantener la salud 
                    de tu mascota al día.
                """.trimIndent()
            )
        }
    }
}

/* ───────── HELPERS ───────── */

@Composable
private fun SectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleLarge)
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
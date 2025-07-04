package com.example.mimos.screens.pages     // ← ajusta si tu paquete es distinto

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
import androidx.navigation.NavController
import com.example.mimos.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuidadosAdultoScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cuidados para Perros Adultos") },
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

            /* ───────── 1. ALIMENTACIÓN ───────── */
            SectionTitle("Alimentación Balanceada")

            SectionImage(
                resId = R.drawable.a2,      // ← agrega tu imagen
                desc  = "Croquetas nutritivas para adultos"
            )

            SectionText(
                """
                    A partir de los 12‑18 meses, tu perro necesita un alimento diseñado para mantener
                    su energía y salud muscular. Elige piensos con proteínas de alta calidad (≥ 25 %), 
                    ácidos grasos omega‑3 y 6 para un pelaje brillante, y niveles controlados de calorías
                    para evitar el sobrepeso.
                    
                    Divide la ración diaria en dos tomas y ajusta la cantidad según actividad y peso ideal.
                """.trimIndent()
            )

            /* ───────── 2. EJERCICIO ───────── */
            SectionTitle("Ejercicio y Estimulación")

            SectionImage(
                resId = R.drawable.a1,       // ← agrega tu imagen
                desc  = "Paseo en el parque"
            )

            SectionText(
                """
                    Un perro adulto requiere entre 30 y 60 minutos de actividad moderada al día:
                    
                    • Caminatas en diferentes rutas para estimular el olfato.  
                    • Juegos de lanzar y traer la pelota o disco.  
                    • Sesiones de entrenamiento de obediencia para fortalecer el vínculo.
                    
                    Varía las actividades para mantener su mente activa y prevenir el aburrimiento.
                """.trimIndent()
            )

            /* ───────── 3. SALUD PREVENTIVA ───────── */
            SectionTitle("Salud Preventiva")

            SectionText(
                """
                    • **Visita veterinaria** cada 6‑12 meses para chequeo general y refuerzo de vacunas.  
                    • **Control dental**: cepilla los dientes 2‑3 veces por semana y ofrece snacks dentales.  
                    • **Desparasitación interna y externa** según recomendación veterinaria.  
                    • **Chequeo de peso**: mantén un registro mensual para detectar variaciones.
                """.trimIndent()
            )

            SectionTitle("Consejo Mimos")

            SectionText(
                """
                    Recuerda equilibrar descanso, juego y disciplina. Un perro adulto feliz es aquel
                    que recibe amor, límites claros y rutinas saludables cada día 🐾.
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

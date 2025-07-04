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
fun InfoAccesoriosScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Info sobre Accesorios") },
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

            /* ─────────── 1. HISTORIA ─────────── */
            SectionTitle("Historia de las Marcas")

            SectionImage(R.drawable.collar, "Collar clásico")

            SectionText(
                """
                    Desde los primeros collares de cuero artesanal hasta los modernos arneses ergonómicos,
                    las marcas han evolucionado para priorizar la comodidad y la seguridad de nuestras mascotas. 
                    
                    • 1920‑1940: auge de los collares de hebilla de latón.  
                    • 1970: aparecen los primeros arneses acolchados.  
                    • 2010→hoy: materiales hipoalergénicos y diseños personalizados.
                """.trimIndent()
            )

            /* ─────────── 2. MATERIALES ─────────── */
            SectionTitle("Materiales Recomendados")

            SectionImage(R.drawable.camita, "Cama viscoelástica")

            SectionText(
                """
                    • Nylon balístico — resistente y ligero; ideal para correas de uso diario.  
                    • Algodón orgánico — suave y transpirable, perfecto para piel sensible.  
                    • Espuma viscoelástica — distribuye el peso y cuida articulaciones en camas y cojines.  
                    • Silicona grado alimenticio — cuencos y juguetes no tóxicos, fáciles de lavar.
                """.trimIndent()
            )

            /* ─────────── 3. CONSEJOS ─────────── */
            SectionTitle("Consejos de Uso y Limpieza")

            SectionImage(R.drawable.bolsa, "Bolsa de transporte")

            SectionText(
                """
                    1️⃣ Ajuste correcto  
                       Pasa dos dedos entre el collar/arnés y el cuello del perro.

                    2️⃣ Limpieza semanal  
                       • Nylon: agua tibia + jabón neutro.  
                       • Cama viscoelástica: funda extraíble a 30 °C.

                    3️⃣ Inspección mensual  
                       Revisa desgaste, hebillas o costuras abiertas y reemplaza si es necesario.

                    4️⃣ Rotación de juguetes  
                       Rota juguetes cada 7 días y sustituye los muy mordidos.
                """.trimIndent()
            )

            Text(
                "¿Quieres ver los accesorios disponibles en nuestra tienda? ¡Explóralos ahora!",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

/* Helpers para no repetir código */
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
        text,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Justify
    )
}

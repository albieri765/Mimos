package com.example.mimos.screens.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mimos.R

/* ───── MODELO DE DATOS ───── */
data class Dog(
    val id: Int,
    val nombre: String,
    val historia: String,
    val imagenRes: Int,
    val raza: String,
    val edad: String,
    val curiosidades: String
)

/* ───── LISTA EN MEMORIA ───── */
private val dogs = listOf(
    Dog(
        0, "Luna",
        "Luna fue rescatada de un refugio y hoy es la reina del hogar.",
        R.drawable.dog0,
        raza = "Labrador Retriever",
        edad = "3 años",
        curiosidades = "Le encanta chapotear en el agua y sabe dar la pata para saludar."
    ),
    Dog(
        1, "Max",
        "Max ama los paseos en la playa y nunca se pierde una pelota.",
        R.drawable.dog1,
        raza = "Golden Retriever",
        edad = "4 años",
        curiosidades = "Puede atrapar una pelota a 10 m de distancia ¡sin fallar!"
    ),
    Dog(
        2, "Canela",
        "Canela es la más cariñosa; siempre busca un regazo para dormir.",
        R.drawable.dog2,
        raza = "Beagle",
        edad = "2 años",
        curiosidades = "Tiene un olfato increíble: encuentra sus galletas escondidas en segundos."
    ),
    Dog(
        3, "Rocky",
        "Rocky fue entrenado para ayudar a niños con autismo.",
        R.drawable.dog3,
        raza = "Pastor Australiano",
        edad = "5 años",
        curiosidades = "Sabe 15 comandos de obediencia y participa en terapias asistidas."
    ),
    Dog(
        4, "Daisy",
        "Daisy es curiosa y le encanta descubrir rincones del jardín.",
        R.drawable.dog4,
        raza = "Cocker Spaniel",
        edad = "1 año y medio",
        curiosidades = "Tiene una colección de 30 juguetes que guarda en un solo rincón."
    ),
    Dog(
        5, "Thor",
        "Thor, un gigante amable que cuida de otras mascotas del vecindario.",
        R.drawable.dog5,
        raza = "Gran Danés",
        edad = "6 años",
        curiosidades = "A pesar de su tamaño, es temeroso de los gatitos recién nacidos."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogDetailScreen(navController: NavController, dogId: Int) {
    val dog = dogs.firstOrNull { it.id == dogId } ?: return

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Conoce a ${dog.nombre}") },
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            /* ── Imagen principal ── */
            Image(
                painter = painterResource(dog.imagenRes),
                contentDescription = dog.nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            )

            /* ── Historia ── */
            Text(
                dog.historia,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )

            /* ── Datos rápidos ── */
            Text("Datos rápidos", style = MaterialTheme.typography.titleMedium)
            Text("• Raza: ${dog.raza}", style = MaterialTheme.typography.bodyMedium)
            Text("• Edad:  ${dog.edad}", style = MaterialTheme.typography.bodyMedium)

            /* ── Curiosidades ── */
            Text("Curiosidades", style = MaterialTheme.typography.titleMedium)
            Text(
                dog.curiosidades,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
        }
    }
}
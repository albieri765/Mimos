package com.example.mimos.screens.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mimos.R

// Datos simples en memoria
private val dogs = listOf(
    Dog(0, "Luna",      "Luna fue rescatada de un refugio y hoy es la reina del hogar.",      R.drawable.dog0),
    Dog(1, "Max",       "Max ama los paseos en la playa y nunca se pierde una pelota.",       R.drawable.dog1),
    Dog(2, "Canela",    "Canela es la más cariñosa; siempre busca un regazo para dormir.",    R.drawable.dog2),
    Dog(3, "Rocky",     "Rocky fue entrenado para ayudar a niños con autismo.",               R.drawable.dog3),
    Dog(4, "Daisy",     "Daisy es curiosa y le encanta descubrir rincones del jardín.",       R.drawable.dog4),
    Dog(5, "Thor",      "Thor, un gigante amable que cuida de otras mascotas del vecindario.",R.drawable.dog5)
)

data class Dog(val id: Int, val nombre: String, val historia: String, val imagenRes: Int)

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
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(dog.imagenRes),
                contentDescription = dog.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                dog.historia,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

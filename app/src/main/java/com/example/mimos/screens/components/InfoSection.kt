package com.example.mimos.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InfoSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Bienvenido a Best for Pets",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Somos tu tienda en línea de confianza en Chile, dedicada a ofrecer una amplia gama de productos de alta calidad para tus mascotas. Nuestro objetivo es satisfacer todas las necesidades de tus compañeros peludos, asegurando su bienestar y felicidad."
        )

        Text(
            text = "Alimentos para Perros y Gatos",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "En Best for Pets, entendemos la importancia de una nutrición adecuada. Por ello, ofrecemos una selección de alimentos secos y húmedos de marcas reconocidas, adaptados a las diferentes etapas de vida y requerimientos dietéticos de perros y gatos. Ya sea que busques opciones premium, super premium o dietas especializadas, aquí encontrarás lo mejor para tu mascota."
        )

        Text(
            text = "Accesorios y Juguetes",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Descubre nuestra variedad de accesorios que facilitan el cuidado diario de tus mascotas. Desde collares, arneses y correas hasta camas cómodas y jaulas de transporte seguras. Además, contamos con una amplia gama de juguetes interactivos y funcionales que proporcionarán horas de diversión y estimulación mental a tus amigos peludos."
        )

        Text(
            text = "Higiene y Cuidado",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "La salud y el bienestar de tu mascota son nuestra prioridad. Por ello, ofrecemos productos de higiene como shampoos, acondicionadores y artículos de limpieza. También disponemos de antiparasitarios, suplementos y vitaminas para apoyar su salud en general."
        )

        Text(
            text = "Beneficios de Comprar en Best for Pets",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Variedad de Productos: ..."
        )
    }
}

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
            text = "Bienvenidos a Mimos",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Somos una tienda de mascotas con corazón tacneño, dedicada a brindar lo mejor para tus compañeros peludos. En Mimos, creemos que cada mascota merece amor, salud y alegría, por eso trabajamos día a día para ofrecerte productos de calidad, con atención cercana y personalizada."
        )

        Text(
            text = "Nutrición para Perros y Gatos",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Contamos con una variedad de alimentos secos y húmedos seleccionados para cada etapa de vida y necesidad específica. Trabajamos con marcas reconocidas a nivel nacional e internacional, ideales tanto para perritos juguetones como para gatitos exigentes. También ofrecemos opciones hipoalergénicas, sin granos y dietas veterinarias especiales."
        )

        Text(
            text = "Accesorios, Camas y Más",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Todo lo que tu mascota necesita está aquí: collares, arneses, correas, transportadoras, camas suaves y mucho más. Cada producto es escogido pensando en la comodidad, seguridad y estilo de tu engreído."
        )

        Text(
            text = "Juguetes y Estimulación",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Sabemos que una mascota feliz es una mascota activa. En Mimos encontrarás juguetes interactivos, mordedores, peluches y opciones que estimulan mente y cuerpo, ideales para todas las edades."
        )

        Text(
            text = "Higiene y Bienestar",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Ofrecemos shampoos suaves, toallitas limpiadoras, antipulgas, peines, vitaminas y suplementos. Todos pensados para mantener a tu mascota saludable, limpia y mimada como se merece."
        )

        Text(
            text = "¿Por qué elegir Mimos?",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = """
                • Atención local en Tacna con amor y experiencia.  
                • Productos seleccionados por amantes de los animales.  
                • Promociones frecuentes y entregas a domicilio.  
                • Asesoría personalizada y acompañamiento postventa.
            """.trimIndent()
        )

        Text(
            text = "Gracias por confiar en nosotros. En Mimos, tu mascota siempre será parte de nuestra familia 🐾.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

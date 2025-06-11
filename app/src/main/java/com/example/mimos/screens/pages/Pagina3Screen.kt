package com.example.mimos.screens.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mimos.R
import com.example.mimos.data.ProductoModel
import com.example.mimos.screens.components.SearchBar
import com.example.mimos.screens.components.FooterSection

@Composable
fun Pagina3Screen(titulo: String,productos: List<ProductoModel>, total: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Barra de búsqueda
        SearchBar()

        Spacer(modifier = Modifier.height(16.dp))

        // Frase o título principal
        Text(
            text = "¡Explora lo mejor para tu mascota!",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Imagen (la misma del ViewPager, pero sin clic)
        Image(
            painter = painterResource(R.drawable.promoc), // cambia a la imagen real que usas
            contentDescription = "Imagen ilustrativa",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Segundo título
        Text(
            text = "Beneficios destacados",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Texto explicativo
        Text(
            text = "Aquí puedes describir por qué esta sección es útil, interesante o relevante para los dueños de mascotas.",
            fontSize = 16.sp,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Íconos sociales al final
        FooterSection()
    }
}

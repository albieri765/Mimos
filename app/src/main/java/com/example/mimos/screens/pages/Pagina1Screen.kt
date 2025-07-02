package com.example.mimos.screens.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mimos.R
import com.example.mimos.view.ProductoViewModel
import com.example.mimos.screens.components.SearchBar
import com.example.mimos.screens.components.FooterSection
import kotlinx.coroutines.launch

@Composable
fun Pagina1Screen(navController: NavController, viewModel: ProductoViewModel = viewModel()) {
    val productos by viewModel.productos.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = "pagina1") {
        viewModel.obtenerProductosPorPagina("pagina1")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "¡Explora lo mejor para tu mascota!",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Imagen sin navegación circular
            Image(
                painter = painterResource(id = R.drawable.promoa),
                contentDescription = "Promo",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Hay ${productos.size} productos",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            productos.forEach { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val imagenUrlModificada = if (!producto.imagen.isNullOrBlank()) {
                            producto.imagen.replace("127.0.0.1", "10.0.2.2")
                        } else {
                            ""
                        }

                        AsyncImage(
                            model = imagenUrlModificada,
                            contentDescription = producto.nombre,
                            modifier = Modifier.size(150.dp)
                        )

                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = producto.nombre, fontSize = 16.sp)
                            Text(text = "Precio: \$${producto.precio}", fontSize = 14.sp)
                            Text(text = "Peso: ${producto.peso}", fontSize = 14.sp)
                            Text(text = "Marca: ${producto.marca}", fontSize = 14.sp)
                            Text(text = "Categoría: ${producto.categoria.nombre}", fontSize = 14.sp)

                        }

                        IconButton(onClick = {
                            scope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "${producto.nombre} se añadió al carrito",
                                    actionLabel = "VER CARRITO"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    navController.navigate("carrito") // ✅ Asegúrate que esta ruta exista
                                }
                            }
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_cart),
                                contentDescription = "Añadir al carrito",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            FooterSection()
        }

        // ✅ Snackbar visual al fondo de la pantalla
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}

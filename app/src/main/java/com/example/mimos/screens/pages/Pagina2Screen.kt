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
fun Pagina2Screen(navController: NavController, viewModel: ProductoViewModel = viewModel()) {
    val productos  by viewModel.productosFiltrados.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val query by viewModel.searchQuery.collectAsState()

    val scope = rememberCoroutineScope()

    // ✅ Cargar productos del orden 1 al 10 solo al entrar a esta pantalla
    LaunchedEffect(key1 = "pagina2") {
        viewModel.obtenerProductosPorPagina("pagina2")
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                query         = query,
                onQueryChanged = viewModel::setSearchQuery,
                onClear        = { viewModel.setSearchQuery("") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "¡Explora lo mejor para tu mascota!",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.promob),
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
                            "https://via.placeholder.com/150"
                        }

                        AsyncImage(
                            model = imagenUrlModificada,
                            contentDescription = producto.nombre,
                            modifier = Modifier
                                .size(120.dp)          // un poco más pequeño si quieres
                                .padding(end = 12.dp)  // ← separa la imagen del texto
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
                                    navController.navigate("carrito")
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

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}

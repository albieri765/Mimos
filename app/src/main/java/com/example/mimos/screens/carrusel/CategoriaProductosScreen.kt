package com.example.mimos.screens.carrusel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mimos.view.ProductoViewModel
import com.example.mimos.data.ProductoModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriaProductosScreen(
    nombreCategoria: String,
    navController: NavController,
    viewModel: ProductoViewModel
) {
    val productos by viewModel.productosPorCategoria.collectAsState()

    LaunchedEffect(nombreCategoria) {
        viewModel.obtenerProductosPorCategoria(nombreCategoria)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Categoría: $nombreCategoria") }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (productos.isEmpty()) {
                Text(
                    text = "No hay productos en esta categoría.",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(productos) { producto ->
                        ProductoCard(producto)
                    }
                }
            }
        }
    }
}


@Composable
fun ProductoCard(producto: ProductoModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = producto.imagen?.replace("127.0.0.1", "10.0.2.2"),
                contentDescription = producto.nombre,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(producto.nombre ?: "Sin nombre")
                Text("Precio: \$${producto.precio}")
                Text("Marca: ${producto.marca}")
            }
        }
    }
}

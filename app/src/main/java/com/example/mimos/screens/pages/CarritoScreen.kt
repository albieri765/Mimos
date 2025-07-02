package com.example.mimos.screens.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mimos.view.ProductoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(
    navController: NavController,
    viewModel: ProductoViewModel
) {
    val carrito     = viewModel.carrito.collectAsState()
    val cantidades  = viewModel.cantidades.collectAsState()
    val total       = viewModel.totalCarrito.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrito de Compras") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    if (carrito.value.isNotEmpty()) {
                        IconButton(onClick = { viewModel.limpiarCarrito() }) {
                            Icon(Icons.Default.DeleteSweep, contentDescription = "Vaciar carrito")
                        }
                    }
                }
            )
        }
    ) { inner ->
        if (carrito.value.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(inner),
                contentAlignment = Alignment.Center
            ) {
                Text("Tu carrito está vacío 😔")
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(inner)
                    .padding(16.dp)
            ) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    carrito.value
                        .distinctBy { it.id }
                        .forEach { producto ->
                            item(key = producto.id) {
                                ListItem(
                                    headlineContent = { Text(producto.nombre) },
                                    supportingContent = {
                                        Text("S/. ${producto.precio} × ${cantidades.value[producto.id]}")
                                    },
                                    leadingContent = {
                                        AsyncImage(
                                            model = producto.imagen?.replace("127.0.0.1", "10.0.2.2"),
                                            contentDescription = producto.nombre,
                                            modifier = Modifier.size(48.dp)
                                        )
                                    },
                                    trailingContent = {
                                        IconButton(
                                            onClick = { viewModel.quitarDelCarrito(producto) }
                                        ) {
                                            Icon(Icons.Default.Delete, contentDescription = "Quitar")
                                        }
                                    }
                                )
                                Divider()
                            }
                        }
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Total: S/. ${"%.2f".format(total.value)}",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = { /* TODO: implementar pago */ },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("PAGAR") }
            }
        }
    }
}

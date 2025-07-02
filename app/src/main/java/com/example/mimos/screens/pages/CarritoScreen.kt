package com.example.mimos.screens.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mimos.view.ProductoViewModel
import com.example.mimos.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(navController: NavController, viewModel: ProductoViewModel) {
    val carrito = viewModel.carrito.collectAsState()
    val total   = viewModel.totalCarrito.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrito de Compras") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { inner ->
        if (carrito.value.isEmpty()) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(inner),
                contentAlignment = Alignment.Center
            ) {
                Text("Tu carrito está vacío 😔")
            }
        } else {
            Column(
                Modifier
                    .padding(inner)
                    .padding(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(carrito.value) { producto ->
                        ListItem(
                            headlineContent = { Text(producto.nombre) },
                            supportingContent = { Text("S/. ${producto.precio}") },
                            leadingContent = {
                                Image(
                                    painter = painterResource(R.drawable.ic_cart),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        )
                        Divider()
                    }
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Total: S/. ${"%.2f".format(total.value)}",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = { /* Implementa pago más adelante */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("PAGAR")
                }
            }
        }
    }
}

package com.example.mimos.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mimos.view.ProductoViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import com.example.mimos.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartDrawerContent(
    viewModel: ProductoViewModel,   // ← pasamos el VM global
    onClose: () -> Unit,
    onViewCart: () -> Unit,
    onPay: () -> Unit
) {
    val carrito    = viewModel.carrito.collectAsState()
    val cantidades = viewModel.cantidades.collectAsState()
    val total      = viewModel.totalCarrito.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE0B2))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        /* ---------- Encabezado y lista ---------- */
        Column(Modifier.weight(1f)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Mi Carrito",
                    style = MaterialTheme.typography.titleLarge
                )
                IconButton(onClick = onClose) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar")
                }
            }

            Divider(Modifier.padding(vertical = 8.dp))

            if (carrito.value.isEmpty()) {
                Text("Tu carrito está vacío 😔")
            } else {
                LazyColumn {
                    carrito.value
                        .distinctBy { it.id }
                        .forEach { producto ->
                            item(key = producto.id) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        model = producto.imagen?.replace("127.0.0.1", "10.0.2.2"),
                                        contentDescription = producto.nombre,
                                        modifier = Modifier.size(40.dp)
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Column(Modifier.weight(1f)) {
                                        Text(producto.nombre, maxLines = 1)
                                        Text(
                                            "S/. ${producto.precio} × ${cantidades.value[producto.id]}",
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                    IconButton(
                                        onClick = { viewModel.quitarDelCarrito(producto) }
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = "Quitar")
                                    }
                                }
                            }
                        }
                }
            }
        }

        /* ---------- Total y botones ---------- */
        Column {
            Text(
                text = "Total: S/. ${"%.2f".format(total.value)}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = onPay,
                enabled = carrito.value.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) { Text("PAGAR") }

            Spacer(Modifier.height(8.dp))

            OutlinedButton(
                onClick = onViewCart,
                modifier = Modifier.fillMaxWidth(),
                enabled = carrito.value.isNotEmpty()
            ) { Text("VER CARRITO") }
        }
    }
}

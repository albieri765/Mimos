package com.example.mimos.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CartDrawerContent(
    onClose: () -> Unit,
    onViewCart: () -> Unit,
    onPay: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE0B2))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Tu carrito está vacío",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Total a pagar: $0.00",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        }

        Column {
            Button(
                onClick = onPay,
                enabled = false, // por ahora deshabilitado
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("PAGAR")
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = onViewCart,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("VER CARRITO")
            }
        }
    }
}

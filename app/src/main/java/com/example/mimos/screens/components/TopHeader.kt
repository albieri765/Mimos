package com.example.mimos.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mimos.R

@Composable
fun TopHeader(
    onMenuClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onUserClick: () -> Unit,
    onCartClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFF9900))
            .statusBarsPadding()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onMenuClick) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú", tint = Color.White)
        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(40.dp) // Ajusta el tamaño como necesites
                .clickable { onHomeClick() } // Aquí ejecutas la acción al tocar el logo
        )
        Row {
            IconButton(onClick = onNotificationsClick) {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notificaciones", tint = Color.White)
            }
            IconButton(onClick = onCartClick) {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Carrito")
            }

            IconButton(onClick = { onUserClick() }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Usuario", tint = Color.White)
            }
            IconButton(onClick = onHomeClick) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Inicio")
            }
        }
    }
}

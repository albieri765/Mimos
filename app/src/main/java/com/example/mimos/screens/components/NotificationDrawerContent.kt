package com.example.mimos.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mimos.R



@Composable
fun NotificationDrawerContent(
    onClose: () -> Unit,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE0B2))
            .padding(16.dp)
    ) {
        Text(
            text = "Notificaciones",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        NotificationItem(
            imageResId = R.drawable.ic_notificaciona,
            title = "Promo especial para ti",
            onClick = { onItemClick("promo") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        NotificationItem(
            imageResId = R.drawable.ic_notificacionb,
            title = "Nuevo producto disponible",
            onClick = { onItemClick("nuevo") }
        )

    }
}

@Composable
fun NotificationItem(
    imageResId: Int,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = title,
            modifier = Modifier
                .size(32.dp)
                .padding(end = 12.dp)
        )
        Text(text = title, color = Color.Black)
    }
}

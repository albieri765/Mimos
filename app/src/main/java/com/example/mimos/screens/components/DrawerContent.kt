package com.example.mimos.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mimos.R
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore


@Composable
fun DrawerContent(
    onNavigate: (String) -> Unit,
    onCloseDrawer: () -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            DrawerItemWithSubOptions(
                title = "Descuentos",
                subItems = listOf("Promociones y Descuento", "Ofertas del mes"),
                onItemClick = onNavigate
            )

            DrawerItemWithSubOptions(
                title = "Farmacia",
                subItems = listOf("Veterinarios a la orden", "Farmacia para perritos"),
                onItemClick = onNavigate
            )

            DrawerExpandableGridItem(
                title = "Cuidados",
                items = listOf("Cachorro", "Adulto", "Senior"),
                onItemClick = onNavigate
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Blog",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigate("blog") }
                    .padding(vertical = 12.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun DrawerItemWithSubOptions(
    title: String,
    subItems: List<String>,
    onItemClick: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(vertical = 12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null
            )
        }

        if (expanded) {
            subItems.forEach { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(item) }
                        .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun DrawerExpandableGridItem(
    title: String,
    items: List<String>,
    onItemClick: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(vertical = 12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null
            )
        }

        if (expanded) {
            items.chunked(2).forEach { rowItems ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    rowItems.forEach { label ->
                        Button(
                            onClick = { onItemClick(label) },
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 4.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCC80))
                        ) {
                            Text(text = label)
                        }
                    }
                }
            }
        }
    }
}

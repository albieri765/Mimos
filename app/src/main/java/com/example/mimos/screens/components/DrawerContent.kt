package com.example.mimos.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class DrawerButtonItem(val label: String, val route: String)

@Composable
fun DrawerContent(
    onNavigate: (String) -> Unit,
    onCloseDrawer: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            DrawerItemWithSubOptions(
                title = "Descuentos",
                subItems = mapOf(
                    "promociones" to "Promociones y Descuento",
                    "ofertas" to "Ofertas del mes"
                ),
                onItemClick = onNavigate,
                onCloseDrawer = onCloseDrawer
            )

            DrawerItemWithSubOptions(
                title = "Farmacia",
                subItems = mapOf(
                    "veterinarios" to "Veterinarios a la orden",
                    "farmacia" to "Farmacia para perritos"
                ),
                onItemClick = onNavigate,
                onCloseDrawer = onCloseDrawer
            )

            DrawerExpandableGridItem(
                title = "Cuidados",
                items = listOf(
                    DrawerButtonItem("Cachorro", "cachorro"),
                    DrawerButtonItem("Adulto", "adulto"),
                    DrawerButtonItem("Senior", "senior")
                ),
                onItemClick = onNavigate,
                onCloseDrawer = onCloseDrawer
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Blog",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onCloseDrawer()
                        onNavigate("blog")
                    }
                    .padding(vertical = 12.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun DrawerItemWithSubOptions(
    title: String,
    subItems: Map<String, String>,
    onItemClick: (String) -> Unit,
    onCloseDrawer: () -> Unit
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
            subItems.forEach { (route, label) ->
                Text(
                    text = label,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onCloseDrawer()
                            onItemClick(route)
                        }
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
    items: List<DrawerButtonItem>,
    onItemClick: (String) -> Unit,
    onCloseDrawer: () -> Unit
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
            items.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowItems.forEach { item ->
                        Button(
                            onClick = {
                                onCloseDrawer()
                                onItemClick(item.route)
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCC80))
                        ) {
                            Text(text = item.label)
                        }
                    }
                }
            }
        }
    }
}

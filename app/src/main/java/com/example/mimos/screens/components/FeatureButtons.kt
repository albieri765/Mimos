package com.example.mimos.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.mimos.R

@Composable
fun FeatureButtons(onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val buttons = listOf(
            "veterinario" to R.drawable.ic_vet,
            "comida" to R.drawable.ic_food,
            "accesorios" to R.drawable.ic_accessories
        )

        buttons.forEach { (route, icon) ->
            Image(
                painter = painterResource(id = icon),
                contentDescription = route,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clickable { onClick(route) }
            )
        }
    }
}

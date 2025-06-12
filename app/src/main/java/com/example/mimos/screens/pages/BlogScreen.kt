package com.example.mimos.screens.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Blog") })
        }
    ) { innerPadding ->
        Text(
            text = "Contenido del blog",
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        )
    }
}
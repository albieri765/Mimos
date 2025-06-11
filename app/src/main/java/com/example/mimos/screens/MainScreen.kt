package com.example.mimos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mimos.screens.components.TopHeader
import com.example.mimos.screens.components.SearchBar
import androidx.compose.ui.Alignment
import com.example.mimos.screens.components.ProductPager
import com.example.mimos.screens.components.CategoryCarousel
import com.example.mimos.screens.components.SectionDivider
import com.example.mimos.screens.components.FeatureButtons
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.mimos.screens.components.DrawerContent
import com.example.mimos.screens.components.FoodImageGrid
import com.example.mimos.screens.components.FooterSection
import com.example.mimos.screens.components.InfoSection
import com.example.mimos.screens.components.SectionTitle
import com.example.mimos.screens.components.DrawerContent
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scrollState = rememberScrollState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color(0xFFFFE0B2))
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {
                DrawerContent(
                    onNavigate = { route ->
                        println("Navegar a: $route") // En el futuro usarás navController.navigate(route)
                    },
                    onCloseDrawer = {
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ){
        Scaffold(
            topBar = {
                TopHeader {
                    scope.launch { drawerState.open() }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(scrollState)
                    .padding(16.dp)
            ) {
                SearchBar()
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "¡Encuentra todo lo que tu mascota necesita!",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(24.dp))
                ProductPager()
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Lo que necesita tu perrito:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 8.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))
                CategoryCarousel { println("Categoría seleccionada: $it") }

                Spacer(modifier = Modifier.height(24.dp))
                SectionDivider(text = "Explora más opciones")
                Spacer(modifier = Modifier.height(12.dp))

                FeatureButtons { println("Clic en: $it") }

                Spacer(modifier = Modifier.height(24.dp))
                SectionTitle(title = "Comida para perros")
                FoodImageGrid { println("Clic en imagen: $it") }

                SectionTitle(title = "Sobre Nosotros")
                InfoSection()

                Spacer(modifier = Modifier.height(24.dp))
                FooterSection()
            }
        }
    }
}


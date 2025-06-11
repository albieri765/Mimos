package com.example.mimos.screens

import androidx.compose.foundation.ScrollState
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
import com.example.mimos.screens.components.CartDrawerContent
import com.example.mimos.screens.components.FoodImageGrid
import com.example.mimos.screens.components.FooterSection
import com.example.mimos.screens.components.InfoSection
import com.example.mimos.screens.components.SectionTitle
import com.example.mimos.screens.components.DrawerContent
import com.example.mimos.screens.components.LoginDialog
import com.example.mimos.screens.components.NotificationDrawerContent
import kotlinx.coroutines.launch
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mimos.screens.pages.Pagina1Screen
import com.example.mimos.screens.pages.Pagina2Screen
import com.example.mimos.screens.pages.Pagina3Screen
import com.example.mimos.view.ProductoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var showLoginDialog by remember { mutableStateOf(false) }
    val cartDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollState = rememberScrollState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val notificationDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()

    // ✅ ViewModel global compartido
    val productoViewModel: ProductoViewModel = viewModel()

    ModalNavigationDrawer(
        drawerState = cartDrawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color(0xFFFFE0B2))
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {
                CartDrawerContent(
                    onClose = { scope.launch { cartDrawerState.close() } },
                    onViewCart = { navController.navigate("carrito") },
                    onPay = { println("Pagar") }
                )
            }
        }
    ) {
        ModalNavigationDrawer(
            drawerState = notificationDrawerState,
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(Color(0xFFFFE0B2))
                        .windowInsetsPadding(WindowInsets.statusBars)
                ) {
                    NotificationDrawerContent(
                        onClose = { scope.launch { notificationDrawerState.close() } },
                        onItemClick = { println("Click en notificación: $it") }
                    )
                }
            }
        ) {
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
                                navController.navigate(route)
                                scope.launch { drawerState.close() }
                            },
                            onCloseDrawer = { scope.launch { drawerState.close() } }
                        )
                    }
                }
            ) {
                if (showLoginDialog) {
                    LoginDialog(
                        onDismiss = { showLoginDialog = false },
                        onLoginClick = { email, password ->
                            println("Iniciar sesión con: $email y $password")
                            showLoginDialog = false
                        },
                        onForgotPassword = {
                            println("Olvidaste tu contraseña")
                        },
                        onCreateAccount = {
                            println("Crear nueva cuenta")
                        }
                    )
                }

                Scaffold(
                    topBar = {
                        TopHeader(
                            onMenuClick = { scope.launch { drawerState.open() } },
                            onNotificationsClick = { scope.launch { notificationDrawerState.open() } },
                            onUserClick = { showLoginDialog = true },
                            onCartClick = { scope.launch { cartDrawerState.open() } },
                            onHomeClick = {
                                navController.navigate("home") {
                                    popUpTo("home") { inclusive = true }
                                }
                                scope.launch {
                                    scrollState.animateScrollTo(0)
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            HomeContent(
                                scrollState = scrollState,
                                navController = navController,
                                onImageClick = { index ->
                                    val route = when (index) {
                                        0 -> "pagina1"
                                        1 -> "pagina2"
                                        2 -> "pagina3"
                                        else -> "pagina1"
                                    }
                                    navController.navigate(route)
                                }
                            )
                        }
                        composable("pagina1") {
                            Pagina1Screen(navController = navController, viewModel = productoViewModel)
                        }
                        // ✅ Agregar la ruta carrito
                        composable("carrito") {
                            PaginaDetalle(titulo = "Carrito")
                        }
                        // Opcionalmente otras páginas
                        composable("pagina2") {
                            PaginaDetalle(titulo = "Página 2")
                        }
                        composable("pagina3") {
                            PaginaDetalle(titulo = "Página 3")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeContent(scrollState: ScrollState, navController: NavHostController,onImageClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
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
        ProductPager(onImageClick = onImageClick)

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

@Composable
fun PaginaDetalle(titulo: String) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = titulo, style = MaterialTheme.typography.headlineSmall)
    }
}

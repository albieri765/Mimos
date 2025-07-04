package com.example.mimos.screens
import androidx.navigation.NavType
import androidx.navigation.navArgument

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
import com.example.mimos.screens.carrusel.CategoriaProductosScreen
import com.example.mimos.screens.pages.BlogScreen
import com.example.mimos.screens.pages.CarritoScreen
import com.example.mimos.screens.pages.CuidadosAdultoScreen
import com.example.mimos.screens.pages.CuidadosCachorroScreen
import com.example.mimos.screens.pages.CuidadosSeniorScreen
import com.example.mimos.screens.pages.DogDetailScreen
import com.example.mimos.screens.pages.FarmaciaScreen
import com.example.mimos.screens.pages.InfoAccesoriosScreen
import com.example.mimos.screens.pages.OfertasScreen
import com.example.mimos.screens.pages.Pagina1Screen
import com.example.mimos.screens.pages.Pagina2Screen
import com.example.mimos.screens.pages.Pagina3Screen
import com.example.mimos.screens.pages.PromocionesScreen
import com.example.mimos.screens.pages.RecomendacionesComidaScreen
import com.example.mimos.screens.pages.VeterinariosScreen
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
                CartDrawerContent(                 // ← añade el ViewModel aquí
                    viewModel = productoViewModel,
                    onClose = { scope.launch { cartDrawerState.close() } },
                    onViewCart = {
                        navController.navigate("carrito")
                        scope.launch { cartDrawerState.close() }
                    },
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
                LaunchedEffect(Unit) {
                    productoViewModel.obtenerTodosLosProductos()
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),        // opcional, si no lo tienes
                    containerColor = Color.Transparent,
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
                                },
                                viewModel = productoViewModel        // ✅ la instancia global
                            )
                        }
                        composable("pagina1") {
                            Pagina1Screen(navController = navController, viewModel = productoViewModel)
                        }
                        composable("pagina2") {
                            Pagina2Screen(navController = navController, viewModel = productoViewModel)
                        }
                        composable("pagina3") {
                            Pagina3Screen(navController = navController, viewModel = productoViewModel)
                        }

                        composable("promociones") { PromocionesScreen(navController) }
                            composable("ofertas") { OfertasScreen(navController) }
                            composable("veterinarios") { VeterinariosScreen(navController) }
                            composable("farmacia") { FarmaciaScreen(navController) }
                        composable("cachorro") { CuidadosCachorroScreen(navController) }
                        composable("adulto") { CuidadosAdultoScreen(navController) }
                        composable("senior") { CuidadosSeniorScreen(navController) }
                        composable("blog") { BlogScreen(navController) }
                        composable("categoria/{nombreCategoria}") { backStackEntry ->
                            val nombreCategoria = backStackEntry.arguments?.getString("nombreCategoria") ?: ""
                            CategoriaProductosScreen(nombreCategoria = nombreCategoria, navController = navController, viewModel = productoViewModel)

                        }

                        composable("recomendaciones_comida") { RecomendacionesComidaScreen(navController) }
                        composable("accesorios_info")        { InfoAccesoriosScreen(navController) }

                        composable(
                            route = "dog/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getInt("id") ?: 0
                            DogDetailScreen(navController, id)
                        }
                        composable("carrito") {
                            CarritoScreen(navController, productoViewModel)
                        }

                    }
                    }
                }
            }
        }
    }



@Composable
fun HomeContent(
    scrollState: ScrollState,
    navController: NavHostController,
    onImageClick: (Int) -> Unit,
    viewModel: ProductoViewModel      // ← sin “= viewModel()”
) {
    val productos by viewModel.productosFiltrados.collectAsState()
    val query     by viewModel.searchQuery.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        SearchBar(
            query = query,
            onQueryChanged = viewModel::setSearchQuery,
            onClear = { viewModel.setSearchQuery("") }
        )

        Spacer(Modifier.height(12.dp))

        if (query.isNotBlank()) {
            Text("Resultados: ${productos.size}", style = MaterialTheme.typography.labelLarge)
            Spacer(Modifier.height(8.dp))

            productos.forEach { prod ->
                Text("• ${prod.nombre} (${prod.marca})", style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(Modifier.height(16.dp))
            Divider()
            Spacer(Modifier.height(16.dp))
        }
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
        CategoryCarousel(navController)

        Spacer(modifier = Modifier.height(24.dp))
        SectionDivider(text = "Explora más opciones")
        Spacer(modifier = Modifier.height(12.dp))

        FeatureButtons { destino ->
            when (destino) {
                "veterinario" -> navController.navigate("veterinarios")
                "comida"      -> navController.navigate("recomendaciones_comida")
                "accesorios"  -> navController.navigate("accesorios_info")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        SectionTitle(title = "Conoce a nuestros perritos")
        FoodImageGrid(navController)   // pasa el navController


        SectionTitle(title = "Sobre Nosotros")
        InfoSection()

        Spacer(modifier = Modifier.height(24.dp))
        FooterSection()
    }
}

@Composable
fun PaginaDetalle(titulo: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = titulo, style = MaterialTheme.typography.headlineSmall)
    }
}

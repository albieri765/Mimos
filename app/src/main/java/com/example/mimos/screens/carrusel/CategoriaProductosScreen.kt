package com.example.mimos.screens.carrusel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mimos.R
import com.example.mimos.data.ProductoModel
import com.example.mimos.screens.components.SearchBar      // ← tu SearchBar
import com.example.mimos.view.ProductoViewModel
import kotlinx.coroutines.launch

/* --------------------------------------------------------- */
/* 1.  PANTALLA  —  Lista de productos por categoría + Search */
/* --------------------------------------------------------- */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriaProductosScreen(
    nombreCategoria: String,
    navController: NavController,
    viewModel: ProductoViewModel
) {
    val productos by viewModel.productosPorCategoria.collectAsState()

    /* --- Estado local de búsqueda SOLO para esta pantalla --- */
    var query by remember { mutableStateOf("") }
    val filtrados = remember(query, productos) {
        if (query.isBlank()) productos
        else productos.filter {
            it.nombre.contains(query, true) ||
                    it.marca.contains(query, true)  ||
                    (it.descripcion ?: "").contains(query, true)
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    /* Cargar al entrar */
    LaunchedEffect(nombreCategoria) {
        viewModel.obtenerProductosPorCategoria(nombreCategoria)
        query = ""                       // limpia búsqueda si cambian de cat.
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Categoría: $nombreCategoria") }) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(Modifier.padding(padding)) {

            /* ---------- Barra de búsqueda ---------- */
            SearchBar(
                query = query,
                onQueryChanged = { query = it },
                onClear = { query = "" }
            )

            Spacer(Modifier.height(8.dp))

            if (filtrados.isEmpty()) {
                Text(
                    "No hay productos en esta categoría.",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(filtrados) { producto ->
                        ProductoCard(
                            producto = producto,
                            onAddToCart = {
                                viewModel.agregarAlCarrito(producto)
                                scope.launch {
                                    val res = snackbarHostState.showSnackbar(
                                        "${producto.nombre} añadido",
                                        actionLabel = "VER CARRITO"
                                    )
                                    if (res == SnackbarResult.ActionPerformed) {
                                        navController.navigate("carrito")
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

/* --------------------------------------------------------- */
/* 2.  TARJETA  —  Card con icono de carrito                  */
/* --------------------------------------------------------- */
@Composable
fun ProductoCard(
    producto: ProductoModel,
    onAddToCart: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            /* Imagen + texto */
            Row {
                AsyncImage(
                    model = producto.imagen?.replace("127.0.0.1", "10.0.2.2"),
                    contentDescription = producto.nombre,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(Modifier.width(8.dp))
                Column {
                    Text(producto.nombre ?: "Sin nombre")
                    Text("S/. ${producto.precio}")
                    Text("Marca: ${producto.marca}")
                    Text(
                        text = producto.descripcion ?: "Sin descripción",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            /* Icono de carrito */
            IconButton(onClick = onAddToCart) {
                Icon(
                    painter = painterResource(R.drawable.ic_cart),
                    contentDescription = "Añadir al carrito"
                )
            }
        }
    }
}

package com.example.mimos.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mimos.data.ProductoModel
import com.example.mimos.data.RetrofitInstance
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductoViewModel : ViewModel() {

    /* ---------- BÚSQUEDA ---------- */
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery
    fun setSearchQuery(newQuery: String) { _searchQuery.value = newQuery }

    /* ---------- LISTA PRINCIPAL ---------- */
    private val _productos = MutableStateFlow<List<ProductoModel>>(emptyList())
    val productos: StateFlow<List<ProductoModel>> = _productos

    /* ---------- FILTRADO EN VIVO ---------- */
    val productosFiltrados: StateFlow<List<ProductoModel>> =
        _searchQuery
            .debounce(300)
            .combine(_productos) { query, list ->
                if (query.isBlank()) list
                else list.filter {
                    it.nombre.contains(query, true) ||
                            it.marca.contains(query, true)  ||
                            it.categoria.nombre.contains(query, true)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    /* ---------- CARRITO ---------- */
    private val _carrito = MutableStateFlow<List<ProductoModel>>(emptyList())
    val carrito: StateFlow<List<ProductoModel>> = _carrito

    fun agregarAlCarrito(producto: ProductoModel) {
        _carrito.value = _carrito.value + producto         // añade (puedes evitar duplicados si lo deseas)
    }

    fun quitarDelCarrito(producto: ProductoModel) {
        _carrito.value = _carrito.value - producto
    }

    val totalCarrito: StateFlow<Double> = carrito
        .map { list -> list.sumOf { it.precio.toDoubleOrNull() ?: 0.0 } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)

    /* ---------- LISTA POR CATEGORÍA (ya existente) ---------- */
    private val _productosPorCategoria = MutableStateFlow<List<ProductoModel>>(emptyList())
    val productosPorCategoria: StateFlow<List<ProductoModel>> = _productosPorCategoria

    /* ---------- MÉTODOS DE CARGA ---------- */
    fun obtenerTodosLosProductos() {
        if (_productos.value.isNotEmpty()) return
        viewModelScope.launch {
            try {
                _productos.value = RetrofitInstance.api.getProductos()
            } catch (e: Exception) {
                Log.e("PRODUCTO_API", "Error al obtener todos los productos", e)
            }
        }
    }

    fun obtenerProductosPorPagina(pagina: String) {
        viewModelScope.launch {
            _productos.value = emptyList()
            try {
                val response = RetrofitInstance.api.getProductos()
                val (inicio, fin) = when (pagina) {
                    "pagina1" -> 1 to 2
                    "pagina2" -> 3 to 4
                    "pagina3" -> 5 to 6
                    else      -> 1 to 2
                }
                _productos.value = response.filter { it.orden in inicio..fin }
            } catch (e: Exception) {
                Log.e("PRODUCTO_API", "Error al obtener productos para $pagina", e)
            }
        }
    }

    fun obtenerProductosPorCategoria(nombreCategoria: String) {
        viewModelScope.launch {
            _productosPorCategoria.value = emptyList()
            try {
                _productosPorCategoria.value =
                    RetrofitInstance.api.getProductosPorCategoria(nombreCategoria)
            } catch (e: Exception) {
                Log.e("PRODUCTO_API", "Error categoría $nombreCategoria", e)
            }
        }
    }
}


package com.example.mimos.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mimos.data.ProductoModel
import com.example.mimos.data.RetrofitInstance
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductoViewModel : ViewModel() {

    /* ---------- BÚSQUEDA ---------- */
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery
    fun setSearchQuery(q: String) { _searchQuery.value = q }

    /* ---------- PRODUCTOS ---------- */
    private val _productos = MutableStateFlow<List<ProductoModel>>(emptyList())
    val   productos: StateFlow<List<ProductoModel>> = _productos

    /* Filtrado reactivo por búsqueda */
    val productosFiltrados: StateFlow<List<ProductoModel>> =
        _searchQuery
            .debounce(300)
            .combine(_productos) { query, list ->
                if (query.isBlank()) list
                else list.filter {
                    it.nombre.contains(query, true) ||
                            it.marca.contains(query, true) ||
                            it.categoria.nombre.contains(query, true)
                }
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    /* ---------- CARRITO ---------- */
    private val _carrito = MutableStateFlow<List<ProductoModel>>(emptyList())
    val carrito: StateFlow<List<ProductoModel>> = _carrito

    /** Cantidades por ID para mostrar en la UI */
    val cantidades: StateFlow<Map<Int, Int>> = carrito
        .map { list -> list.groupingBy { it.id }.eachCount() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyMap())

    fun agregarAlCarrito(prod: ProductoModel) {
        _carrito.value = _carrito.value + prod
    }

    /** Quita una unidad (si existe) */
    fun quitarDelCarrito(prod: ProductoModel) {
        val mutable = _carrito.value.toMutableList()
        val idx = mutable.indexOfFirst { it.id == prod.id }
        if (idx != -1) mutable.removeAt(idx)   // quita sólo una unidad
        _carrito.value = mutable
    }

    fun limpiarCarrito() { _carrito.value = emptyList() }

    val totalCarrito: StateFlow<Double> = carrito
        .map { it.sumOf { p -> p.precio.toDoubleOrNull() ?: 0.0 } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)

    /* ---------- CARGA REMOTA ---------- */
    private val _productosPorCategoria = MutableStateFlow<List<ProductoModel>>(emptyList())
    val   productosPorCategoria: StateFlow<List<ProductoModel>> = _productosPorCategoria

    fun obtenerTodosLosProductos() {
        if (_productos.value.isNotEmpty()) return
        viewModelScope.launch {
            try { _productos.value = RetrofitInstance.api.getProductos() }
            catch (e: Exception) { Log.e("PRODUCTO_API", "Todos", e) }
        }
    }

    fun obtenerProductosPorPagina(pagina: String) {
        viewModelScope.launch {
            _productos.value = emptyList()
            try {
                val r = RetrofitInstance.api.getProductos()
                val (ini, fin) = when (pagina) {
                    "pagina1" -> 1 to 2
                    "pagina2" -> 3 to 4
                    "pagina3" -> 5 to 6
                    else      -> 1 to 2
                }
                _productos.value = r.filter { it.orden in ini..fin }
            } catch (e: Exception) {
                Log.e("PRODUCTO_API", "Página $pagina", e)
            }
        }
    }

    fun obtenerProductosPorCategoria(nombre: String) {
        viewModelScope.launch {
            _productosPorCategoria.value = emptyList()
            try {
                _productosPorCategoria.value =
                    RetrofitInstance.api.getProductosPorCategoria(nombre)
            } catch (e: Exception) {
                Log.e("PRODUCTO_API", "Categoría $nombre", e)
            }
        }
    }
}


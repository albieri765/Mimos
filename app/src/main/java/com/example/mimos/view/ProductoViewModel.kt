package com.example.mimos.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mimos.data.ProductoModel
import com.example.mimos.data.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

class ProductoViewModel : ViewModel() {

    private val _productos = MutableStateFlow<List<ProductoModel>>(emptyList())
    val productos: StateFlow<List<ProductoModel>> = _productos

    private val _productosPorCategoria = MutableStateFlow<List<ProductoModel>>(emptyList())
    val productosPorCategoria: StateFlow<List<ProductoModel>> = _productosPorCategoria

    fun obtenerProductosPorPagina(pagina: String) {
        viewModelScope.launch {
            _productos.value = emptyList()
            try {
                val response = RetrofitInstance.api.getProductos()
                val (inicio, fin) = when (pagina) {
                    "pagina1" -> 1 to 2
                    "pagina2" -> 3 to 4
                    "pagina3" -> 5 to 6
                    else -> 1 to 2
                }
                val filtrados = response.filter { it.orden in inicio..fin }
                _productos.value = filtrados
            } catch (e: Exception) {
                Log.e("PRODUCTO_API", "Error al obtener productos para $pagina", e)
                Log.d("PRODUCTO_API", "Productos asignados: ${_productos.value.map { it.nombre }}")
            }
        }
    }

    fun obtenerTodosLosProductos() {
        if (_productos.value.isNotEmpty()) return
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProductos()
                _productos.value = response
            } catch (e: Exception) {
                Log.e("PRODUCTO_API", "Error al obtener todos los productos", e)
            }
        }
    }

    fun obtenerProductosPorCategoria(nombreCategoria: String) {
        viewModelScope.launch {
            _productosPorCategoria.value = emptyList() // limpia antes de cargar
            try {
                val response = RetrofitInstance.api.getProductosPorCategoria(nombreCategoria)
                _productosPorCategoria.value = response
                Log.d("PRODUCTO_API", "Productos para '$nombreCategoria': ${response.size}")
            } catch (e: Exception) {
                Log.e("PRODUCTO_API", "Error al obtener productos por categoría", e)
            }
        }
    }
}
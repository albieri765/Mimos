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

    init {
        obtenerProductos()
    }

    private fun obtenerProductos() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProductos()
                Log.d("PRODUCTO_API", "Productos recibidos: $response")
                _productos.value = response
            } catch (e: Exception) {
                Log.e("PRODUCTO_API", "Error al obtener productos", e)
            }
        }
    }
}

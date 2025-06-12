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

    // ✅ Cargar productos según la página (2 productos por página basados en el orden)
    fun obtenerProductosPorPagina(pagina: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProductos()
                Log.d("PRODUCTO_API", "Productos recibidos: $response")

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
            }
        }
    }
}

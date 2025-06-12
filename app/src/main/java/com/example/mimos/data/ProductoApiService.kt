package com.example.mimos.data

import com.example.mimos.data.ProductoModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductoApiService {

    // Obtener todos los productos
    @GET("api/productos/")
    suspend fun getProductos(): List<ProductoModel>

    // Obtener productos por nombre de categoría (opcional)
    @GET("api/productos/")
    suspend fun getProductosPorCategoria(
        @Query("categoria__nombre") categoria: String
    ): List<ProductoModel>
}

package com.example.mimos.data

import com.example.mimos.data.ProductoModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductoApiService {

    // Obtener todos los productos
    @GET("productos/")
    suspend fun getProductos(): List<ProductoModel>

    // Obtener productos por nombre de categoría (opcional)
    @GET("productos/categoria/{nombre}/")
    suspend fun getProductosPorCategoria(@Path("nombre") nombreCategoria: String): List<ProductoModel>

}

package com.example.mimos.data

import retrofit2.http.GET

interface ProductoApiService {
    @GET("api/productos/")
    suspend fun getProductos(): List<ProductoModel>
}

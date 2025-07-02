package com.example.mimos.data

data class CategoriaModel(
    val id: Int,
    val nombre: String
)

data class ProductoModel(
    val id: Int,
    val nombre: String,
    val precio: String,
    val peso: String,
    val marca: String,
    val orden: Int,
    val imagen: String?,
    val categoria: CategoriaModel,
    val descripcion: String?, // <- CAMBIO CLAVE AQUÍ
)
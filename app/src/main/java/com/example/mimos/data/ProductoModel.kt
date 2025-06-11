package com.example.mimos.data

data class ProductoModel(
    val id: Int,
    val nombre: String,
    val precio: String,
    val peso: String,
    val marca: String,
    val orden: Int,
    val imagen: String?
)

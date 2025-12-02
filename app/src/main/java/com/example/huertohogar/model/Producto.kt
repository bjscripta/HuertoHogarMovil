package com.example.huertohogar.data.model

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val imagen: String,
    val descripcion: String,
    var stock: Int = 50, // Stock inicial de cada producto
    val categoria: String = "Alimentos"
)
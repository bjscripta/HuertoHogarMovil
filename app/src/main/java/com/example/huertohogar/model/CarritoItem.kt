package com.example.huertohogar.data.model

data class CarritoItem(
    val id: Int,
    val productoId: Int,
    val nombre: String,
    val precio: Double,
    val imagen: String,
    var cantidad: Int,
    val descripcion: String = ""
) {
    fun getTotal(): Double = precio * cantidad
}
package com.example.huertohogar.data.repository

import com.example.huertohogar.data.model.CarritoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object CarritoRepository {
    private val _carritoItems = MutableStateFlow<List<CarritoItem>>(emptyList())
    val carritoItems: StateFlow<List<CarritoItem>> = _carritoItems.asStateFlow()

    fun agregarAlCarrito(productoId: Int, nombre: String, precio: Double, imagen: String, cantidad: Int): Boolean {
        val items = _carritoItems.value.toMutableList()
        val itemExistente = items.find { it.productoId == productoId }

        return if (itemExistente != null) {
            // Si ya existe, actualizar cantidad
            itemExistente.cantidad += cantidad
            _carritoItems.value = items
            true
        } else {
            // Si no existe, agregar nuevo
            val nuevoItem = CarritoItem(
                id = items.size + 1,
                productoId = productoId,
                nombre = nombre,
                precio = precio,
                imagen = imagen,
                cantidad = cantidad
            )
            _carritoItems.value = items + nuevoItem
            true
        }
    }

    fun actualizarCantidad(productoId: Int, nuevaCantidad: Int): Boolean {
        val items = _carritoItems.value.toMutableList()
        val itemIndex = items.indexOfFirst { it.productoId == productoId }

        if (itemIndex != -1) {
            if (nuevaCantidad > 0) {
                items[itemIndex].cantidad = nuevaCantidad
            } else {
                items.removeAt(itemIndex)
            }
            _carritoItems.value = items
            return true
        }
        return false
    }

    fun eliminarDelCarrito(productoId: Int): Boolean {
        val items = _carritoItems.value.toMutableList()
        val itemIndex = items.indexOfFirst { it.productoId == productoId }

        if (itemIndex != -1) {
            items.removeAt(itemIndex)
            _carritoItems.value = items
            return true
        }
        return false
    }

    fun getTotalItems(): Int {
        return _carritoItems.value.sumOf { it.cantidad }
    }

    fun getTotalPrecio(): Double {
        return _carritoItems.value.sumOf { it.getTotal() }
    }

    fun limpiarCarrito() {
        _carritoItems.value = emptyList()
    }

    fun getCantidadEnCarrito(productoId: Int): Int {
        return _carritoItems.value.find { it.productoId == productoId }?.cantidad ?: 0
    }
}
package com.example.huertohogar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogar.data.model.CarritoItem
import com.example.huertohogar.data.repository.CarritoRepository
import com.example.huertohogar.data.repository.ProductoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CarritoViewModel : ViewModel() {
    // Flujo de items del carrito
    val carritoItems: StateFlow<List<CarritoItem>> = CarritoRepository.carritoItems

    // Total de items en el carrito (contando cantidades)
    val totalItems: StateFlow<Int> = CarritoRepository.carritoItems
        .map { items -> items.sumOf { it.cantidad } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    // Precio total del carrito
    val totalPrecio: StateFlow<Double> = CarritoRepository.carritoItems
        .map { items -> items.sumOf { it.getTotal() } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    // Productos recomendados
    fun getProductosRecomendados() = ProductoRepository.getProductos().take(3)

    // Actualizar cantidad de un producto en el carrito
    fun actualizarCantidad(productoId: Int, nuevaCantidad: Int) {
        viewModelScope.launch {
            if (nuevaCantidad > 0) {
                CarritoRepository.actualizarCantidad(productoId, nuevaCantidad)
            } else {
                CarritoRepository.eliminarDelCarrito(productoId)
            }
        }
    }

    // Eliminar un item del carrito
    fun eliminarItem(productoId: Int) {
        viewModelScope.launch {
            CarritoRepository.eliminarDelCarrito(productoId)
        }
    }

    // Vaciar todo el carrito
    fun limpiarCarrito() {
        viewModelScope.launch {
            CarritoRepository.limpiarCarrito()
        }
    }

    // Agregar producto recomendado al carrito
    fun agregarProductoRecomendado(productoId: Int, cantidad: Int = 1) {
        viewModelScope.launch {
            val producto = ProductoRepository.getProductoById(productoId)
            producto?.let {
                if (ProductoRepository.actualizarStock(productoId, cantidad)) {
                    CarritoRepository.agregarAlCarrito(
                        productoId = producto.id,
                        nombre = producto.nombre,
                        precio = producto.precio,
                        imagen = producto.imagen,
                        cantidad = cantidad
                    )
                }
            }
        }
    }
}
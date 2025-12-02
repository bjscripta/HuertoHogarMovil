package com.example.huertohogar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogar.data.model.Producto
import com.example.huertohogar.data.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatalogoViewModel : ViewModel() {
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()

    private val _mostrarDialog = MutableStateFlow(false)
    val mostrarDialog: StateFlow<Boolean> = _mostrarDialog.asStateFlow()

    private val _productoSeleccionado = MutableStateFlow<Producto?>(null)
    val productoSeleccionado: StateFlow<Producto?> = _productoSeleccionado.asStateFlow()

    init {
        cargarProductos()
    }

    private fun cargarProductos() {
        _productos.value = ProductoRepository.getProductos()
    }

    fun seleccionarProducto(producto: Producto) {
        _productoSeleccionado.value = producto
        _mostrarDialog.value = true
    }

    fun cerrarDialog() {
        _mostrarDialog.value = false
        _productoSeleccionado.value = null
    }

    fun actualizarProductos() {
        cargarProductos()
    }
}
package com.example.huertohogar.data.repository

import com.example.huertohogar.data.model.Producto

object ProductoRepository {
    private val productos = mutableListOf<Producto>()

    init {
        cargarProductosIniciales()
    }

    private fun cargarProductosIniciales() {
        productos.clear()
        productos.addAll(listOf(
            Producto(1, "Espinacas Frescas", 2500.0, "espinacas_frescas", "Espinacas orgánicas recién cosechadas", 50),
            Producto(2, "Leche Entera", 1800.0, "leche_entera", "Leche entera de vacas criadas en pradera", 30),
            Producto(3, "Manzana Fuji", 1200.0, "manzana_fuji", "Manzanas Fuji dulces y crujientes", 100),
            Producto(4, "Miel Orgánica", 3800.0, "miel_organica", "Miel pura de abejas sin procesar", 20),
            Producto(5, "Naranjas Valencia", 2200.0, "naranjas_valencia", "Naranjas jugosas para jugo", 60),
            Producto(6, "Pimientos Tricolores", 2950.0, "pimientos_tricolores", "Mix de pimientos rojo, amarillo y verde", 40),
            Producto(7, "Plátanos Cavendish", 1500.0, "platanos_cavendish", "Plátanos maduros y dulces", 80),
            Producto(8, "Quinua Orgánica", 3200.0, "quinoa_organica", "Quinua orgánica de grano entero", 25),
            Producto(9, "Zanahorias Orgánicas", 1100.0, "zanahorias_organicas", "Zanahorias dulces cultivadas sin pesticidas", 70)
        ))
    }

    fun getProductos(): List<Producto> = productos

    fun getProductoById(id: Int): Producto? = productos.find { it.id == id }

    fun actualizarStock(productoId: Int, cantidadVendida: Int): Boolean {
        val producto = productos.find { it.id == productoId }
        producto?.let {
            if (it.stock >= cantidadVendida) {
                it.stock -= cantidadVendida
                return true
            }
        }
        return false
    }

    fun aumentarStock(productoId: Int, cantidad: Int) {
        val producto = productos.find { it.id == productoId }
        producto?.let {
            it.stock += cantidad
        }
    }
}
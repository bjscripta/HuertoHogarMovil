// ViewModel/AuthViewModel.kt
package com.example.huertohogar.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class UsuarioAuth(
    val correo: String = "",
    val nombre: String = "",
    val estaAutenticado: Boolean = false
)

class AuthViewModel : ViewModel() {

    // Estado persistente del usuario
    private val _usuarioActual = MutableStateFlow(UsuarioAuth())
    val usuarioActual: StateFlow<UsuarioAuth> = _usuarioActual.asStateFlow()

    // Estado del menú
    private val _mostrarMenuUsuario = MutableStateFlow(false)
    val mostrarMenuUsuario: StateFlow<Boolean> = _mostrarMenuUsuario.asStateFlow()

    // Iniciar sesión - GUARDAR en ViewModel
    fun iniciarSesion(correo: String) {
        viewModelScope.launch {
            val nombre = correo.substringBefore("@").replace(".", " ").capitalize()
            _usuarioActual.value = UsuarioAuth(
                correo = correo,
                nombre = nombre,
                estaAutenticado = true
            )
            println("DEBUG: Usuario autenticado: $correo")
        }
    }
    fun cerrarSesion() {
        viewModelScope.launch {
            _usuarioActual.value = UsuarioAuth()
            println("DEBUG: Usuario cerró sesión")
        }
    }
    fun toggleMenuUsuario() {
        viewModelScope.launch {
            _mostrarMenuUsuario.value = !_mostrarMenuUsuario.value
        }
    }

    fun ocultarMenuUsuario() {
        viewModelScope.launch {
            _mostrarMenuUsuario.value = false
        }
    }
}
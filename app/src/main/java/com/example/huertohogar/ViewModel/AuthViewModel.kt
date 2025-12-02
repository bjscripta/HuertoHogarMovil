package com.example.huertohogar.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Modelo para el usuario autenticado
data class UsuarioAuth(
    val correo: String = "",
    val nombre: String = "",
    val estaAutenticado: Boolean = false
)

class AuthViewModel : ViewModel() {
    // Estado del usuario autenticado
    private val _usuarioActual = MutableStateFlow(UsuarioAuth())
    val usuarioActual: StateFlow<UsuarioAuth> = _usuarioActual

    // Estado para el menú desplegable
    private val _mostrarMenuUsuario = MutableStateFlow(false)
    val mostrarMenuUsuario: StateFlow<Boolean> = _mostrarMenuUsuario

    // Función para iniciar sesión
    fun iniciarSesion(correo: String) {
        viewModelScope.launch {
            // Extraer nombre del correo (antes del @)
            val nombre = correo.substringBefore("@")
            _usuarioActual.value = UsuarioAuth(
                correo = correo,
                nombre = nombre,
                estaAutenticado = true
            )
        }
    }

    // Función para cerrar sesión
    fun cerrarSesion() {
        viewModelScope.launch {
            _usuarioActual.value = UsuarioAuth()
        }
    }

    // Función para mostrar/ocultar menú de usuario
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
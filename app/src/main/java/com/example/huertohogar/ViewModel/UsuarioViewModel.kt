// ViewModel/UsuarioViewModel.kt
package com.example.huertohogar.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogar.data.repository.UsuarioRepository
import kotlinx.coroutines.launch

class UsuarioViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UsuarioRepository(application)
    val usuarios = repository.usuarios

    fun registrarUsuario(correo: String, contrasena: String) {
        viewModelScope.launch {
            repository.registrarUsuario(correo, contrasena)
        }
    }

    fun cargarUsuarios() {
        viewModelScope.launch {
            repository.obtenerUsuarios()
        }
    }
    fun validarLogin(correo: String, contrasena: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val resultado = repository.validarLogin(correo, contrasena)
                onResult(resultado)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}
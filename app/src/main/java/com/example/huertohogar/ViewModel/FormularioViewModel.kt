package com.example.conedb.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conedb.data.UsuarioDAO
import com.example.conedb.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FormularioViewModel(private val usuarioDAO: UsuarioDAO): ViewModel() {
    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())

    val usuarios = _usuarios.asStateFlow()

    fun agregarUsuario(nombre: String, contrasena: String){
        val nuevoUsuario = Usuario(nombre = nombre, contrasena = contrasena)

        viewModelScope.launch {
            usuarioDAO.insertar(nuevoUsuario)
            _usuarios.value = usuarioDAO.obtenerUsuarios()
        }
    }

    fun cargarUsuario(){
        viewModelScope.launch {
            _usuarios.value = usuarioDAO.obtenerUsuarios()
        }
    }
}
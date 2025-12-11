// data/repository/UsuarioRepository.kt
package com.example.huertohogar.data.repository

import android.content.Context
import androidx.room.Room
import com.example.conedb.data.UsuarioDataBase
import com.example.conedb.data.UsuarioDAO
import com.example.conedb.model.Usuario
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsuarioRepository(context: Context) {
    private val database: UsuarioDataBase
    private val usuarioDAO: UsuarioDAO
    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios = _usuarios.asStateFlow()

    init {
        database = Room.databaseBuilder(
            context.applicationContext,
            UsuarioDataBase::class.java,
            "usuarios_db"
        ).build()

        usuarioDAO = database.usuarioDAO()
    }
    suspend fun obtenerUsuarios() {
        val lista = usuarioDAO.obtenerUsuarios()
        _usuarios.value = lista
    }
    suspend fun registrarUsuario(correo: String, contrasena: String) {
        val usuario = Usuario(
            correo = correo,
            contrasena = contrasena
        )
        usuarioDAO.insertar(usuario)
        obtenerUsuarios()
    }

    suspend fun validarLogin(correo: String, contrasena: String): Boolean {
        val usuarios = usuarioDAO.obtenerUsuarios()
        return usuarios.any { it.correo == correo && it.contrasena == contrasena }
    }
}
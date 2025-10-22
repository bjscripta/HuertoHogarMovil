package com.example.conedb.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.conedb.model.Usuario

@Dao
interface UsuarioDAO {
    @Insert
    suspend fun insertar(usuario : Usuario)

    @Query("SELECT* FROM Usuario")
    suspend fun obtenerUsuarios() : List<Usuario>
}
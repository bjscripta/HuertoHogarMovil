package com.example.conedb.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.conedb.model.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract class UsuarioDataBase : RoomDatabase(){

    abstract fun usuarioDAO() : UsuarioDAO

}
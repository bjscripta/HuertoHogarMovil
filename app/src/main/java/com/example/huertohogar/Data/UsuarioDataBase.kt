package com.example.conedb.data

import androidx.room.Room
import androidx.room.RoomDatabase

abstract class UsuarioDataBase : RoomDatabase(){

    abstract fun usuarioDAO() : UsuarioDAO

}
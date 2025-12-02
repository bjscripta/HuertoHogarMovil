package com.example.huertohogar.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("huertohogar_session", Context.MODE_PRIVATE)

    fun saveSession(correo: String, nombre: String) {
        prefs.edit().apply {
            putString("correo", correo)
            putString("nombre", nombre)
            putBoolean("estaAutenticado", true)
            apply()
        }
    }

    fun getCorreo(): String = prefs.getString("correo", "") ?: ""
    fun getNombre(): String = prefs.getString("nombre", "") ?: ""
    fun isAuthenticated(): Boolean = prefs.getBoolean("estaAutenticado", false)

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
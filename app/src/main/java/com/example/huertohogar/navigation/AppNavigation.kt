package com.example.huertohogar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.huertohogar.ui.Screen.Blog
import com.example.huertohogar.ui.Screen.Carrito
import com.example.huertohogar.ui.Screen.Catalogo
import com.example.huertohogar.ui.Screen.Login
import com.example.huertohogar.ui.Screen.Registro
import com.example.huertohogar.ui.Screen.Home
import com.example.huertohogar.ui.Screen.Nosotros
import com.example.huertohogar.ui.Screen.PerfilUsuario
import com.example.huertohogar.ui.Screen.PostScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("login") { Login(navController = navController) }
        composable("registro") { Registro(navController = navController) }
        composable("home") { Home(navController = navController) }
        composable("catalogo") { Catalogo(navController = navController) }
        composable("carrito") { Carrito(navController = navController) }
        composable("perfil") { PerfilUsuario(navController = navController) }
        composable("blog") { Blog(navController = navController) }
        composable("nosotros") { Nosotros(navController = navController) }
    }
}
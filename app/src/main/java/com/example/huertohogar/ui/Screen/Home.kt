package com.example.huertohogar.ui.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.huertohogar.R
import com.example.huertohogar.ViewModel.AuthViewModel

@Composable
fun Home(navController: NavController) {
    val authViewModel: AuthViewModel = viewModel()
    val usuarioActual by authViewModel.usuarioActual.collectAsState()
    val mostrarMenu by authViewModel.mostrarMenuUsuario.collectAsState()

    var selectedItem by remember { mutableStateOf("Inicio") }
    val menuItems = listOf("Inicio", "Productos", "Nosotros", "Blogs")

    // Efecto para cerrar el menú si se hace clic fuera
    LaunchedEffect(Unit) {
        // El menú se cierra automáticamente con la lógica del ViewModel
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_huertoghogar),
                        contentDescription = "Logo HuertoHogar",
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                    )

                    if (usuarioActual.estaAutenticado) {
                        // MOSTRAR USUARIO AUTENTICADO (icono + nombre + menú)
                        Box {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable { authViewModel.toggleMenuUsuario() }
                                    .padding(8.dp)
                            ) {
                                // Icono de usuario
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF81154C))
                                        .padding(8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = "Usuario",
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                // Nombre del usuario
                                Text(
                                    text = usuarioActual.nombre,
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.width(4.dp))

                                // Flecha del menú desplegable
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Menú usuario",
                                    tint = Color.Black
                                )
                            }

                            // MENÚ DESPLEGABLE
                            if (mostrarMenu) {
                                DropdownMenu(
                                    expanded = mostrarMenu,
                                    onDismissRequest = { authViewModel.ocultarMenuUsuario() },
                                    modifier = Modifier.background(Color.White)
                                ) {
                                    DropdownMenuItem(
                                        text = { Text("Ver Perfil") },
                                        onClick = {
                                            authViewModel.ocultarMenuUsuario()
                                            // Navegar al perfil (crearás esta pantalla después)
                                            // navController.navigate("perfil")
                                            println("Navegar a perfil")
                                        }
                                    )
                                    Divider()
                                    DropdownMenuItem(
                                        text = { Text("Cerrar Sesión", color = Color.Red) },
                                        onClick = {
                                            authViewModel.cerrarSesion()
                                            authViewModel.ocultarMenuUsuario()
                                            // Navegar al login
                                            navController.navigate("login") {
                                                popUpTo("home") { inclusive = true }
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    } else {
                        // MOSTRAR BOTONES DE LOGIN/REGISTRO (cuando NO está autenticado)
                        Row {
                            TextButton(
                                onClick = {
                                    navController.navigate("login")
                                }
                            ) {
                                Text(
                                    text = "Iniciar Sesión",
                                    color = Color.Black,
                                    fontSize = 12.sp
                                )
                            }
                            TextButton(
                                onClick = {
                                    navController.navigate("registro")
                                }
                            ) {
                                Text(
                                    text = "Registrarse",
                                    color = Color.Black,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    menuItems.forEach { item ->
                        TextButton(
                            onClick = {
                                selectedItem = item
                                // NUEVO: Navegar a Catalogo cuando se selecciona "Productos"
                                if (item == "Productos") {
                                    navController.navigate("catalogo")
                                }
                            }
                        ) {
                            Text(
                                text = item,
                                color = if (selectedItem == item) Color(0xFF81154C) else Color.Black,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    "Contacto",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    "Av. Principal 123, Santiago",
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Text(
                    "+56 2 2345 6789",
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Text(
                    "info@huertohogar.cl",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }
        },
        containerColor = Color.LightGray
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (selectedItem) {
                "Inicio" -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (usuarioActual.estaAutenticado) {
                            Text(
                                "¡Bienvenido ${usuarioActual.nombre}!",
                                style = MaterialTheme.typography.headlineMedium,
                                color = Color(0xFF81154C),
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            Text(
                                "Correo: ${usuarioActual.correo}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                        } else {
                            Text(
                                "Bienvenido a HuertoHogar",
                                style = MaterialTheme.typography.headlineMedium,
                                color = Color(0xFF81154C),
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            Text(
                                "Inicia sesión para acceder a todas las funciones",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                        }
                    }
                }
                "Productos" -> {
                    // NUEVO: Si por alguna razón no navegó automáticamente
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Redirigiendo a catálogo...",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                        Button(
                            onClick = { navController.navigate("catalogo") },
                            modifier = Modifier.padding(top = 16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF81154C)
                            )
                        ) {
                            Text("Ir al Catálogo")
                        }
                    }
                }
                "Nosotros" -> Text("Página Nosotros", style = MaterialTheme.typography.headlineMedium)
                "Blogs" -> Text("Página de Blogs", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}
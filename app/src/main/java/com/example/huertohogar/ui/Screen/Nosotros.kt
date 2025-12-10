package com.example.huertohogar.ui.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
fun Nosotros(navController: NavController) {
    val authViewModel: AuthViewModel = viewModel()
    val usuarioActual by authViewModel.usuarioActual.collectAsState()
    val mostrarMenu by authViewModel.mostrarMenuUsuario.collectAsState()

    var selectedItem by remember { mutableStateOf("Nosotros") }
    val menuItems = listOf("Inicio", "Productos", "Nosotros", "Blogs")

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
                        Box {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable { authViewModel.toggleMenuUsuario() }
                                    .padding(8.dp)
                            ) {
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
                                Text(
                                    text = usuarioActual.nombre,
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Menú usuario",
                                    tint = Color.Black
                                )
                            }
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
                                            println("Navegar a perfil")
                                        }
                                    )
                                    Divider()
                                    DropdownMenuItem(
                                        text = { Text("Cerrar Sesión", color = Color.Red) },
                                        onClick = {
                                            authViewModel.cerrarSesion()
                                            authViewModel.ocultarMenuUsuario()
                                            navController.navigate("login") {
                                                popUpTo("home") { inclusive = true }
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    } else {
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
                                when (item) {
                                    "Inicio" -> navController.navigate("home")
                                    "Productos" -> navController.navigate("catalogo")
                                    "Blogs" -> navController.navigate("blog")
                                    "Nosotros" -> { } // Ya estamos aquí
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
                .background(Color.LightGray)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sobre Nosotros",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF81154C),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "# Nuestra Misión",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color(0xFF81154C),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "HuertoHogar. Nuestra misión es proporcionar productos frescos y de calidad directamente desde el campo hasta la puerta de nuestros clientes, garantizando la frescura y el sabor en cada entrega. Nos comprometemos a fomentar una conexión más cercana entre los consumidores y los agricultores locales, apoyando prácticas agrícolas sostenibles y promoviendo una alimentación saludable en todos los hogares chilenos.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black,
                            lineHeight = 24.sp
                        )
                    }
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "# Nuestra Visión",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color(0xFF81154C),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "HuertoHogar. Nuestra visión es ser la tienda online líder en la distribución de productos frescos y naturales en Chile, reconocida por nuestra calidad excepcional, servicio al cliente y compromiso con la sostenibilidad. Aspiramos a expandir nuestra presencia a nivel nacional e internacional, estableciendo un nuevo estándar en la distribución de productos agrícolas directos del productor al consumidor.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black,
                            lineHeight = 24.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
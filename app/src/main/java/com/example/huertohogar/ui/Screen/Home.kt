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
fun Home(navController: NavController) {
    val authViewModel: AuthViewModel = viewModel()
    val usuarioActual by authViewModel.usuarioActual.collectAsState()
    val mostrarMenu by authViewModel.mostrarMenuUsuario.collectAsState()

    var selectedItem by remember { mutableStateOf("Inicio") }
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
                                            navController.navigate("perfil")
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
                                    "Productos" -> navController.navigate("catalogo")
                                    "Nosotros" -> navController.navigate("nosotros")
                                    "Blogs" -> navController.navigate("blog")
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
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.huerto),
                contentDescription = "Huerto Hogar",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "# Sobre Nosotros",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF81154C),
                    fontWeight = FontWeight.Bold,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Text(
                    text = "HuertoHogar es una tienda online dedicada a llevar la frescura y calidad de los productos del campo directamente a la puerta de nuestros clientes en Chile. Con más de 6 años de experiencia, operamos en más de 9 puntos a lo largo del país, incluyendo ciudades clave como Santiago, Puerto Montt, Villarica, Nacimiento, Viña del Mar, Valparaíso, y Concepción. Nuestra misión es conectar a las familias chilenas con el campo, promoviendo un estilo de vida saludable y sostenible.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    lineHeight = 24.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Justify
                )
                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F8F8)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Nuestras Ubicaciones",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color(0xFF81154C),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 12.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )

                        Text(
                            text = "Estamos presentes en:",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 12.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            listOf(
                                "Santiago",
                                "Puerto Montt",
                                "Villarica",
                                "Nacimiento",
                                "Viña del Mar",
                                "Valparaíso",
                                "Concepción"
                            ).forEach { ciudad ->
                                Text(
                                    text = "• $ciudad",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
                Button(
                    onClick = {
                        navController.navigate("catalogo")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF81154C)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 4.dp
                    )
                ) {
                    Text(
                        text = "Ver Productos",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
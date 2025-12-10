package com.example.huertohogar.ui.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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

data class Noticia(
    val id: Int,
    val titulo: String,
    val subtitulo: String,
    val contenido: String,
    val botonTexto: String
)

@Composable
fun Blog(navController: NavController) {
    val authViewModel: AuthViewModel = viewModel()
    val usuarioActual by authViewModel.usuarioActual.collectAsState()
    val mostrarMenu by authViewModel.mostrarMenuUsuario.collectAsState()

    var selectedItem by remember { mutableStateOf("Blogs") }
    val menuItems = listOf("Inicio", "Productos", "Nosotros", "Blogs")
    val noticias = listOf(
        Noticia(
            id = 1,
            titulo = "CASO CURIOSO #1: EL TOMATE QUE CRECIÓ EN MARTE",
            subtitulo = "Agricultura extraterrestre y sus beneficios para la Tierra",
            contenido = "En un experimento revolucionario de la NASA, científicos lograron cultivar tomates en un suelo simulado de Marte. Las plantas mostraron una adaptación sorprendente al ambiente marciano, desarrollando raíces más profundas y un sistema de conservación de agua único. Este descubrimiento no solo acerca la posibilidad de agricultura extraterrestre, sino que también ofrece insights valiosos para el cultivo en condiciones áridas en la Tierra.\n\nLos tomates marcianos mostraron un contenido de antioxidantes un 30% mayor que sus contrapartes terrestres, lo que podría revolucionar la agricultura sostenible en zonas desérticas.",
            botonTexto = "VER CASO"
        ),
        Noticia(
            id = 2,
            titulo = "CASO CURIOSO #2: LA JARDINERA DE 90 AÑOS CON EL HUERTO URBANO MÁS PRODUCTIVO",
            subtitulo = "Técnicas tradicionales y productividad en espacios reducidos",
            contenido = "Doña Carmen, una mujer de 90 años de Sevilla, España, ha desarrollado en su pequeño balcón de 10m² el huerto urbano más productivo documentado. Utilizando técnicas verticales y de asociación de cultivos que ella misma ha perfeccionado a lo largo de 40 años, cosecha anualmente más de 150kg de hortalizas, suficientes para abastecerse y regalar a sus vecinos.\n\nSu secreto: una mezcla especial de compost que incluye cáscaras de plátano, posos de café y cenizas de madera, junto con un sistema de riego por goteo artesanal que mantiene la humedad perfecta para cada planta.",
            botonTexto = "VER CASO"
        )
    )

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
                                    "Nosotros" -> navController.navigate("nosotros")
                                    "Blogs" -> { }
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.LightGray),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    "Noticias y Casos Curiosos",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF81154C),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    "Noticias y casos curiosos en HuertoHogar",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            items(noticias) { noticia ->
                TarjetaNoticia(noticia = noticia, onClick = {
                    println("Caso clickeado: ${noticia.titulo}")
                })
            }
        }
    }
}

@Composable
fun TarjetaNoticia(noticia: Noticia, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = noticia.titulo,
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF81154C),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = noticia.subtitulo,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = noticia.contenido,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp),
                lineHeight = 22.sp
            )
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF81154C)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = noticia.botonTexto,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
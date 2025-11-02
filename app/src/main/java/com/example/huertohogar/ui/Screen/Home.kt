package com.example.huertohogar.ui.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
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
                    Text(
                        "HuertoHogar",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row {
                        TextButton(
                            onClick = {  }
                        ) {
                            Text(
                                text = "Iniciar Sesion",
                                color = Color.Black,
                                fontSize = 12.sp
                            )
                        }
                        TextButton(
                            onClick = {  }
                        ) {
                            Text(
                                text = "Registrarse",
                                color = Color.Black,
                                fontSize = 12.sp
                            )
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
                            onClick = { selectedItem = item }
                        ) {
                            Text(
                                text = item,
                                color = if (selectedItem == item) Color.Blue else Color.Black,
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
                "Inicio" -> Text("Página de Inicio")
                "Productos" -> Text("Página de Productos")
                "Nosotros" -> Text("Página Nosotros")
                "Blogs" -> Text("Página de Blogs")
            }
        }
    }
}
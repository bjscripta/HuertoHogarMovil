package com.example.huertohogar.ui.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Admin(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Panel de Administración",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF81154C)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF0F8FF)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "Bienvenido Administrador",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF2E7D32)
                )

                Text(
                    "Funciones disponibles:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )

                listOf(
                    "• Administrar usuarios"
                ).forEach { funcion ->
                    Text(
                        text = funcion,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate("home") {
                    popUpTo("admin") { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF81154C)
            )
        ) {
            Text("Ir a la pagina Principal")
        }
        Button(
            onClick = { navController.navigate("verUsuarios") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF81154C)
            )
        ) {
            Text("Ver Usuarios Registrados")
        }
    }
}
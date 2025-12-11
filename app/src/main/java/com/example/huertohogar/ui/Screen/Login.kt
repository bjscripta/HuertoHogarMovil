package com.example.huertohogar.ui.Screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.huertohogar.ViewModel.AuthViewModel

@Composable
fun Login(navController: NavController) {
    val context = LocalContext.current
    val authViewModel: AuthViewModel = viewModel()

    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Iniciar Sesión",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF53AF4C)
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("Correo", color = Color(0x88B97C31)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Contraseña", color = Color(0x88B97C31)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (user.isNotBlank() && pass.isNotBlank()) {
                    if (user == "admin@duoc.cl" && pass == "123456") {
                        authViewModel.iniciarSesion(user)

                        Toast.makeText(context, "Bienvenido Admin", Toast.LENGTH_SHORT).show()
                        navController.navigate("admin") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                    else if (user.endsWith("@duoc.cl") || user.endsWith("@profesor.duoc.cl")) {
                        authViewModel.iniciarSesion(user)
                        Toast.makeText(context, "Bienvenido $user", Toast.LENGTH_SHORT).show()
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                    else {
                        Toast.makeText(context, "Solo correos @duoc.cl o @profesor.duoc.cl", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF81154C),
                contentColor = Color(0xFFC7F9CC)
            )
        ) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                navController.navigate("registro")
            }
        ) {
            Text("¿No tienes cuenta? Regístrate", color = Color(0xFF81154C))
        }
    }
}
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
fun Registro(navController: NavController) {
    val context = LocalContext.current
    val authViewModel: AuthViewModel = viewModel()

    var correo by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Registrarse",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF53AF4C)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo", color = Color(0x88B97C31)) },
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

        OutlinedTextField(
            value = confirmPass,
            onValueChange = { confirmPass = it },
            label = { Text("Confirmar Contraseña", color = Color(0x88B97C31)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Validaciones
                if (!correo.endsWith("@duoc.cl") && !correo.endsWith("@profesor.duoc.cl")) {
                    Toast.makeText(context, "Error, Solo se permiten correos con dominio @duoc.cl o @profesor.duoc.cl", Toast.LENGTH_SHORT).show()
                } else if (pass != confirmPass) {
                    Toast.makeText(context, "Error, las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                } else if (correo.isNotBlank() && pass.isNotBlank()) {
                    // Guardar en la BD (si tienes Room configurado)
                    println("DEBUG: Usuario registrado - $correo")

                    // Iniciar sesión automáticamente después del registro
                    authViewModel.iniciarSesion(correo)
                    Toast.makeText(context, "Usuario $correo registrado e iniciado sesión", Toast.LENGTH_SHORT).show()

                    // Navegación al home
                    navController.navigate("home") {
                        popUpTo("registro") { inclusive = true }
                    }

                    correo = ""
                    pass = ""
                    confirmPass = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF81154C),
                contentColor = Color(0xFFC7F9CC)
            )
        ) {
            Text(text = "Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                navController.navigate("login")
            }
        ) {
            Text("¿Ya tienes cuenta? Inicia Sesión", color = Color(0xFF81154C))
        }
    }
}
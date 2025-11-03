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
import com.example.conedb.ViewModel.FormularioViewModel

@Composable
fun Registro(viewModel: FormularioViewModel) {
    val context = LocalContext.current
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
                // Validaciones primero
                if (!correo.endsWith("@duoc.cl") && !correo.endsWith("@profesor.duoc.cl")) {
                    Toast.makeText(context, "Error, Solo se permiten correos con dominio @duoc.cl o @profesor.duoc.cl", Toast.LENGTH_SHORT).show()
                } else if (pass != confirmPass) {
                    Toast.makeText(context, "Error, las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                } else if (correo.isNotBlank() && pass.isNotBlank()) {
                    viewModel.agregarUsuario(correo, pass)
                    Toast.makeText(context, "Hola! $correo", Toast.LENGTH_SHORT).show()
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

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.mostrarUsuarios()
                Toast.makeText(context, "Verificando usuarios...", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF81154C),
                contentColor = Color(0xFFC7F9CC)
            )
        ) {
            Text("Enviar log")
        }

    }
}
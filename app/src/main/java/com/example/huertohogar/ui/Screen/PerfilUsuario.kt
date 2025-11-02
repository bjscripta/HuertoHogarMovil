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

@Composable
fun PerfilUsuario() {
    val context = LocalContext.current

    var rut by remember { mutableStateOf("21863953-K") }
    var correo by remember { mutableStateOf("benj.candia@duoc.cl") }
    var passActual by remember { mutableStateOf("") }
    var nuevaPass by remember { mutableStateOf("") }
    var confirmarPass by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Mi Perfil",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF53AF4C)
        )

        Spacer(Modifier.height(24.dp))

        Text(
            "Datos Personales",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0x88B97C31),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = rut,
            onValueChange = { rut = it },
            label = { Text("RUT", color = Color(0x88B97C31)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo", color = Color(0x88B97C31)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Cambiar Contraseña",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0x88B97C31),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = passActual,
            onValueChange = { passActual = it },
            label = { Text("Contraseña Actual", color = Color(0x88B97C31)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nuevaPass,
            onValueChange = { nuevaPass = it },
            label = { Text("Nueva Contraseña", color = Color(0x88B97C31)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = confirmarPass,
            onValueChange = { confirmarPass = it },
            label = { Text("Confirmar Nueva Contraseña", color = Color(0x88B97C31)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                 if (!correo.endsWith("@duoc.cl") && !correo.endsWith("@profesor.duoc.cl")) {
                    Toast.makeText(context, "Error, Solo se permiten correos con el dominio @duoc.cl o @profesor.duoc.cl", Toast.LENGTH_SHORT).show()
                } else if (nuevaPass.isNotEmpty() && nuevaPass != confirmarPass) {
                    Toast.makeText(context, "Error, Las nuevas contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF81154C),
                contentColor = Color(0xFFC7F9CC)
            )
        ) {
            Text("Guardar Cambios")
        }
    }
}
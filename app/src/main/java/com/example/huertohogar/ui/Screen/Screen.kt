package com.example.conedb.ui.theme.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.conedb.ViewModel.FormularioViewModel

@Composable
fun Formulario(viewModel: FormularioViewModel) {
    var nombre by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    val usuarios by viewModel.usuarios.collectAsState()






}
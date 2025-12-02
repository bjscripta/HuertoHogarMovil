package com.example.huertohogar.ui.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.huertohogar.data.model.Producto
import com.example.huertohogar.data.repository.CarritoRepository
import com.example.huertohogar.data.repository.ProductoRepository
import com.example.huertohogar.viewmodel.CatalogoViewModel
import com.example.huertohogar.R

@Composable
fun DialogCantidad(
    viewModel: CatalogoViewModel,
    onDismiss: () -> Unit,
    onConfirm: (cantidad: Int) -> Unit
) {
    val productoSeleccionado by viewModel.productoSeleccionado.collectAsState()
    var cantidad by remember { mutableStateOf(1) }

    if (productoSeleccionado != null) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Imagen del producto
                    Image(
                        painter = painterResource(id = getImagenResource(productoSeleccionado!!.imagen)),
                        contentDescription = productoSeleccionado!!.nombre,
                        modifier = Modifier
                            .size(150.dp)
                            .padding(10.dp),
                        contentScale = ContentScale.Crop
                    )

                    // Nombre del producto
                    Text(
                        text = productoSeleccionado!!.nombre,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    // Descripción
                    Text(
                        text = productoSeleccionado!!.descripcion,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Precio
                    Text(
                        text = "$${productoSeleccionado!!.precio.toInt()}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF81154C),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Stock disponible
                    Text(
                        text = "Stock disponible: ${productoSeleccionado!!.stock} unidades",
                        fontSize = 14.sp,
                        color = if (productoSeleccionado!!.stock > 0) Color.Green else Color.Red,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Selector de cantidad
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                if (cantidad > 1) cantidad--
                            },
                            enabled = cantidad > 1
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Disminuir",
                                tint = if (cantidad > 1) Color.Black else Color.Gray
                            )
                        }

                        Box(
                            modifier = Modifier
                                .width(60.dp)
                                .height(40.dp)
                                .background(Color.LightGray, RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = cantidad.toString(),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        IconButton(
                            onClick = {
                                if (cantidad < productoSeleccionado!!.stock) cantidad++
                            },
                            enabled = cantidad < productoSeleccionado!!.stock
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Aumentar",
                                tint = if (cantidad < productoSeleccionado!!.stock) Color.Black else Color.Gray
                            )
                        }
                    }

                    Text(
                        text = "Cantidad seleccionada",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Botones
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = onDismiss,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray
                            )
                        ) {
                            Text(
                                text = "Cancelar",
                                color = Color.Black
                            )
                        }

                        Button(
                            onClick = {
                                if (productoSeleccionado!!.stock >= cantidad) {
                                    onConfirm(cantidad)
                                }
                            },
                            modifier = Modifier.weight(1f),
                            enabled = productoSeleccionado!!.stock >= cantidad && cantidad > 0,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2E7D32)
                            )
                        ) {
                            Text(
                                text = "Agregar al Carrito",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

// Agrega esto al FINAL del archivo DialogCantidad.kt:

private fun getImagenResource(nombre: String): Int {
    return when (nombre) {
        "espinacas_frescas" -> R.drawable.espinacas_frescas
        "leche_entera" -> R.drawable.leche_entera
        "manzana_fuji" -> R.drawable.manzana_fuji
        "miel_organica" -> R.drawable.miel_organica
        "naranjas_valencia" -> R.drawable.naranjas_valencia
        "pimientos_tricolores" -> R.drawable.pimientos_tricolores
        "platanos_cavendish" -> R.drawable.platanos_cavendish
        "quinoa_organica" -> R.drawable.quinoa_organica
        "zanahorias_organicas" -> R.drawable.zanahorias_organicas
        else -> R.drawable.ic_launcher_foreground
    }
}
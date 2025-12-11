package com.example.huertohogar.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.huertohogar.R
import com.example.huertohogar.data.model.Producto
import com.example.huertohogar.data.repository.CarritoRepository
import com.example.huertohogar.viewmodel.CatalogoViewModel

@Composable
fun ProductoCard(
    producto: Producto,
    catalogoViewModel: CatalogoViewModel = viewModel()
) {
    val cantidadEnCarrito = remember { mutableStateOf(0) }

    // Actualizar cantidad en carrito cuando cambia
    LaunchedEffect(producto.id) {
        cantidadEnCarrito.value = CarritoRepository.getCantidadEnCarrito(producto.id)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del producto
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = getImagenResource(producto.imagen)),
                    contentDescription = producto.nombre,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Información del producto
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = producto.nombre,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = producto.descripcion,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Precio y stock
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "$${producto.precio.toInt()}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32)
                        )

                        Text(
                            text = "Stock: ${producto.stock}",
                            fontSize = 12.sp,
                            color = if (producto.stock > 0) Color.Green else Color.Red
                        )
                    }

                    // Mostrar si ya está en el carrito
                    if (cantidadEnCarrito.value > 0) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF2E7D32), RoundedCornerShape(16.dp))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "${cantidadEnCarrito.value} en carrito",
                                fontSize = 12.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Botón para agregar
            IconButton(
                onClick = {
                    catalogoViewModel.seleccionarProducto(producto)
                },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF81154C), RoundedCornerShape(8.dp))
                    .testTag("boton_carrito_${producto.id}")
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Agregar al carrito",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

fun getImagenResource(nombre: String): Int {
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
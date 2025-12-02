package com.example.huertohogar.ui.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.huertohogar.R
import com.example.huertohogar.data.model.Producto
import com.example.huertohogar.ui.components.CarritoItemCard
import com.example.huertohogar.ui.components.ProductoCard
import com.example.huertohogar.viewmodel.CarritoViewModel

@Composable
fun Carrito(navController: NavController) {
    val carritoViewModel: CarritoViewModel = viewModel()
    val carritoItems by carritoViewModel.carritoItems.collectAsState()
    val totalPrecio by carritoViewModel.totalPrecio.collectAsState()
    val productosRecomendados = carritoViewModel.getProductosRecomendados()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { navController.navigateUp() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Mi Carrito",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Botón para limpiar carrito
                    if (carritoItems.isNotEmpty()) {
                        IconButton(
                            onClick = { carritoViewModel.limpiarCarrito() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Limpiar carrito",
                                tint = Color.Red
                            )
                        }
                    }
                }

                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        },
        bottomBar = {
            if (carritoItems.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    // RESUMEN
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Text(
                            text = "$${totalPrecio.toInt()}",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // BOTÓN PROCESAR PAGO (solo visual)
                    Button(
                        onClick = {
                            // Aquí iría la lógica de pago
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2E7D32)
                        )
                    ) {
                        Text(
                            text = "Proceder al Pago",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.LightGray)
        ) {
            if (carritoItems.isEmpty()) {
                // CARRITO VACÍO
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_empty_cart),
                        contentDescription = "Carrito vacío",
                        modifier = Modifier.size(120.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Tu carrito está vacío",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Agrega productos desde el catálogo",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            navController.navigate("catalogo") {
                                popUpTo("carrito") { inclusive = true }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF81154C)
                        )
                    ) {
                        Text(
                            text = "Ir al Catálogo",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            } else {
                // LISTA DE PRODUCTOS EN EL CARRITO
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(carritoItems) { item ->
                        CarritoItemCard(
                            item = item,
                            onCantidadChange = { nuevaCantidad ->
                                carritoViewModel.actualizarCantidad(item.productoId, nuevaCantidad)
                            },
                            onEliminar = {
                                carritoViewModel.eliminarItem(item.productoId)
                            }
                        )
                    }

                    // PRODUCTOS RECOMENDADOS
                    item {
                        if (productosRecomendados.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(24.dp))

                            Text(
                                text = "También te puede interesar",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32),
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                productosRecomendados.forEach { producto ->
                                    ProductoRecomendadoCard(
                                        producto = producto,
                                        onAgregar = {
                                            carritoViewModel.agregarProductoRecomendado(producto.id)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductoRecomendadoCard(
    producto: Producto,
    onAgregar: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = getImagenResource(producto.imagen)),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .size(70.dp)
                    .padding(end = 12.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = producto.nombre,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$${producto.precio.toInt()}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32)
                )
            }

            Button(
                onClick = onAgregar,
                modifier = Modifier.height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF81154C)
                )
            ) {
                Text(
                    text = "Agregar",
                    fontSize = 12.sp
                )
            }
        }
    }
}

// Función auxiliar para obtener imágenes
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
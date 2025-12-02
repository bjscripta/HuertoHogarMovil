package com.example.huertohogar.ui.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.huertohogar.R
import com.example.huertohogar.data.repository.CarritoRepository
import com.example.huertohogar.ui.components.ProductoCard
import com.example.huertohogar.viewmodel.CarritoViewModel
import com.example.huertohogar.viewmodel.CatalogoViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Catalogo(navController: NavController) {
    val catalogoViewModel: CatalogoViewModel = viewModel()
    val carritoViewModel: CarritoViewModel = viewModel()
    val productos by catalogoViewModel.productos.collectAsState()
    val mostrarDialog by catalogoViewModel.mostrarDialog.collectAsState()

    // Contador del carrito
    var contadorCarrito by remember { mutableStateOf(0) }

    // Observar cambios en el carrito
    LaunchedEffect(Unit) {
        CarritoRepository.carritoItems.collectLatest { items ->
            contadorCarrito = items.sumOf { it.cantidad }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // BARRA SUPERIOR
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // LOGO - Click para ir a Home
                Image(
                    painter = painterResource(id = R.drawable.logo_huertoghogar),
                    contentDescription = "Logo HuertoHogar",
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp)
                        .clickable {
                            navController.navigate("home") {
                                popUpTo("home") { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                )

                // BOTÓN CARRITO con contador
                Box {
                    IconButton(
                        onClick = {
                            navController.navigate("carrito")
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF81154C))
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Carrito",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // BADGE con contador
                    if (contadorCarrito > 0) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color.Red)
                                .align(Alignment.TopEnd),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (contadorCarrito > 9) "9+" else contadorCarrito.toString(),
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Divider(color = Color.LightGray, thickness = 1.dp)

            // TÍTULO
            Text(
                text = "Catálogo de Productos",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )

            // LISTA DE PRODUCTOS
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(productos) { producto ->
                    ProductoCard(producto = producto)
                }
            }
        }

        // BOTÓN FLOTANTE PARA VOLVER A HOME
        FloatingActionButton(
            onClick = {
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                    launchSingleTop = true
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xFF81154C),
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home"
            )
        }

        // DIALOG PARA SELECCIONAR CANTIDAD
        if (mostrarDialog) {
            DialogCantidad(
                viewModel = catalogoViewModel,
                onDismiss = {
                    catalogoViewModel.cerrarDialog()
                },
                onConfirm = { cantidad ->
                    val producto = catalogoViewModel.productoSeleccionado.value
                    producto?.let {
                        // Actualizar stock en el repositorio
                        if (com.example.huertohogar.data.repository.ProductoRepository.actualizarStock(it.id, cantidad)) {
                            // Agregar al carrito
                            CarritoRepository.agregarAlCarrito(
                                productoId = it.id,
                                nombre = it.nombre,
                                precio = it.precio,
                                imagen = it.imagen,
                                cantidad = cantidad
                            )
                            // Actualizar productos en la vista
                            catalogoViewModel.actualizarProductos()
                        }
                    }
                    catalogoViewModel.cerrarDialog()
                }
            )
        }
    }
}
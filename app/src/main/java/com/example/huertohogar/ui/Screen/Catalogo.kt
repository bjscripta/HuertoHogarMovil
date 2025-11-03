package com.example.huertohogar.ui.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.huertohogar.R
import com.example.huertohogar.data.model.Producto

@Composable
fun Catalogo() {
    val productos = remember { getProductosEjemplo() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text(
                "Catalogo de productos",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32),
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(productos) { producto ->
            ProductoCard(producto = producto)
        }
    }
}

@Composable
fun ProductoCard(producto: Producto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = getImagenResource(producto.imagen)),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    producto.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    producto.descripcion,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "$${producto.precio.toInt()}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32)
                )
            }

            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2E7D32)
                )
            ) {
                Text("Agregar")
            }
        }
    }
}

fun getProductosEjemplo(): List<Producto> {
    return listOf(
        Producto(1, "Espinacas Frescas", 2500.0, "espinacas_frescas", "Espinacas orgánicas recién cosechadas"),
        Producto(2, "Leche Entera", 1800.0, "leche_entera", "Leche entera de vacas criadas en pradera"),
        Producto(3, "Manzana Fuji", 1200.0, "manzana_fuji", "Manzanas Fuji dulces y crujientes"),
        Producto(4, "Miel Orgánica", 3800.0, "miel_organica", "Miel pura de abejas sin procesar"),
        Producto(5, "Naranjas Valencia", 2200.0, "naranjas_valencia", "Naranjas jugosas para jugo"),
        Producto(6, "Pimientos Tricolores", 2950.0, "pimientos_tricolores", "Mix de pimientos rojo, amarillo y verde"),
        Producto(7, "Plátanos Cavendish", 1500.0, "platanos_cavendish", "Plátanos maduros y dulces"),
        Producto(8, "Quinua Orgánica", 3200.0, "quinoa_organica", "Quinua orgánica de grano entero"),
        Producto(9, "Zanahorias Orgánicas", 1100.0, "zanahorias_organicas", "Zanahorias dulces cultivadas sin pesticidas")
    )
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
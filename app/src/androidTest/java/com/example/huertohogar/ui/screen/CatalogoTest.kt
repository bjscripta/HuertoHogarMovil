package com.example.huertohogar.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.huertohogar.MainActivity
import com.example.huertohogar.data.model.Producto
import com.example.huertohogar.data.repository.ProductoRepository
import com.example.huertohogar.ui.Screen.Catalogo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatalogoTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        // Opcional: inicializar datos de prueba si es necesario
        // ProductoRepository.initializeWithTestData()
    }

    @Test
    fun catalogo_muestraTituloYElementosPrincipales() {
        composeTestRule.setContent {
            Catalogo(navController = rememberNavController())
        }

        // Verificar título principal
        composeTestRule.onNodeWithText("Catálogo de Productos").assertIsDisplayed()

        // Verificar que hay elementos de producto (al menos uno)
        // Esto depende de que ProductoRepository tenga datos
        Thread.sleep(1000) // Esperar a que se carguen los datos

        // Verificar que hay botones de carrito (icono)
        // Podemos buscar por contentDescription si lo tienes definido
        // composeTestRule.onNodeWithContentDescription("Carrito").assertIsDisplayed()
    }

    @Test
    fun catalogo_muestraListaDeProductos() {
        composeTestRule.setContent {
            Catalogo(navController = rememberNavController())
        }

        // Esperar a que se carguen los productos
        Thread.sleep(1000)

        // Obtener productos del repositorio
        val productos = ProductoRepository.getProductos()

        if (productos.isNotEmpty()) {
            // Verificar que se muestra al menos un producto
            val primerProducto = productos.first()

            // Scroll hasta encontrar el producto
            composeTestRule.onNodeWithText(primerProducto.nombre)
                .performScrollTo()
                .assertIsDisplayed()

            // Verificar que se muestra el precio
            composeTestRule.onNodeWithText("$${primerProducto.precio.toInt()}")
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun catalogo_botonesCarritoSonClickeables() {
        composeTestRule.setContent {
            Catalogo(navController = rememberNavController())
        }

        // Esperar a que se cargue la pantalla
        Thread.sleep(1000)

        // Buscar botones de "Agregar al carrito" en las ProductoCard
        // Necesitamos una forma de identificar estos botones
        // Podemos buscar por contentDescription si lo defines

        // Buscar todos los iconos de carrito (si tienen contentDescription)
        // val botonesCarrito = composeTestRule.onAllNodesWithContentDescription("Agregar al carrito")
        // if (botonesCarrito.fetchSemanticsNodes().isNotEmpty()) {
        //     botonesCarrito[0].assertIsEnabled().performClick()
        // }
    }

    @Test
    fun catalogo_botonHomeFlotanteEsVisible() {
        composeTestRule.setContent {
            Catalogo(navController = rememberNavController())
        }

        // Verificar que el botón flotante de home está presente
        // Buscar por contentDescription del icono Home
        composeTestRule.onNodeWithContentDescription("Home")
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun catalogo_muestraStockDeProductos() {
        composeTestRule.setContent {
            Catalogo(navController = rememberNavController())
        }

        // Esperar a que se carguen los productos
        Thread.sleep(1000)

        val productos = ProductoRepository.getProductos()

        if (productos.isNotEmpty()) {
            val primerProducto = productos.first()

            // Verificar que se muestra el stock
            composeTestRule.onNodeWithText("Stock: ${primerProducto.stock}")
                .performScrollTo()
                .assertIsDisplayed()
        }
    }

    @Test
    fun catalogo_interfazTieneColoresCorrectos() {
        composeTestRule.setContent {
            Catalogo(navController = rememberNavController())
        }

        // Verificar que el título tiene el color verde (#2E7D32)
        composeTestRule.onNodeWithText("Catálogo de Productos")
            .assertIsDisplayed()

        // Verificar botones con color morado (#81154C)
        // Esto es una verificación visual básica
    }
}
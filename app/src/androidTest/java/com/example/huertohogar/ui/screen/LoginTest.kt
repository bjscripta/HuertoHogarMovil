package com.example.huertohogar.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.huertohogar.MainActivity
import com.example.huertohogar.ui.Screen.Login
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.ui.test.onAllNodesWithText

@RunWith(AndroidJUnit4::class)
class LoginTest {

    @get:Rule
    val composeTestRule = createComposeRule()



    @Test
    fun login_muestraTodosLosElementosEsperados() {
        composeTestRule.setContent {
            Login(navController = rememberNavController())
        }

        // 1. Encontrar TODOS los nodos con "Iniciar Sesión"
        val nodosIniciarSesion = composeTestRule.onAllNodesWithText("Iniciar Sesión")
        val cantidad = nodosIniciarSesion.fetchSemanticsNodes().size

        // Debe haber al menos 2 (título y botón)
        assertTrue("Debe haber al menos 2 nodos con 'Iniciar Sesión'", cantidad >= 2)

        // 2. El PRIMERO es probablemente el título
        nodosIniciarSesion[0].assertIsDisplayed()

        // 3. El SEGUNDO es el botón
        nodosIniciarSesion[1]
            .assertIsDisplayed()
            .assertIsEnabled()

        // 4. Campos
        composeTestRule.onNodeWithText("Correo").assertIsDisplayed()
        composeTestRule.onNodeWithText("Contraseña").assertIsDisplayed()

        // 5. Link
        composeTestRule.onNodeWithText("¿No tienes cuenta? Regístrate").assertIsDisplayed()
    }

    @Test
    fun login_camposPermitenEntradaDeTexto() {
        composeTestRule.setContent {
            Login(navController = rememberNavController())
        }

        val emailTest = "usuario@duoc.cl"
        val passwordTest = "password123"

        // Escribir en campo de correo
        composeTestRule.onNodeWithText("Correo").performTextInput(emailTest)

        // Escribir en campo de contraseña
        composeTestRule.onNodeWithText("Contraseña").performTextInput(passwordTest)
    }
}
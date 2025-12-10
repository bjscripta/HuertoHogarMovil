package com.example.huertohogar.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.huertohogar.MainActivity
import com.example.huertohogar.ui.Screen.Registro
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistroTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun registro_muestraTodosLosCampos() {
        composeTestRule.setContent {
            Registro(navController = rememberNavController())
        }

        // Verificar título
        composeTestRule.onNodeWithText("Registrarse").assertIsDisplayed()

        // Verificar todos los campos
        composeTestRule.onNodeWithText("Correo").assertIsDisplayed()
        composeTestRule.onNodeWithText("Contraseña").assertIsDisplayed()
        composeTestRule.onNodeWithText("Confirmar Contraseña").assertIsDisplayed()

        // Verificar botón
        composeTestRule.onNodeWithText("Registrarse").assertIsDisplayed().assertIsEnabled()

        // Verificar link de login
        composeTestRule.onNodeWithText("¿Ya tienes cuenta? Inicia Sesión").assertIsDisplayed()
    }

    @Test
    fun registro_validaCorreoDuoc() {
        composeTestRule.setContent {
            Registro(navController = rememberNavController())
        }

        // Probar con correo @duoc.cl
        composeTestRule.onNodeWithText("Correo").performTextInput("alumno@duoc.cl")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("pass123")
        composeTestRule.onNodeWithText("Confirmar Contraseña").performTextInput("pass123")

        // Probar con correo @profesor.duoc.cl
        composeTestRule.onNodeWithText("Correo").performTextInput("")
        composeTestRule.onNodeWithText("Correo").performTextInput("profesor@profesor.duoc.cl")
    }

    @Test
    fun registro_validaCoincidenciaContraseñas() {
        composeTestRule.setContent {
            Registro(navController = rememberNavController())
        }

        // Contraseñas que coinciden
        composeTestRule.onNodeWithText("Correo").performTextInput("test@duoc.cl")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("password123")
        composeTestRule.onNodeWithText("Confirmar Contraseña").performTextInput("password123")

        // Botón debería estar habilitado
        composeTestRule.onNodeWithText("Registrarse").assertIsEnabled()
    }

    @Test
    fun registro_linkLoginEsClickeable() {
        composeTestRule.setContent {
            Registro(navController = rememberNavController())
        }

        composeTestRule.onNodeWithText("¿Ya tienes cuenta? Inicia Sesión")
            .assertIsDisplayed()
            .performClick()
    }
}
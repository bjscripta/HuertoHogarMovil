package com.example.huertohogar.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.huertohogar.ui.Screen.Registro
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class RegistroTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun registro_test_minimo() {
        // Solo setContent sin verificar nada
        composeTestRule.setContent {
            Registro(navController = rememberNavController())
        }

        // Si no hay excepción, el test pasa
        Thread.sleep(2000) // Espera larga
    }

    @Test
    fun test2_registro_tiene_correo() {
        composeTestRule.setContent {
            Registro(navController = rememberNavController())
        }

        Thread.sleep(500)
        composeTestRule.onNodeWithText("Correo", substring = true).assertIsDisplayed()
    }
}
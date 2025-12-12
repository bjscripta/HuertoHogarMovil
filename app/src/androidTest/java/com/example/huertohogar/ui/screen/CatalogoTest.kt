package com.example.huertohogar.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.huertohogar.ui.Screen.Catalogo
import org.junit.Rule
import org.junit.Test

class CatalogoTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test1_catalogo_muestra_titulo() {
        composeTestRule.setContent {
            Catalogo(navController = rememberNavController())
        }

        Thread.sleep(1000)
        composeTestRule.onNodeWithText("Catálogo", substring = true).assertIsDisplayed()
    }

    @Test
    fun test2_catalogo_se_carga() {
        composeTestRule.setContent {
            Catalogo(navController = rememberNavController())
        }

        Thread.sleep(1500)
        // Si llega aquí sin excepción, pasó
    }
}
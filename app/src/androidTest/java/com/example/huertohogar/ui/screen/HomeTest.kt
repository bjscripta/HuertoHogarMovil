package com.example.huertohogar.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.huertohogar.ui.Screen.Home
import org.junit.Rule
import org.junit.Test

/**class HomeTest{
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Home_muestraTitutlo(){
        composeTestRule.setContent {
            Home(navController = rememberNavController())

        }

        composeTestRule.onNodeWithText("HuertoHogar").assertIsDisplayed()
    }

    @Test
    fun Home_muestraDisponible(){
        composeTestRule.setContent {
            Home(navController = rememberNavController())
        }

        composeTestRule.onNodeWithText("Producto Disponible").assertIsDisplayed()
    }
}**/
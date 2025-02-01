package com.example.cartapp

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val cartViewModel = remember { CartViewModel() }

    NavHost(navController = navController, startDestination = "cashier") {
        composable("cashier") {
            CashierScreen(
                cartViewModel = cartViewModel,
                onShowCustomerScreen = { navController.navigate("customer") }
            )
        }
        composable("customer") {
            CustomerScreen(cartViewModel = cartViewModel,
                backToCashierScreen = {navController.navigate("cashier")}
            )
        }
    }
}

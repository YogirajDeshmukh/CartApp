package com.example.cartapp

import android.graphics.Paint.FAKE_BOLD_TEXT_FLAG
import android.graphics.Paint.Style
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.reflect.Modifier

@Composable
fun CashierScreen(cartViewModel: CartViewModel, onShowCustomerScreen: () -> Unit) {
    var itemName by remember { mutableStateOf("") }
    var itemPrice by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(text = "Cashier Screen", style = MaterialTheme.typography.bodyLarge,
            fontSize = 32.sp,
            color = Color.Black,
            fontStyle = FontStyle(FAKE_BOLD_TEXT_FLAG),
            textDecoration = TextDecoration.Underline
        )

        Spacer(modifier = androidx.compose.ui.Modifier.padding(8.dp))

        // Input fields for item details
        TextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Item Name") }
        )
        TextField(
            value = itemPrice,
            onValueChange = { itemPrice = it },
            label = { Text("Item Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = itemQuantity,
            onValueChange = { itemQuantity = it },
            label = { Text("Item Quantity") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Add item button
        Button(onClick = {
            val price = itemPrice.toDoubleOrNull() ?: 0.0
            val quantity = itemQuantity.toIntOrNull() ?: 1
            cartViewModel.addItem(CartItem(itemName, price, quantity))
            itemName = ""
            itemPrice = ""
            itemQuantity = ""
        }) {
            Text("Add Item")
        }

        // Button to show customer screen
        Button(onClick = { onShowCustomerScreen() }) {
            Text("Show Customer Screen")
        }
    }
}

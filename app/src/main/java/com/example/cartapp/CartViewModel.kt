package com.example.cartapp
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    // List of cart items
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> = _cartItems

    // Total price calculation
    val total: Double
        get() = _cartItems.sumOf { it.price * it.quantity }

    // Add  item to the cart
    fun addItem(item: CartItem) {
        _cartItems.add(item)
    }

    fun removeItem(item : CartItem){
        _cartItems.remove(item)
    }


}

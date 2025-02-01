package com.example.cartapp
import android.graphics.Paint.FAKE_BOLD_TEXT_FLAG
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun CustomerScreen(cartViewModel: CartViewModel,
                   backToCashierScreen : () -> Unit,
                   ) {

    var showDialog by remember { mutableStateOf(false) }
    var editedQuantity by remember { mutableStateOf("") }

    Column(
        modifier =  androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,


    ) {
        Text(text = "Customer Screen",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 32.sp,
            color = Color.Black,
            fontStyle = FontStyle(FAKE_BOLD_TEXT_FLAG),
            textDecoration = TextDecoration.Underline
            )
        Spacer(modifier = androidx.compose.ui.Modifier.padding(16.dp))


        Row(
            modifier =  androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(color = Color.LightGray),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            Text("Item Name")
            Spacer(modifier = androidx.compose.ui.Modifier.padding(8.dp))

            Text("Price X Unit")
            Spacer(modifier = androidx.compose.ui.Modifier.padding(8.dp))

            Text("Amount ")
            Spacer(modifier = androidx.compose.ui.Modifier.padding(8.dp))

            Text("Edit")
            Spacer(modifier = androidx.compose.ui.Modifier.padding(8.dp))
        }



        // List of cart items
        LazyColumn (verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .background(color = Color.LightGray)
                .height(400.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ){
            items(cartViewModel.cartItems) { item ->


                Row(
                    modifier =  androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                    Text(text = item.name)
                    Spacer(modifier = androidx.compose.ui.Modifier.padding(16.dp))

                    Text(text = "₹${item.price} x ${item.quantity}")
                    Spacer(modifier = androidx.compose.ui.Modifier.padding(16.dp))

                    Text(text = "₹${item.price * item.quantity}")
                    Spacer(modifier = androidx.compose.ui.Modifier.padding(16.dp))

                  IconButton(onClick = {cartViewModel.removeItem(item)},

                      ) {
                      Icon(
                          imageVector = Icons.Default.Delete, // Delete icon
                          contentDescription = "Delete Item",
                          tint = MaterialTheme.colorScheme.error) // Set color to red
                  }









                    IconButton(onClick = { showDialog=true
                                         editedQuantity=item.quantity.toString()
                                         },

                        ) {
                        Icon(
                            imageVector = Icons.Default.Edit, // Delete icon
                            contentDescription = "Edit Quantity",
                            tint = MaterialTheme.colorScheme.error) // Set color to red
                    }




                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = {
                                Text(text = "Edit Quantity",
                                    )

                                Column(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .wrapContentSize()
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .wrapContentSize()
                                            .padding(8.dp)
                                    ) {
                                        OutlinedTextField(
                                            value = editedQuantity,
                                            onValueChange = { editedQuantity = it },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp)
                                        )
                                    }
                                }
                            },
                            text = {},
                            confirmButton = {
                                Button(
                                    onClick = {
                                        item.quantity = editedQuantity.toIntOrNull() ?: 0
                                        showDialog=false
                                    },
                                    modifier = Modifier.padding(8.dp),
                                ) {
                                    Text("Update")
                                }
                            },
                            dismissButton = {
                                Button(onClick = { showDialog = false }) {
                                    Text("Dismiss")
                                }
                            },
                            modifier = Modifier.wrapContentSize()
                        )

                    }


                }
            }
        }

        // Total price
        Text(
            text = "Total: ₹${cartViewModel.total}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 16.sp,
            color = Color.Black,
            fontStyle = FontStyle(FAKE_BOLD_TEXT_FLAG),
            textDecoration = TextDecoration.Underline
        )

        Spacer(modifier = androidx.compose.ui.Modifier.padding(16.dp))

        Button(onClick = backToCashierScreen) {
            Text("  OK  ")
        }

    }

}




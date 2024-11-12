package com.example.e_comm.presentaion.Pages.Cart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_comm.Components.CoilImage
import com.example.e_comm.data.Localdata.CartItem
import com.example.e_comm.presentaion.Pages.Home.CartState
import com.example.e_comm.presentaion.Pages.Home.dataViewModel
import com.example.e_comm.R
import com.example.e_comm.ui.theme.MyGray
import com.example.e_comm.ui.theme.Purple40
import com.example.e_comm.utile.calculatesubtotal

@Composable
fun Cart() {
    val vm: cartviewmodel = hiltViewModel()
    val cart by vm.cartItems.collectAsState()
    Column(Modifier.fillMaxSize()) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier =
            Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Your Cart",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontSize = 22.sp
            )
        }
        if (cart.isEmpty()) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(text = "Cart is empty")
            }
        } else {
            val total = cart.calculatesubtotal()
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .height(90.dp)
            ) {
                items(cart) {
                    Row(
                        Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(80.dp)
                    ) {
                        Box(
                            Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(MyGray)
                        ) {
                            CoilImage(url = it.picUrl)
                        }

                        Column(
                            Modifier
                                .padding(10.dp)
                                .fillMaxHeight()
                                .fillMaxWidth(.65f)
                        ) {
                            Text(
                                text = it.title,
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily.Default,
                                fontSize = 15.sp
                            )
                            Text(
                                text = "$${it.price}",
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily.Default,
                                fontSize = 15.sp,
                                color = Purple40
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "$${it.price * it.quantity}",
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                fontSize = 15.sp
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.delete),
                                contentDescription = null,
                                modifier = Modifier.size(25.dp).clickable { vm.removeFromCart(it.id) },
                                tint = Color.Red
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier =
                                Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(MyGray)
                                    .padding(4.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier =
                                    Modifier
                                        .size(25.dp)
                                        .clip(RoundedCornerShape(5.dp))
                                        .background(Color.White)
                                        .clickable {
                                            if (it.quantity == 1) {
                                                vm.removeFromCart(it.id)
                                            } else
                                                vm.toggleAddtocart(it.copy(quantity = it.quantity - 1))
                                        }
                                ) {
                                    Text(text = "-")
                                }
                                Text(text = it.quantity.toString())
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier =
                                    Modifier
                                        .size(25.dp)
                                        .clip(RoundedCornerShape(5.dp))
                                        .background(
                                            Purple40
                                        )
                                        .clickable {
                                            vm.toggleAddtocart(it.copy(quantity = it.quantity + 1))
                                        }
                                ) {
                                    Text(text = "+", color = Color.White)
                                }
                            }
                        }
                    }


                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(10.dp)
                            .height(25.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Subtotal", fontWeight = FontWeight.Medium)
                        Text(text = "$$total")
                    }
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(10.dp)
                            .height(25.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Fee Delivery", fontWeight = FontWeight.Medium)
                        Text(text = "$50.0")
                    }
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(10.dp)
                            .height(25.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Total Tax", fontWeight = FontWeight.Medium)
                        Text(text = "$${total * 0.06}")
                    }
                }
                item {
                    Canvas(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth()
                    ) {
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, size.height),
                            strokeWidth = 2.dp.toPx() // Adjust the thickness
                        )
                    }
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(10.dp)
                            .height(25.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Total", fontWeight = FontWeight.Bold)
                        Text(text = "$${total + 50.0 + total * 0.06}")
                    }
                }
                item {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(25.dp)
                    ) {
                        FloatingActionButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = "Pay Now", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

    }
}
//
//
//@Preview(showSystemUi = true)
//@Composable
//private fun pp() {
//    Cart()
//}
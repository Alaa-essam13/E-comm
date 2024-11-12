package com.example.e_comm.presentaion.Pages.Home

import com.example.e_comm.data.Localdata.CartItem

data class CartState(
    val data:List<CartItem>,
    val isloading:Boolean = true
)

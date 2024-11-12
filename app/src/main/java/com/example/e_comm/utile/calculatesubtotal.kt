package com.example.e_comm.utile

import com.example.e_comm.data.Localdata.CartItem

fun List<CartItem>.calculatesubtotal():Double{
    return this.sumOf { it.price * it.quantity }
}
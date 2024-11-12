package com.example.e_comm.utile

import com.example.e_comm.data.Localdata.LocalItem
import com.example.e_comm.domain.Item

fun List<Item?>.filterLoved():List<Item>{
    return this.filterNotNull().filter { it.isLoved }
}
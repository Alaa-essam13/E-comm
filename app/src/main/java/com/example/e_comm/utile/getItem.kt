package com.example.e_comm.utile

import com.example.e_comm.data.Localdata.LocalItem
import com.example.e_comm.data.remotedata.RemoteItems
import com.example.e_comm.domain.Item

fun List<Item>.getItem(id:Int): Item {
    return find { it.id == id }
        ?: Item(0, 0, "", "", emptyList(), emptyList(), 0.0, 0.0, false)
}
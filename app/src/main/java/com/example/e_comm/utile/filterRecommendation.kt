package com.example.e_comm.utile

import com.example.e_comm.data.Localdata.LocalItem
import com.example.e_comm.data.remotedata.RemoteItems
import com.example.e_comm.domain.Item

fun List<Item?>.filterRecomendations():List<Item>{
    return this.filterNotNull().filter { it.showRecommended }
}
package com.example.e_comm.presentaion.Pages.itemPage

import com.example.e_comm.data.Localdata.LocalItem
import com.example.e_comm.domain.Item

data class ItemState(
    var item:LocalItem,
    var isLoading:Boolean,
    val message:String?=null
)

package com.example.e_comm.presentaion.Pages.Home

import com.example.e_comm.data.Localdata.LocalItem
import com.example.e_comm.domain.Item


data class dataState(
    val data :List<Item>,
    val isLoading : Boolean = false
)

package com.example.e_comm.Components

import androidx.annotation.DrawableRes

data class BottmItem(
    val route:String,
    @DrawableRes val selectedIcon:Int,
    @DrawableRes val unselectedIcon:Int,
    val title: String,
)

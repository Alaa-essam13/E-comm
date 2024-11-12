package com.example.e_comm.data.Localdata

import androidx.room.ColumnInfo

data class ItemsFavState(
    @ColumnInfo("id")
    val id:Int,
    @ColumnInfo("isLoved")
    val isFav:Boolean=false
)

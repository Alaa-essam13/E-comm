package com.example.e_comm.data.Localdata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cart_db")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id:Int =0,
    @ColumnInfo("itemId")
    val itemId :Int,
    @ColumnInfo("title")
    val title : String,
    @ColumnInfo("picUrl")
    val picUrl : String,
    @ColumnInfo("model")
    val model :String,
    @ColumnInfo("price")
    val price : Double,
    @ColumnInfo("quantity")
    val quantity : Int,
)

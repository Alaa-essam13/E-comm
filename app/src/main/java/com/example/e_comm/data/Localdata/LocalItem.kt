package com.example.e_comm.data.Localdata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Local_Store")
data class LocalItem(
    @PrimaryKey
    @ColumnInfo("id")
    val id :Int,
    @ColumnInfo("categoryId")
    val categoryId:Int,
    @ColumnInfo("title")
    val title:String,
    @ColumnInfo("description")
    val description:String,
    @ColumnInfo("model")
    val model:List<String>,
    @ColumnInfo("picUrl")
    val picUrl:List<String>,
    @ColumnInfo("price")
    val price:Double,
    @ColumnInfo("rating")
    val rating:Double,
    @ColumnInfo("showRecommended")
    val showRecommended:Boolean,
    @ColumnInfo("isLoved")
    val isLoved:Boolean=false
)

package com.example.e_comm.domain


data class Item(
val id :Int,
val categoryId:Int,
val title:String,
val description:String,
val model:List<String>,
val picUrl:List<String>,
val price:Double,
val rating:Double,
val showRecommended:Boolean,
val isLoved:Boolean=false
)


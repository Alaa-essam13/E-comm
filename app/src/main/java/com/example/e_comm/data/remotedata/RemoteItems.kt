package com.example.e_comm.data.remotedata


import com.google.gson.annotations.SerializedName

data class RemoteItems(
    val id :Int,
    @SerializedName("categoryId")
    val categoryId:Int,
    @SerializedName("title")
    val title:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("model")
    val model:List<String>,
    @SerializedName("picUrl")
    val picUrl:List<String>,
    @SerializedName("price")
    val price:Double,
    @SerializedName("rating")
    val rating:Double,
    @SerializedName("showRecommended")
    val showRecommended:Boolean
)

package com.example.e_comm.data.Localdata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface ItemsDao {

    @Query("select * from Local_Store")
    suspend fun getAllItems(): List<LocalItem>

    @Upsert
    suspend fun insertAll(items:List<LocalItem>)

    @Update(entity = LocalItem::class)
    suspend fun update(listfavState: ItemsFavState)

    @Query("select * from Local_Store where isLoved =1")
    suspend fun getLovedItems():List<LocalItem>

    @Update(entity = LocalItem::class)
    suspend fun updateAll(listgymfavState: List<ItemsFavState>)

    @Upsert
    suspend fun addtocart(item: CartItem)

    @Query("select * from Cart_db")
    suspend fun getCartItems():List<CartItem>

    @Query("delete from Cart_db where id = :itemId")
    suspend fun removeFromCart(itemId: Int)

    @Query("select * from local_store where id = :itemId")
    suspend fun getItem(itemId:Int): LocalItem

}
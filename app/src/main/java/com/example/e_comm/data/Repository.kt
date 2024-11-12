package com.example.e_comm.data

import com.example.e_comm.data.Localdata.AppDatabase
import com.example.e_comm.domain.Item
import com.example.e_comm.data.Localdata.ItemsDao
import com.example.e_comm.data.Localdata.ItemsFavState
import com.example.e_comm.data.Localdata.LocalItem
import com.example.e_comm.data.remotedata.ApiService
import com.example.e_comm.ecommApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val dao: ItemsDao
) {

    suspend fun loadData() = withContext(
        Dispatchers.IO
    ) {
        try {
            updateLocalDatabase()
        } catch (ex: Exception) {
            if (dao.getAllItems().isEmpty()) throw Exception("Something went wrong")
        }
    }

    suspend fun getItems(): List<Item> {
        return withContext(Dispatchers.IO) {
            return@withContext dao.getAllItems().map {
                Item(
                    it.id,
                    it.categoryId,
                    it.title,
                    it.description,
                    it.model,
                    it.picUrl,
                    it.price,
                    it.rating,
                    it.showRecommended,
                    it.isLoved
                )
            }
        }
    }

    private suspend fun updateLocalDatabase() {
        val items = apiService.getData().filterNotNull()
        val favitems = dao.getLovedItems()
        dao.insertAll(items.map {
            LocalItem(
                it.id,
                it.categoryId,
                it.title,
                it.description,
                it.model,
                it.picUrl,
                it.price,
                it.rating,
                it.showRecommended
            )
        })
        dao.updateAll(favitems.map { ItemsFavState(id = it.id, true) })
    }

    suspend fun toggleLoved(itemId: Int, currentLoved: Boolean) =
        withContext(Dispatchers.IO) {
            dao.update(ItemsFavState(id = itemId, isFav = currentLoved))
            dao.getAllItems()
        }


}
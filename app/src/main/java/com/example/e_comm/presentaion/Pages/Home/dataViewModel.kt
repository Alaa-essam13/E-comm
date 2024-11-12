package com.example.e_comm.presentaion.Pages.Home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_comm.data.Localdata.AppDatabase
import com.example.e_comm.data.Localdata.CartItem
import com.example.e_comm.data.Localdata.ItemsDao
import com.example.e_comm.data.Localdata.ItemsFavState
import com.example.e_comm.data.Localdata.LocalItem
import com.example.e_comm.ecommApp
import com.example.e_comm.data.remotedata.ApiService
import com.example.e_comm.domain.GetAllDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class dataViewModel @Inject constructor (
    private var dao:ItemsDao,
    private val getItemsUseCase:GetAllDataUseCase,
    private val apiService: ApiService
): ViewModel() {
    private var _state by mutableStateOf(
        dataState(
            emptyList(),
            isLoading = true
        )
    )
    private var _bannerState by mutableStateOf(
        bannerState(
            banners = emptyList(),
            isLoading = true
        )
    )

    private var _cartItems = MutableStateFlow<List<CartItem>>(emptyList())

    val bannerstate: State<bannerState>
        get() = derivedStateOf { _bannerState }


    val state: State<dataState>
        get() = derivedStateOf { _state }

//    val cartItems: StateFlow<List<CartItem>> =  _cartItems

//    private val apiService: ApiService

    init {

//        val retrofit: Retrofit =
//            Retrofit.Builder()
//                .addConverterFactory(
//                    GsonConverterFactory.create()
//                )
//                .baseUrl("https://e-comm-95a1c-default-rtdb.firebaseio.com/")
//                .build()
//
//        apiService = retrofit.create(ApiService::class.java)
        getcart()
        fetch()
    }


    fun getcart(){
        viewModelScope.launch {
            try {
                _cartItems.value =  dao.getCartItems()
            } catch (e: Exception) {
                _cartItems.value= emptyList()
            }
        }
    }


    fun fetch() {
        fetchData()
        fetchBanners()
    }

    fun fetchData() {
        viewModelScope.launch (Dispatchers.IO) {
            val receiedData = getItemsUseCase()
            _state = _state.copy(data = receiedData, isLoading = false)
        }
    }

    fun fetchBanners() {
        viewModelScope.launch {
            _bannerState = _bannerState.copy(banners = apiService.getBanners(), isLoading = false)
        }
    }

}
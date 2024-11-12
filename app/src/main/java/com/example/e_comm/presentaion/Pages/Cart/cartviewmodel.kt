package com.example.e_comm.presentaion.Pages.Cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_comm.data.Localdata.AppDatabase
import com.example.e_comm.data.Localdata.CartItem
import com.example.e_comm.data.Localdata.ItemsDao
import com.example.e_comm.ecommApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class cartviewmodel @Inject constructor(
    private var dao :ItemsDao
) : ViewModel() {

    private var _cartItems = MutableStateFlow<List<CartItem>>(emptyList())

    val cartItems: StateFlow<List<CartItem>> =  _cartItems

    init {
        loadCartItems()
    }

    fun loadCartItems(){
        viewModelScope.launch {
            try {
                _cartItems.value =  dao.getCartItems()
            } catch (e: Exception) {
                _cartItems.value= emptyList()
            }
        }
    }

    fun toggleAddtocart(cartItem: CartItem){
        viewModelScope.launch {
            dao.addtocart(cartItem)
            loadCartItems()
        }
    }

    fun removeFromCart(id:Int){
        viewModelScope.launch {
            dao.removeFromCart(id)
            loadCartItems()
        }
    }

}
package com.example.e_comm.presentaion.Pages.itemPage

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_comm.data.Localdata.AppDatabase
import com.example.e_comm.data.Localdata.CartItem
import com.example.e_comm.data.Localdata.ItemsDao
import com.example.e_comm.data.Localdata.LocalItem
import com.example.e_comm.domain.Item
import com.example.e_comm.domain.ToggleFavouriteUseCase
import com.example.e_comm.ecommApp
import com.example.e_comm.presentaion.Pages.Home.dataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private var dao:ItemsDao,
    private val toggleFavouriteUseCase:ToggleFavouriteUseCase
) :ViewModel() {

    private var _state by mutableStateOf(
        ItemState(
            LocalItem(0,0,"","", emptyList(), emptyList(),0.0,0.0,false),
            isLoading = true
        )
    )
    val state: State<ItemState>
        get() = derivedStateOf { _state }

    init {
        val itemId = savedStateHandle.get<Int>("itemId")?:0
        getItem(itemId)
    }

    private fun getItem(itemId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getITM(itemId)
            }catch (ex:Exception){
                if (_state==null) throw Exception("NotFounded")
            }
        }
    }

    private suspend fun getITM(itemId: Int){
        val itm =dao.getItem(itemId)
        _state.item = itm
        _state.isLoading=false
    }

    fun toggleFavItem(id: Int, oldState: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _state= _state.copy(item = _state.item.copy(isLoved = !oldState))
            toggleFavouriteUseCase(id,oldState)
        }
    }

    fun toggleAddtocart(cartItem: CartItem){
        viewModelScope.launch {
            dao.addtocart(cartItem)
        }
    }
}
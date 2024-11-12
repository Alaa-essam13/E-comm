package com.example.e_comm.presentaion.Pages.ListAllProducts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_comm.Components.MyCard
import com.example.e_comm.presentaion.Pages.Home.dataViewModel
import com.example.e_comm.utile.shimmerEffect
@Composable
fun ListProducts(onClickItem:(Int)-> Unit) {
    val vm :dataViewModel= hiltViewModel()
        val dt = vm.state.value.data
        if (vm.state.value.isLoading) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(6) {
                    Column(
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth(.5f)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .shimmerEffect()
                    ){

                    }
                }
            }

        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(dt) { item ->
                    MyCard(
                        id = item.id,
                        url = item.picUrl[0],
                        title = item.title,
                        rate = item.rating,
                        price = item.price
                    ) {
                        onClickItem(item.id)
                    }
                }
            }

    }
}

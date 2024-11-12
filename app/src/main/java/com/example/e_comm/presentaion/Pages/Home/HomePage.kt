package com.example.e_comm.presentaion.Pages.Home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_comm.Components.Banner
import com.example.e_comm.Components.MyCard
import com.example.e_comm.Components.TopScreen
import com.example.e_comm.utile.filterRecomendations
import com.example.e_comm.utile.shimmerEffect

@Composable
fun HomeScreen(  onLovedClick: () -> Unit, onClick:(Int)->Unit) {
    val datavm :dataViewModel= hiltViewModel()
    Column(
        Modifier
            .fillMaxSize()
            .padding()) {
      TopScreen { onLovedClick() }
        if(datavm.bannerstate.value.isLoading){
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()) {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(
                            RoundedCornerShape(15.dp)
                        )
                        .shimmerEffect()
                ) {

                }
            }
        }else {
            Banner(image = datavm.bannerstate.value.banners) {

            }
        }
      LazyVerticalGrid( columns = GridCells.Fixed(2)) {
          items(datavm.state.value.data.filterRecomendations()) {item->
              MyCard(id=item.id, url = item.picUrl[0], title = item.title, rate =item.rating, price =item.price){
                  onClick(item.id)
              }
          }
          if(datavm.state.value.isLoading) {
              items(2) {
                  Box(
                      modifier = Modifier
                          .padding(15.dp)
                          .fillMaxWidth(.5f)
                          .height(150.dp)
                          .clip(
                              RoundedCornerShape(15.dp)
                          )
                          .shimmerEffect()
                  ) {

                  }
              }
          }
      }
  }
}
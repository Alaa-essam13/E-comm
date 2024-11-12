package com.example.e_comm.presentaion.Pages.itemspage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_comm.Components.CoilImage
import com.example.e_comm.data.Localdata.LocalItem
import com.example.e_comm.R
import com.example.e_comm.domain.Item
import com.example.e_comm.presentaion.Pages.Home.dataViewModel
import com.example.e_comm.ui.theme.MyGray
import com.example.e_comm.ui.theme.Purple40
import com.example.e_comm.utile.filterLoved

@Composable
fun itemslist(vm: dataViewModel,onItemClick: (Int) -> Unit) {
    val items=vm.state.value.data.filterLoved()
    LazyColumn(Modifier.fillMaxSize()) {
        item {
            Row (horizontalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)){
                Text(
                    text = "Favourite items",
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Default,
                    fontSize = 15.sp
                )
            }
        }
        items(items) {
            Row(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(80.dp)
                    .clickable {
                        onItemClick(it.id)
                    }
            ) {
                Box(
                    Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(MyGray)
                ) {
                    CoilImage(url = it.picUrl[0])
                }

                Column(
                    Modifier
                        .padding(10.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(.65f)
                ) {
                    Text(
                        text = it.title,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Default,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "$${it.price}",
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Default,
                        fontSize = 15.sp,
                        color = Purple40
                    )
                }
            }
        }
    }

}
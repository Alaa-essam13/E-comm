package com.example.e_comm.presentaion.Pages.itemPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_comm.Components.CoilImage
import com.example.e_comm.Components.MyIconBtn
import com.example.e_comm.data.Localdata.CartItem
import com.example.e_comm.data.Localdata.LocalItem
import com.example.e_comm.R
import com.example.e_comm.ui.theme.MyGray
import com.example.e_comm.ui.theme.Purple40
import com.example.e_comm.utile.shimmerEffect
import kotlinx.coroutines.delay

@Composable
fun ItemPage(
    onBackClick: () -> Unit
) {
    val vm: ItemViewModel = hiltViewModel()
    val item = vm.state.value.item
    var selectedItem by remember { mutableIntStateOf(0) }
    var showMessage by remember { mutableStateOf(false) }

    LaunchedEffect(showMessage) {
        if (showMessage) {
            delay(2000)
            showMessage = false
        }
    }

    if (showMessage) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(90.dp)
                    .background(Color.Black, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
                    .zIndex(1f)
            ) {
                Text("Done", color = Color.White)
            }
        }
    }

    if (vm.state.value.isLoading) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            ){
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                MyIconBtn(icon = R.drawable.back) {
                    onBackClick()
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .shimmerEffect()
            )
        }
    } else {
        item.let { nonNullItem ->
            Column(Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.9f)
                        .padding(15.dp)
                ) {
                    item {
                        Top(onBackClick, nonNullItem) { id, isloved ->
                            vm.toggleFavItem(id, isloved)
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        bigBox(nonNullItem)
                        Spacer(modifier = Modifier.height(15.dp))
                        imagelist(nonNullItem)
                        Spacer(modifier = Modifier.height(15.dp))
                        pricesection(nonNullItem)
                        Spacer(modifier = Modifier.height(10.dp))
                        ratesection(nonNullItem)
                        Spacer(modifier = Modifier.height(10.dp))
                        modelsSection(nonNullItem, selectedItem) {
                            selectedItem = it
                        }
                        Text(
                            text = nonNullItem.description,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 2.dp, start = 5.dp)
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxSize()
                        .padding(13.dp)
                ) {
                    FloatingActionButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth(.8f)
                            .fillMaxHeight(),
                        containerColor = Purple40
                    ) {
                        Text(
                            text = "Buy Now",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.White
                        )
                    }
                    IconButton(
                        onClick = { /*TODO*/ },
                        Modifier
                            .padding(start = 10.dp)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(15.dp))
                            .background(MyGray)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.fillcart),
                            contentDescription = "cart",
                            tint = Purple40,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    showMessage = true
                                    vm.toggleAddtocart(
                                        CartItem(
                                            itemId = nonNullItem.id,
                                            title = nonNullItem.title,
                                            model = nonNullItem.model[selectedItem],
                                            picUrl = nonNullItem.picUrl[0],
                                            price = nonNullItem.price,
                                            quantity = 1
                                        )
                                    )
                                }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun modelsSection(itm: LocalItem, selectedItem: Int, onselectedItem: (Int) -> Unit) {

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        itemsIndexed(itm.model) { index, itm ->
            Box(
                contentAlignment = Alignment.Center,
                modifier =
                Modifier
                    .padding(5.dp)
                    .width(80.dp)
                    .height(55.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MyGray)
                    .clickable { onselectedItem(index) }
                    .border(
                        if (index == selectedItem) BorderStroke(
                            2.dp,
                            Color.Blue
                        ) else BorderStroke(0.dp, Color.Transparent),
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Text(
                    text = itm,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 2.dp, start = 5.dp)
                )
            }
        }
    }
}

@Composable
private fun ratesection(itm: LocalItem) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier =
        Modifier
            .fillMaxWidth()
            .height(25.dp)
    ) {
        Text(
            text = "Select Model",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            fontSize = 15.sp
        )
        Row {
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${itm.rating}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.padding(top = 2.dp, start = 5.dp)
            )
        }

    }
}

@Composable
private fun pricesection(itm: LocalItem) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier =
        Modifier
            .fillMaxWidth()
            .height(25.dp)
    ) {
        Text(
            text = itm.title,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            fontSize = 22.sp
        )
        Text(
            text = "$${itm.price}",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            fontSize = 22.sp
        )
    }
}

@Composable
private fun imagelist(itm: LocalItem) {
    LazyRow(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
    ) {
        items(itm.picUrl) { itm ->
            Box(
                Modifier
                    .padding(6.dp)
                    .size(70.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MyGray)
                    .padding(5.dp)
            ) {
                CoilImage(
                    url = itm,
                    ContentScale.Inside
                )
            }
        }
    }
}

@Composable
private fun bigBox(itm: LocalItem) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MyGray)
    ) {
        CoilImage(url = itm.picUrl[0])
    }
}

@Composable
private fun Top(
    onBackClick: () -> Unit,
    itm: LocalItem,
    onFavClick: (Int, Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        MyIconBtn(icon = R.drawable.back) {
            onBackClick()
        }
        MyIconBtn(icon = if (itm.isLoved) R.drawable.love else R.drawable.fav_icon) {
            onFavClick(itm.id, itm.isLoved)
        }
    }
}


//@Preview(showSystemUi = true)
//@Composable
//private fun pr() {
//    ItemPage()
//}
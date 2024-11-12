package com.example.e_comm.presentaion.Pages.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_comm.Components.CoilImage
import com.example.e_comm.R
import com.example.e_comm.data.Localdata.LocalItem
import com.example.e_comm.domain.Item
import com.example.e_comm.presentaion.Pages.Home.dataViewModel
import com.example.e_comm.ui.theme.MyGray
import com.example.e_comm.ui.theme.Purple40
import com.example.e_comm.utile.getItem
import com.example.e_comm.utile.searchfilter

@Composable
fun SearchPage(
    onItemClick: (Item) -> Unit
) {
    val vm :dataViewModel = hiltViewModel()
    val data = vm.state.value.data
    var search by remember {
        mutableStateOf(false)
    }
    var txt by remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current
    Column(
        Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        TextField(
            value = txt,
            onValueChange = {
                txt = it
                search = false
            },


            maxLines = 1,
            minLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    search = true
                    focusManager.clearFocus()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MyGray,
                unfocusedContainerColor = MyGray,
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (txt != "") {
            if (search) {
                Mylist(data.searchfilter(txt)) {
                    onItemClick(data.getItem(it))
                }
            } else {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(data.searchfilter(txt)) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .clickable { onItemClick(it) }
                        ) {
                            Text(text = it.title, fontSize = 15.sp)
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun Mylist(
    items: List<Item>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn(Modifier.fillMaxSize()) {
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

//@Preview(showSystemUi = true)
//@Composable
//private fun ppp() {
//    SearchPage()
//}
package com.example.e_comm.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_comm.R

@Composable
fun TopScreen(onLovedClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(Modifier.fillMaxWidth(.6f)) {
            Text(text = "Welcome")
            Text(text = "Alaa", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        MyIconBtn(icon = R.drawable.fav_icon) {
            onLovedClick()
        }

    }
}

//
//@Preview(showBackground = true)
//@Composable
//private fun test() {
//    TopScreen()
//}
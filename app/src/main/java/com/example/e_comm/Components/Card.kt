package com.example.e_comm.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_comm.R
import com.example.e_comm.ui.theme.Purple40

@Composable
fun MyCard(id:Int,url:String, title:String,rate:Double,price:Double,onClick:(Int) -> Unit) {
    Column(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(.5f)
            .padding(16.dp)
            .clickable { onClick(id) }
    ) {
        Box(
            modifier = Modifier
                .size(175.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.LightGray)
                .padding(5.dp)
        ) {
            CoilImage(url = url)
        }
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Row(
            Modifier
                .fillMaxSize()
                .padding(5.dp)) {
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = rate.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.LightGray,
                modifier = Modifier.padding(top = 2.dp, start = 5.dp)
            )
            Box (contentAlignment = Alignment.TopEnd, modifier = Modifier.fillMaxSize()){
                Text(
                    text = "$${price}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Purple40,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }

    }
}

//
//@Preview(showSystemUi = true)
//@Composable
//private fun pp() {
//    MyCard()
//}
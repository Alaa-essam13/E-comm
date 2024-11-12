package com.example.e_comm.Components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.e_comm.R
import com.example.e_comm.ui.theme.Purple40

@Composable
fun Categorybtn(selected: Boolean,@DrawableRes image:Int,txt:String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(30.dp)
            .width(if (selected) 70.dp else 30.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) Purple40 else Color.Gray)
    ) {
        Row {
        Image(painter = painterResource(id = image), contentDescription = null)
        Text(text = txt)
        }
    }
}

@Preview
@Composable
private fun p1() {
    Categorybtn(selected = true, image = R.drawable.cat1, txt = "Pc")
}

//@Preview
//@Composable
//private fun p3() {
//    Categorybtn(selected = true, image = R.drawable.cat3)
//}
//@Preview
//@Composable
//private fun p4() {
//    Categorybtn(selected = true, image = R.drawable.cat4)
//}
//@Preview
//@Composable
//private fun p5() {
//    Categorybtn(selected = true, image = R.drawable.cat5)
//}
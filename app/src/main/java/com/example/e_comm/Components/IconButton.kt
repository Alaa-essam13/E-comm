package com.example.e_comm.Components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.e_comm.R
import com.example.e_comm.ui.theme.MyGray

@Composable
fun MyIconBtn(@DrawableRes icon: Int, onClick: () -> Unit) {
    IconButton(
        onClick = onClick, colors = IconButtonDefaults.iconButtonColors(
            containerColor = MyGray
        ),
        modifier = Modifier.size(48.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}


@Preview
@Composable
private fun iconbtn() {
    MyIconBtn(R.drawable.search_icon) {}
}

@Preview
@Composable
private fun iconbtn2() {
    MyIconBtn(R.drawable.back) {}
}

@Preview
@Composable
private fun iconbtn3() {
    MyIconBtn(R.drawable.fav_icon) {}
}
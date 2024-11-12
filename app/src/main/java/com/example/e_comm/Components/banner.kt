package com.example.e_comm.Components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.e_comm.data.remotedata.RemoteSliderModel
import com.example.e_comm.utile.shimmerEffect
import kotlinx.coroutines.delay

@Composable
fun Banner(image: List<RemoteSliderModel>, onClick: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { image.size })
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.scrollToPage(nextPage)
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .height(200.dp)
        .fillMaxWidth()) {
        HorizontalPager(state = pagerState) {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                CoilImage(url = image[it].imageURL, contentScale = ContentScale.Fit)
            }
        }

        Indicator( image.size, pagerState.currentPage)
    }

}

@Composable
fun CoilImage(url: String,contentScale: ContentScale = ContentScale.Inside, modifier: Modifier = Modifier.fillMaxSize()) {
        AsyncImage(
        model =
        ImageRequest
            .Builder(LocalContext.current)
            .data(url)
            .build(),
        contentDescription = "Profile Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = contentScale,
    )
    
}

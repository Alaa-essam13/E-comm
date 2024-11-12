package com.example.e_comm.presentaion.Pages.onBoarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_comm.Components.Indicator
import kotlinx.coroutines.launch

@Composable
fun Onboardingcreen(
    onFinished: () -> Unit
) {
    val pages = listOf(
        Onboardingdata.FirstPage,
        Onboardingdata.SecondPage,
        Onboardingdata.ThirdPage,
    )
    val pageState = rememberPagerState(initialPage = 0) {
        pages.size
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
//
        content = {
            Column(Modifier.padding(it)) {
                HorizontalPager(state = pageState) { index ->
                    OnboardingUi(onboardingdata = pages[index])
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Indicator(pagesSize = pages.size, currentPage = pageState.currentPage)
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(onClick = {scope.launch {


                            if (pageState.currentPage < pages.size - 1) {
                                pageState.animateScrollToPage(pageState.currentPage + 1)
                            } else {
                                onFinished()
                            }
                        }
                        }) {
                            Text(text = if (pageState.currentPage < pages.size - 1) "Next" else "Start")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(50.dp)
                                .clickable { onFinished() }) {
                            if(pageState.currentPage<pages.size-1)
                              Text(text = "Skip", fontWeight = FontWeight.Medium, fontSize = 20.sp)
                        }
                    }
                }
            }

        }
    )
}


@Preview
@Composable
private fun pppp() {
    Onboardingcreen(){}
}
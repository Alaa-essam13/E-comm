package com.example.e_comm.presentaion.Pages.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_comm.ui.theme.Purple40

@Composable
fun OnboardingUi(onboardingdata: Onboardingdata) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
        Modifier
            .padding(top = 50.dp)
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Spacer(modifier = Modifier.height(35.dp))

        Image(
            painter = painterResource(onboardingdata.image),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(300.dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
                text = onboardingdata.title,
        modifier = Modifier.fillMaxWidth(.8f),
        fontWeight = FontWeight.Bold,
        color = Purple40,
        fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = onboardingdata.description,
            modifier = Modifier.fillMaxWidth(.8f),
            fontFamily = FontFamily.SansSerif,
            color = Color.Gray,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

    }
}


@Preview(showSystemUi = true)
@Composable
fun OnboardingGraphUIPreview1() {
    OnboardingUi(Onboardingdata.FirstPage)
}

@Preview(showSystemUi = true)
@Composable
fun OnboardingGraphUIPreview2() {
    OnboardingUi(Onboardingdata.SecondPage)
}

@Preview(showSystemUi = true)
@Composable
fun OnboardingGraphUIPreview3() {
    OnboardingUi(Onboardingdata.ThirdPage)
}

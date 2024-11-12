package com.example.e_comm.presentaion.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_comm.R
import com.example.e_comm.ui.theme.Purple40

@Composable
fun IntroScreen(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
        Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp, top = 35.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.intro_logo), contentDescription = "img")
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Welcome to Digital Store a Gateway to the Latest Tech",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(85.dp))
        FloatingActionButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth(.8f)
                .height(50.dp), containerColor = Purple40
        ) {
            Text(text = "Let's Get Started", color = Color.White, fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(25.dp))
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Already have an account? Sign in")
        }
    }

}


@Preview(showSystemUi = true)
@Composable
private fun prevew() {
    IntroScreen()
}
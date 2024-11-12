package com.example.e_comm.presentaion.Pages.onBoarding

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.e_comm.R

sealed class Onboardingdata(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,

){
    data object FirstPage:Onboardingdata(
        "Welcome to ShopEase",
        "Discover the best deals and find everything you need in one place",
        R.drawable.p1,
    )
    data object SecondPage:Onboardingdata(
        "Wide Range of Products",
        "Choose from thousands of products across categories tailored to your needs",
        R.drawable.p2,
    )
    data object ThirdPage:Onboardingdata(
        "Easy \n Checkout",
        "Enjoy a seamless shopping experience with quick and secure checkout",
        R.drawable.p3,
    )
}
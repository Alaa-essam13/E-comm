package com.example.e_comm.presentaion.Pages.Home

import com.example.e_comm.data.remotedata.RemoteSliderModel

data class bannerState(
    val banners: List<RemoteSliderModel>,
    val isLoading: Boolean,
)

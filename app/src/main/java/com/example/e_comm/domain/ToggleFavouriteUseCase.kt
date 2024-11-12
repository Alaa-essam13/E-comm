package com.example.e_comm.domain

import com.example.e_comm.data.Repository
import javax.inject.Inject

class ToggleFavouriteUseCase @Inject constructor(
    private val repository:Repository
) {
    suspend operator fun invoke(id:Int,oldState:Boolean){
        val newState = oldState.not()
        repository.toggleLoved(id,newState)
    }
}
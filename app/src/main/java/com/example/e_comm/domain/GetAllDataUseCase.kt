package com.example.e_comm.domain

import com.example.e_comm.data.Repository
import javax.inject.Inject

class GetAllDataUseCase @Inject constructor(
    private val repository: Repository,
    private  val getStoredUseCase: GetStoredUseCase
) {
    suspend operator fun invoke( ):List<Item>{
        repository.loadData()
        return getStoredUseCase()
    }
}
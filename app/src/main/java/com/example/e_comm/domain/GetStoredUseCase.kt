package com.example.e_comm.domain

import com.example.e_comm.data.Repository
import javax.inject.Inject

class GetStoredUseCase @Inject constructor(
    private val repository:Repository
) {
    suspend operator fun invoke(): List<Item> {
        return repository.getItems()
    }
}